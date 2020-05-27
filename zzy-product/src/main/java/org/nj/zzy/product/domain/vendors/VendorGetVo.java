package org.nj.zzy.product.domain.vendors;

import java.util.List;

import org.nj.zzy.product.domain.products.Product;

import lombok.Data;

@Data
public class VendorGetVo extends Vendor {
    private static final long serialVersionUID = 9095306931447400357L;
    private List<Product> productList;
}
