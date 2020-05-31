package org.nj.zzy.product.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.common.validate.util.CheckUtil;
import org.nj.zzy.product.dao.ProductsMapper;
import org.nj.zzy.product.dao.VendorsMapper;
import org.nj.zzy.product.domain.products.Product;
import org.nj.zzy.product.domain.vendors.Vendor;
import org.nj.zzy.product.domain.vendors.VendorGetVo;
import org.nj.zzy.product.domain.vendors.VendorQueryCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Service
public class VendorsServiceMysqlImpl {

    private VendorsMapper vendorsMapper;

    private ProductsMapper productsMapper;

    @Autowired(required = false)
    public VendorsServiceMysqlImpl(VendorsMapper vendorsMapper, ProductsMapper productsMapper) {
        this.vendorsMapper = vendorsMapper;
        this.productsMapper = productsMapper;
    }

    /**
     * 创建供应商业务实现
     *
     * @param vendor
     *            供应商业务模型
     */
    @Transactional
    public void create(Vendor vendor) {
        int isSuccess = vendorsMapper.insertSelective(vendor);
        CheckUtil.checkBusinessException(isSuccess != 1, "create vendor failed");
    }

    /**
     * 更新供应商业务实现
     *
     * @param vendor
     *            供应商业务模型
     */
    @Transactional
    public void update(Vendor vendor) {
        int isSuccess = vendorsMapper.updateByPrimaryKeySelective(vendor);
        CheckUtil.checkBusinessException(isSuccess != 1, "update vendor failed");
    }

    /**
     * 删除供应商业务实现
     *
     * @param id
     *            供应商业务标识
     */
    @Transactional
    public void delete(Integer id) {
        int isSuccess = vendorsMapper.deleteByPrimaryKey(id);
        CheckUtil.checkBusinessException(isSuccess != 1, "delete vendor failed");
    }

    /**
     * 查询供应商业务实现
     *
     * @param vendorsQueryCond
     *            vendors的查询条件模型
     * @return GetListWrapper 业务数据、分页信息
     */
    public GetListWrapper<?> selectAll(VendorQueryCond vendorsQueryCond) {
        Boolean withProducts = vendorsQueryCond.getWithProducts();
        Integer id = vendorsQueryCond.getId();

        if (id == null) {
            long count = vendorsMapper.countAll(vendorsQueryCond);
            vendorsQueryCond.getPage().setCount(count);
        }

        if (withProducts) {
            GetListWrapper<VendorGetVo> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                VendorGetVo vendorGetVo = vendorsMapper.selectByPrimaryKeyWithProducts(id);
                getListWrapper.setGetVoList(Collections.singletonList(vendorGetVo));
            } else {
                List<VendorGetVo> vendorGetVos = vendorsMapper.selectAllWithProducts(vendorsQueryCond);
                List<Product> products = productsMapper
                    .selectByVendorIdList(vendorGetVos.stream().map(VendorGetVo::getId).collect(Collectors.toSet()));
                Map<Integer, List<Product>> vidProductMap =
                    products.stream().collect(Collectors.groupingBy(Product::getVendorId));
                vendorGetVos.forEach(vendorGetVo -> vendorGetVo.setProductList(vidProductMap.get(vendorGetVo.getId())));
                getListWrapper.setGetVoList(vendorGetVos);
                getListWrapper.setPage(vendorsQueryCond.getPage());
            }
            return getListWrapper;
        } else {
            GetListWrapper<Vendor> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                Vendor vendor = vendorsMapper.selectByPrimaryKey(id);
                getListWrapper.setGetVoList(Collections.singletonList(vendor));
            } else {
                List<Vendor> vendors = vendorsMapper.selectAll(vendorsQueryCond);
                getListWrapper.setGetVoList(vendors);
                getListWrapper.setPage(vendorsQueryCond.getPage());
            }
            return getListWrapper;
        }
    }
}
