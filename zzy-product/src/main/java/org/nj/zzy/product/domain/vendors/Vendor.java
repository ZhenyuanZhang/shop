package org.nj.zzy.product.domain.vendors;

import java.io.Serializable;

import org.nj.zzy.common.util.excel.ExcelColumn;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document
@Data
@ToString
public class Vendor implements Serializable {

    private static final long serialVersionUID = 2624154348761929410L;

    @Id
    @ExcelColumn(zh_CN = "供应商ID", en_US = "Vendor ID")
    private Integer id;

    @ExcelColumn(zh_CN = "供应商名称", en_US = "Vendor Name")
    private String name;

    @ExcelColumn(zh_CN = "供应商地址", en_US = "Vendor Address")
    private String address;

    @ExcelColumn(zh_CN = "供应商城市", en_US = "Vendor City")
    private String city;

    @ExcelColumn(zh_CN = "供应商省份", en_US = "Vendor State")
    private String state;

    @ExcelColumn(zh_CN = "供应商邮政编码", en_US = "Vendor Zip")
    private String zip;

    @ExcelColumn(zh_CN = "供应商国家", en_US = "Vendor Country")
    private String country;
}
