package org.nj.zzy.product.domain.products;

import java.math.BigDecimal;

import org.nj.zzy.common.domain.PageBean;

import lombok.Data;

@Data
public class ProductQueryCond extends Product {
    private Boolean nameFuzzyQuery = false;
    private Boolean descFuzzyQuery = false;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean withVendor = false;
    private PageBean page;
}
