package org.nj.zzy.product.controller.vendors;

import org.nj.zzy.common.aop.operationlog.OperationLog;
import org.nj.zzy.common.domain.BusinessType;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.common.domain.OperationType;
import org.nj.zzy.common.http.ResponseBean;
import org.nj.zzy.common.message.CommonErrorConstant;
import org.nj.zzy.common.validate.util.CheckUtil;
import org.nj.zzy.product.domain.vendors.Vendor;
import org.nj.zzy.product.domain.vendors.VendorQueryCond;
import org.nj.zzy.product.service.VendorsServiceMysqlImpl;
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

@RestController
@RequestMapping("/vendors/vendor")
@Slf4j
public class VendorsController {
    private VendorsServiceMysqlImpl vendorsServiceMysqlImpl;

    public VendorsController(@Autowired VendorsServiceMysqlImpl vendorsServiceMysqlImpl) {
        this.vendorsServiceMysqlImpl = vendorsServiceMysqlImpl;
    }

    @PostMapping()
    @OperationLog(businessType = BusinessType.VENDOR, operationType = OperationType.CREATE)
    public ResponseBean<?> insertVendors(@RequestBody Vendor vendor) {
        log.info("[VendorsController] create vendor: {}", vendor);
        CheckUtil.checkBadRequest(vendor.getId() != null, CommonErrorConstant.MUST_BE_EMPTY, "id");
        vendorsServiceMysqlImpl.create(vendor);
        return ResponseBean.getOK();
    }

    @PutMapping()
    @OperationLog(businessType = BusinessType.VENDOR, operationType = OperationType.UPDATE)
    public ResponseBean<?> updateVendors(@RequestBody Vendor vendor) {
        log.info("[VendorsController] update vendor: {}", vendor);
        CheckUtil.checkBadRequest(vendor.getId() == null, CommonErrorConstant.CAN_NOT_BE_EMPTY, "id");
        vendorsServiceMysqlImpl.update(vendor);
        return ResponseBean.getOK();
    }

    @DeleteMapping("/{id}")
    @OperationLog(businessType = BusinessType.VENDOR, operationType = OperationType.DELETE)
    public ResponseBean<?> deleteVendors(@PathVariable Integer id) {
        log.info("[VendorsController] delete vendor: {}", id);
        vendorsServiceMysqlImpl.delete(id);
        return ResponseBean.getOK();
    }

    @GetMapping()
    public ResponseBean<GetListWrapper<?>> selectVendors(VendorQueryCond queryCond) {
        log.info("[VendorsController] get vendor: {}", queryCond);
        ResponseBean<GetListWrapper<?>> response = ResponseBean.getOK();
        response.setData(vendorsServiceMysqlImpl.selectAll(queryCond));
        return response;
    }

}
