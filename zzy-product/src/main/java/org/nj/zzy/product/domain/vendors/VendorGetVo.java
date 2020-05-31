package org.nj.zzy.product.domain.vendors;

import java.util.List;

import org.nj.zzy.product.domain.products.Product;

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
public class VendorGetVo extends Vendor {
    private static final long serialVersionUID = 9095306931447400357L;
    private List<Product> productList;
}
