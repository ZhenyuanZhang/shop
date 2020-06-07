package org.nj.zzy.common.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.nj.zzy.common.constant.CommonErrorConstant;
import org.nj.zzy.common.util.GetBeanUtil;
import org.nj.zzy.common.validate.exception.BaseException;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelReader {

    private int minColumnNum;
    private Class clazz;
    private XMLReader sheetParser;
    private Map<Class, InputStream> sheetInputStream = new HashMap<>();

    private Map<String, Field> chineseMap = new ConcurrentHashMap<>();
    private Map<String, Field> englishMap = new ConcurrentHashMap<>();
    private List<Field> titleSetField = new ArrayList<>();
    private List<Object> sheetData;

    public ExcelReader(InputStream inputStream) {
        try {
            OPCPackage pkg = OPCPackage.open(inputStream);
            XSSFReader xssfReader = new XSSFReader(pkg);
            StylesTable stylesTable = xssfReader.getStylesTable();
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
            ContentHandler handler =
                new XSSFSheetXMLHandler(stylesTable, null, strings, new SheetToDomain(), new DataFormatter(), false);
            sheetParser = SAXHelper.newXMLReader();
            sheetParser.setContentHandler(handler);

            SheetIterator sheetsData = (SheetIterator)xssfReader.getSheetsData();
            ExcelSheetName excelSheetName = GetBeanUtil.getBean(ExcelSheetName.class);
            while (sheetsData.hasNext()) {
                InputStream sheetInputStream = sheetsData.next();
                String sheetName = sheetsData.getSheetName();
                Class model = Optional.ofNullable(excelSheetName.getChineseSheetName().get(sheetName))
                    .orElse(excelSheetName.getEnglishSheetName().get(sheetName));
                Optional.ofNullable(model).ifPresent(m -> this.sheetInputStream.put(model, sheetInputStream));
            }
        } catch (IOException | OpenXML4JException | SAXException | ParserConfigurationException e) {
            log.error("[ExcelReader] Constructor error", e);
            throw new BaseException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> parse(Class<T> model) {
        try {
            chineseMap.clear();
            englishMap.clear();
            titleSetField.clear();
            sheetData = new ArrayList<>();

            this.clazz = model;
            Arrays.stream(clazz.getDeclaredFields()).filter(field -> field.isAnnotationPresent(ExcelColumn.class))
                .forEach(field -> {
                    ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                    chineseMap.put(annotation.zh_CN(), field);
                    englishMap.put(annotation.en_US(), field);
                });
            minColumnNum = chineseMap.size();

            sheetParser.parse(new InputSource(sheetInputStream.get(model)));
            return (List<T>)sheetData;
        } catch (IOException | SAXException e) {
            log.error("[ExcelReader] parse error", e);
            throw new BaseException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                CommonErrorConstant.CONVERSION_FAILURE, "sheetInputStream", "model");
        }
    }

    private class SheetToDomain implements SheetContentsHandler {

        private Object domain;

        @Override
        public void startRow(int rowNum) {
            domain = null;
        }

        @Override
        public void endRow(int rowNum) {
            Optional.ofNullable(domain).ifPresent(sheetData::add);
        }

        @Override
        public void cell(String cellReference, String value, XSSFComment comment) {
            CellReference cell = new CellReference(cellReference);
            int row = cell.getRow();
            if (row == 0) {
                titleSetField.add(Optional.ofNullable(chineseMap.get(value)).orElse(englishMap.get(value)));
            } else {
                int column = cell.getCol();
                if (column >= minColumnNum) {
                    return;
                }
                Field field = titleSetField.get(column);
                try {
                    if (domain == null) {
                        domain = clazz.newInstance();
                    }
                    if (field != null) {
                        Class<?> parameterType = field.getType();
                        field.setAccessible(true);
                        if (parameterType == String.class) {
                            field.set(domain, value);
                        } else {
                            // 这里将String类型转换为对应的包装类
                            field.set(domain, parameterType.getConstructor(String.class).newInstance(value));
                        }
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                    | NoSuchMethodException e) {
                    log.error("[ExcelReader] parse error", e);
                    throw new BaseException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        CommonErrorConstant.CONVERSION_FAILURE, "sheetInputStream", "model");
                }
            }
        }
    }
}
