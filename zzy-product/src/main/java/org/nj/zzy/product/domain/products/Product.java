package org.nj.zzy.product.domain.products;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Document
@Data
@ToString
public class Product implements Serializable {
    private static final long serialVersionUID = 8765558675227202089L;

    private String uuid;

    private String name;

    private BigDecimal price;

    private String desc;

    private Integer vendorId;
}
