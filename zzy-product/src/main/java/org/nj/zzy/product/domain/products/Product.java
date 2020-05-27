package org.nj.zzy.product.domain.products;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 8765558675227202089L;

    private String uuid;

    private String name;

    private BigDecimal price;

    private String desc;

    private Integer vendorId;
}
