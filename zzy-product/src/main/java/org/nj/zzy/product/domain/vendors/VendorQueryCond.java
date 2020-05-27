package org.nj.zzy.product.domain.vendors;

import org.nj.zzy.common.domain.PageBean;

import lombok.Data;

@Data
public class VendorQueryCond extends Vendor {
    private Boolean nameFuzzyQuery = false;
    private Boolean addressFuzzyQuery = false;
    private Boolean cityFuzzyQuery = false;
    private Boolean stateFuzzyQuery = false;
    private Boolean countryFuzzyQuery = false;
    private Boolean withProducts = false;
    private PageBean page = null;
}
