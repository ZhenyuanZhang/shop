package org.nj.zzy.product.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.nj.zzy.common.http.ResponseBean;
import org.nj.zzy.common.util.CustomFileUtil;
import org.nj.zzy.common.util.excel.ExcelReader;
import org.nj.zzy.common.validate.util.CheckUtil;
import org.nj.zzy.product.domain.products.Product;
import org.nj.zzy.product.domain.vendors.Vendor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zhenyuan Zhang
 * @time 2020/6/7 21:19
 */
@Controller
@RequestMapping("/excel")
@Slf4j
public class ExcelImportController {

    /**
     * 导入Excel批量创建Vendor接口，将错误信息，写入上传的Excel表格中
     *
     * @param file Http请求获取文件流
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @PostMapping(value = "/import")
    @ResponseBody
    public ResponseBean<?> importVendors(@RequestParam("file") MultipartFile file) {
        CheckUtil.checkExcelFile(file);
        ExcelReader excelReader = new ExcelReader(CustomFileUtil.convert2InputStream(file));
        List<Vendor> vendorList = excelReader.parse(Vendor.class);
        List<Product> productList = excelReader.parse(Product.class);
        HashMap<String, List<?>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("供应商", vendorList);
        stringListHashMap.put("产品", productList);
        return ResponseBean.getOkAndData(stringListHashMap);
    }
}
