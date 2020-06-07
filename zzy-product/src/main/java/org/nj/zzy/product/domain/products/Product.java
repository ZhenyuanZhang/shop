package org.nj.zzy.product.domain.products;

import java.io.Serializable;
import java.math.BigDecimal;

import org.nj.zzy.common.util.excel.ExcelColumn;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Document
@Data
@ToString
public class Product implements Serializable {
    private static final long serialVersionUID = 8765558675227202089L;

    @Id
    @ExcelColumn(zh_CN = "产品ID", en_US = "Product UUID")
    private String uuid;

    @ExcelColumn(zh_CN = "产品名称", en_US = "Product Name")
    private String name;

    @ExcelColumn(zh_CN = "产品价格", en_US = "Product Price")
    private BigDecimal price;

    @ExcelColumn(zh_CN = "产品描述", en_US = "Product Description")
    private String desc;

    @ExcelColumn(zh_CN = "供应商ID", en_US = "Vendor ID")
    private Integer vendorId;
}
