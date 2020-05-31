package org.nj.zzy.product.domain.vendors;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Document
@Data
@ToString
public class Vendor implements Serializable {

    private static final long serialVersionUID = 2624154348761929410L;

    @Id
    private Integer id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String country;
}
