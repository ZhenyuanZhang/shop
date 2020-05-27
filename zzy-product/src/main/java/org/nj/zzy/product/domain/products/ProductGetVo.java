package org.nj.zzy.product.domain.products;

import org.nj.zzy.product.domain.vendors.Vendor;

import lombok.Data;

@Data
public class ProductGetVo extends Product {
    private static final long serialVersionUID = 3350020838734965976L;
    private Vendor vendor;
}
