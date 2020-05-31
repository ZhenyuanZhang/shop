package org.nj.zzy.customer.domain.customers;

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
public class Customer implements Serializable {
    private static final long serialVersionUID = 8765558675227202089L;

    private Integer id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zip;

    private String country;

    private String contact;

    private String email;
}
