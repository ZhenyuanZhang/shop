package org.nj.zzy.product.service;

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

@Service
public class ProductsServiceMysqlImpl {

    private ProductsMapper productsMapper;

    @Autowired(required = false)
    public ProductsServiceMysqlImpl(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }

    @Transactional
    public void create(Product product) {
        int isSuccess = productsMapper.insertSelective(product);
        CheckUtil.checkBusinessException(isSuccess != 1, "create product failed");
    }

    @Transactional
    public void update(Product product) {
        int isSuccess = productsMapper.updateByPrimaryKeySelective(product);
        CheckUtil.checkBusinessException(isSuccess != 1, "update product failed");
    }

    @Transactional
    public void delete(String id) {
        int isSuccess = productsMapper.deleteByPrimaryKey(id);
        CheckUtil.checkBusinessException(isSuccess != 1, "delete product failed");
    }

    public GetListWrapper<?> selectAll(ProductQueryCond vendorsQueryCond) {
        Boolean withVendor = vendorsQueryCond.getWithVendor();
        String id = vendorsQueryCond.getUuid();

        if (id != null) {
            long count = productsMapper.countAll(vendorsQueryCond);
            vendorsQueryCond.getPage().setCount(count);
        }

        if (withVendor) {
            GetListWrapper<Product> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                Product product = productsMapper.selectByPrimaryKey(id);
                getListWrapper.setGetVoList(Lists.newArrayList(product));
            } else {
                List<Product> vendors = productsMapper.selectAll(vendorsQueryCond);
                getListWrapper.setGetVoList(vendors);
                getListWrapper.setPage(vendorsQueryCond.getPage());
            }
            return getListWrapper;
        } else {
            GetListWrapper<ProductGetVo> getListWrapper = new GetListWrapper<>();
            if (id != null) {
                ProductGetVo productGetVo = productsMapper.selectByPrimaryKeyWithProducts(id);
                getListWrapper.setGetVoList(Lists.newArrayList(productGetVo));
            } else {
                List<ProductGetVo> productGetVos = productsMapper.selectAllWithProducts(vendorsQueryCond);
                getListWrapper.setGetVoList(productGetVos);
                getListWrapper.setPage(vendorsQueryCond.getPage());
            }
            return getListWrapper;
        }
    }
}
