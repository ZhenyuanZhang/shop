package org.nj.zzy.product.domain.products;

import java.math.BigDecimal;

import org.nj.zzy.common.domain.PageBean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductQueryCond extends Product {
    private Boolean nameFuzzyQuery = false;
    private Boolean descFuzzyQuery = false;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean withVendor = false;
    private PageBean page = new PageBean();
}
