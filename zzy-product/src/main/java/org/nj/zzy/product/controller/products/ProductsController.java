package org.nj.zzy.product.controller.products;

import org.nj.zzy.common.aop.operationlog.OperationLog;
import org.nj.zzy.common.constant.BusinessType;
import org.nj.zzy.common.constant.CommonErrorConstant;
import org.nj.zzy.common.constant.OperationType;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.common.http.ResponseBean;
import org.nj.zzy.common.validate.util.CheckUtil;
import org.nj.zzy.product.domain.products.Product;
import org.nj.zzy.product.domain.products.ProductQueryCond;
import org.nj.zzy.product.service.ProductsServiceMysqlImpl;
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
@RequestMapping("/products/product")
@Slf4j
public class ProductsController {
    private ProductsServiceMysqlImpl productsServiceMysqlImpl;

    public ProductsController(@Autowired ProductsServiceMysqlImpl productsServiceMysqlImpl) {
        this.productsServiceMysqlImpl = productsServiceMysqlImpl;
    }

    /**
     * 创建Product接口
     *
     * @param product 产品业务模型
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @PostMapping()
    @OperationLog(businessType = BusinessType.PRODUCT, operationType = OperationType.CREATE)
    public ResponseBean<?> insert(@RequestBody Product product) {
        CheckUtil.checkBadRequest(product.getUuid() != null, CommonErrorConstant.MUST_BE_EMPTY, "uuid");
        productsServiceMysqlImpl.create(product);
        return ResponseBean.getOkAndData(null);
    }

    /**
     * 更新Product接口
     *
     * @param product 产品业务模型
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @PutMapping()
    @OperationLog(businessType = BusinessType.PRODUCT, operationType = OperationType.UPDATE)
    public ResponseBean<?> update(@RequestBody Product product) {
        CheckUtil.checkBadRequest(product.getUuid() == null, CommonErrorConstant.CAN_NOT_BE_EMPTY, "uuid");
        productsServiceMysqlImpl.update(product);
        return ResponseBean.getOkAndData(null);
    }

    /**
     * 删除Product接口
     *
     * @param uuid 产品业务模型标识
     * @return ResponseBean
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @DeleteMapping("/{uuid}")
    @OperationLog(businessType = BusinessType.PRODUCT, operationType = OperationType.DELETE)
    public ResponseBean<?> deleteProducts(@PathVariable String uuid) {
        productsServiceMysqlImpl.delete(uuid);
        return ResponseBean.getOkAndData(null);
    }

    /**
     * 查询Product接口
     *
     * @param queryCond 产品业务查询条件
     * @return GetListWrapper 查询结果：业务数据、分页信息
     * @see org.nj.zzy.common.aop.operationlog.OperationAspect 记录本次接口调用的操作日志
     */
    @GetMapping()
    public ResponseBean<GetListWrapper<?>> selectProducts(ProductQueryCond queryCond) {
        GetListWrapper<?> getListWrapper = productsServiceMysqlImpl.selectAll(queryCond);
        return ResponseBean.getOkAndData(getListWrapper);
    }

}
