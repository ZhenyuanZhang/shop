package org.nj.zzy.product.service;

import java.util.Collections;
import java.util.List;

import org.nj.zzy.common.validate.util.CheckUtil;
import org.nj.zzy.product.dao.ProductsMapper;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.product.domain.products.Product;
import org.nj.zzy.product.domain.products.ProductGetVo;
import org.nj.zzy.product.domain.products.ProductQueryCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Service
public class ProductsServiceMysqlImpl {

    private ProductsMapper productsMapper;

    @Autowired(required = false)
    public ProductsServiceMysqlImpl(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    /**
     * 创建产品业务实现
     *
     * @param product 产品业务模型
     */
    @Transactional
    public void create(Product product) {
        int isSuccess = productsMapper.insertSelective(product);
        CheckUtil.checkBusinessException(isSuccess != 1, "create product failed");
    }

    /**
     * 更新产品业务实现
     *
     * @param product 产品业务模型
     */
    @Transactional
    public void update(Product product) {
        int isSuccess = productsMapper.updateByPrimaryKeySelective(product);
        CheckUtil.checkBusinessException(isSuccess != 1, "update product failed");
    }

    /**
     * 删除产品业务实现
     *
     * @param uuid 产品业务标识
     */
    @Transactional
    public void delete(String uuid) {
        int isSuccess = productsMapper.deleteByPrimaryKey(uuid);
        CheckUtil.checkBusinessException(isSuccess != 1, "delete product failed");
    }

    /**
     * 查询产品业务实现
     *
     * @param productQueryCond 产品业务查询条件
     * @return GetListWrapper 产品业务数据、分页数据
     */
    public GetListWrapper<?> selectAll(ProductQueryCond productQueryCond) {
        Boolean withVendor = productQueryCond.getWithVendor();
        String id = productQueryCond.getUuid();

        if (id == null) {
            long count = productsMapper.countAll(productQueryCond);
            productQueryCond.getPage().setCount(count);
        }

        if (withVendor) {
            GetListWrapper<ProductGetVo> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                ProductGetVo productGetVo = productsMapper.selectByPrimaryKeyWithVendors(id);
                getListWrapper.setGetVoList(Collections.singletonList(productGetVo));
            } else {
                List<ProductGetVo> productGetVos = productsMapper.selectAllWithVendors(productQueryCond);
                getListWrapper.setGetVoList(productGetVos);
                getListWrapper.setPage(productQueryCond.getPage());
            }
            return getListWrapper;
        } else {
            GetListWrapper<Product> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                Product product = productsMapper.selectByPrimaryKey(id);
                getListWrapper.setGetVoList(Collections.singletonList(product));
            } else {
                List<Product> vendors = productsMapper.selectAll(productQueryCond);
                getListWrapper.setGetVoList(vendors);
                getListWrapper.setPage(productQueryCond.getPage());
            }
            return getListWrapper;
        }
    }
}
