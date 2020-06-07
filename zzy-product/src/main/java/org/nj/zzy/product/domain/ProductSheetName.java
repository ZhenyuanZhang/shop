package org.nj.zzy.product.domain;

import java.util.HashMap;
import java.util.Map;

import org.nj.zzy.common.util.excel.ExcelSheetName;
import org.nj.zzy.product.domain.products.Product;
import org.nj.zzy.product.domain.vendors.Vendor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Zhenyuan Zhang
 * @time 2020/6/5 21:48
 */
@Component
@Primary
public class ProductSheetName implements ExcelSheetName {
    @Override
    public Map<String, Class> getChineseSheetName() {
        return new HashMap<String, Class>() {
            {
                put("供应商", Vendor.class);
                put("产品", Product.class);
            }
        };
    }

    @Override
    public Map<String, Class> getEnglishSheetName() {
        return new HashMap<String, Class>() {
            {
                put("Vendor", Vendor.class);
                put("Product", Product.class);
            }
        };
    }
}
