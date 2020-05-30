package org.nj.zzy.common.aop.operationlog;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper {

    void insertOperationLog(OperationLogDO operationLog);

    void updateOperationLog(OperationLogDO operationLog);

    void deleteOperationLog(Date date);
}
