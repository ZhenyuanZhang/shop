package org.nj.zzy.customer.domain.customers;

import org.nj.zzy.common.domain.PageBean;

import lombok.Data;

@Data
public class CustomerQueryCond extends Customer {

    private Boolean nameFuzzyQuery = false;

    private Boolean addressFuzzyQuery = false;

    private Boolean cityFuzzyQuery = false;

    private Boolean stateFuzzyQuery = false;

    private Boolean countryFuzzyQuery = false;

    private PageBean page;
}
