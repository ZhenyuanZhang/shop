package org.nj.zzy.product.domain.vendors;

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
public class VendorQueryCond extends Vendor {
    private Boolean nameFuzzyQuery = false;
    private Boolean addressFuzzyQuery = false;
    private Boolean cityFuzzyQuery = false;
    private Boolean stateFuzzyQuery = false;
    private Boolean countryFuzzyQuery = false;
    private Boolean withProducts = false;
    private PageBean page = new PageBean();
}
