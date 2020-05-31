package org.nj.zzy.common.aop.operationlog;

import java.util.Date;

import lombok.Data;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Data
public class OperationLogDO {
    private Integer id;

    private String metaData;

    private String businessType;

    private String operationType;

    private String method;

    private String args;

    private String result;

    private String errorMsg;

    private String operator;

    private Integer timeConsuming;

    private Date operationTime;
}
