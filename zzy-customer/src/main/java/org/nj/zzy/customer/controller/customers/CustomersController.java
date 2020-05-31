package org.nj.zzy.customer.controller.customers;

import org.nj.zzy.common.aop.operationlog.OperationLog;
import org.nj.zzy.common.constant.BusinessType;
import org.nj.zzy.common.constant.CommonErrorConstant;
import org.nj.zzy.common.constant.OperationType;
import org.nj.zzy.common.validate.util.CheckUtil;
import org.nj.zzy.customer.domain.customers.Customer;
import org.nj.zzy.customer.domain.customers.CustomerQueryCond;
import org.nj.zzy.customer.service.CustomersServiceMysqlImpl;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.common.http.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@RestController
@RequestMapping("/customers/customer")
@Slf4j
public class CustomersController {
    private CustomersServiceMysqlImpl customersServiceMysqlImpl;

    public CustomersController(@Autowired CustomersServiceMysqlImpl customersServiceMysqlImpl) {
        this.customersServiceMysqlImpl = customersServiceMysqlImpl;
    }

    /**
     * 创建Customer接口
     *
     * @param customer 顾客业务模型
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @PostMapping()
    @OperationLog(businessType = BusinessType.CUSTOMER, operationType = OperationType.CREATE)
    public ResponseBean<?> insert(@RequestBody Customer customer) {
        log.info("[CustomersController] create customer: {}", customer);
        CheckUtil.checkBadRequest(customer.getId() != null, CommonErrorConstant.MUST_BE_EMPTY, "id");
        customersServiceMysqlImpl.create(customer);
        return ResponseBean.getOK();
    }

    /**
     * 更新Customer接口
     *
     * @param customer 顾客业务模型
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @PutMapping()
    @OperationLog(businessType = BusinessType.CUSTOMER, operationType = OperationType.UPDATE)
    public ResponseBean<?> update(@RequestBody Customer customer) {
        log.info("[CustomersController] update customer: {}", customer);
        CheckUtil.checkBadRequest(customer.getId() == null, CommonErrorConstant.CAN_NOT_BE_EMPTY, "id");
        customersServiceMysqlImpl.update(customer);
        return ResponseBean.getOK();
    }

    /**
     * 删除Customer接口
     *
     * @param id 顾客业务标识
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @DeleteMapping("/{id}")
    @OperationLog(businessType = BusinessType.CUSTOMER, operationType = OperationType.DELETE)
    public ResponseBean<?> deleteCustomers(@PathVariable Integer id) {
        log.info("[CustomersController] delete customer: {}", id);
        customersServiceMysqlImpl.delete(id);
        return ResponseBean.getOK();
    }

    /**
     * 查询Customer接口
     *
     * @param queryCond 顾客业务查询条件
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @GetMapping()
    public ResponseBean<GetListWrapper<?>> selectCustomers(CustomerQueryCond queryCond) {
        log.info("[CustomersController] get customer: {}", queryCond);
        ResponseBean<GetListWrapper<?>> response = ResponseBean.getOK();
        response.setData(customersServiceMysqlImpl.selectAll(queryCond));
        return response;
    }

}
