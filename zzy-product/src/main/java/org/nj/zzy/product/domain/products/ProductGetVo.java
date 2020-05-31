package org.nj.zzy.product.domain.products;

import org.nj.zzy.product.domain.vendors.Vendor;

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
public class ProductGetVo extends Product {
    private static final long serialVersionUID = 3350020838734965976L;
    private Vendor vendor;
}


