package org.nj.zzy.product.service;

import java.util.List;

import org.nj.zzy.product.dao.VendorsMapper;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.product.domain.vendors.Vendor;
import org.nj.zzy.product.domain.vendors.VendorGetVo;
import org.nj.zzy.product.domain.vendors.VendorQueryCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.nj.zzy.common.validate.util.CheckUtil;

import com.google.common.collect.Lists;

@Service
public class VendorsServiceMysqlImpl {

    private VendorsMapper vendorsMapper;

    @Autowired(required = false)
    public VendorsServiceMysqlImpl(VendorsMapper vendorsMapper) {
        this.vendorsMapper = vendorsMapper;
    }

    @Transactional
    public void create(Vendor vendor) {
        int isSuccess = vendorsMapper.insertSelective(vendor);
        CheckUtil.checkBusinessException(isSuccess != 1, "create vendor failed");
    }

    @Transactional
    public void update(Vendor vendor) {
        int isSuccess = vendorsMapper.updateByPrimaryKeySelective(vendor);
        CheckUtil.checkBusinessException(isSuccess != 1, "update vendor failed");
    }

    @Transactional
    public void delete(Integer id) {
        int isSuccess = vendorsMapper.deleteByPrimaryKey(id);
        CheckUtil.checkBusinessException(isSuccess != 1, "delete vendor failed");
    }

    public GetListWrapper<?> selectAll(VendorQueryCond vendorsQueryCond) {
        Boolean withProducts = vendorsQueryCond.getWithProducts();
        Integer id = vendorsQueryCond.getId();

        if (id != null) {
            long count = vendorsMapper.countAll(vendorsQueryCond);
            vendorsQueryCond.getPage().setCount(count);
        }

        if (withProducts) {
            GetListWrapper<VendorGetVo> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                VendorGetVo vendorGetVo = vendorsMapper.selectByPrimaryKeyWithProducts(id);
                getListWrapper.setGetVoList(Lists.newArrayList(vendorGetVo));
            } else {
                List<VendorGetVo> vendorGetVos = vendorsMapper.selectAllWithProducts(vendorsQueryCond);
                getListWrapper.setGetVoList(vendorGetVos);
                getListWrapper.setPage(vendorsQueryCond.getPage());
            }
            return getListWrapper;
        } else {
            GetListWrapper<Vendor> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                Vendor vendor = vendorsMapper.selectByPrimaryKey(id);
                getListWrapper.setGetVoList(Lists.newArrayList(vendor));
            } else {
                List<Vendor> vendors = vendorsMapper.selectAll(vendorsQueryCond);
                getListWrapper.setGetVoList(vendors);
                getListWrapper.setPage(vendorsQueryCond.getPage());
            }
            return getListWrapper;
        }
    }
}
