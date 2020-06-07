package org.nj.zzy.common.util.excel;

import java.util.Map;

/**
 * @author Zhenyuan Zhang
 * @time 2020/6/5 21:42
 */
public interface ExcelSheetName {

    Map<String, Class> getChineseSheetName();

    Map<String, Class> getEnglishSheetName();
}
