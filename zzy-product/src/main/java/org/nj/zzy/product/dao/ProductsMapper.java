package org.nj.zzy.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nj.zzy.product.domain.products.Product;
import org.nj.zzy.product.domain.products.ProductGetVo;
import org.nj.zzy.product.domain.products.ProductQueryCond;

@Mapper
public interface ProductsMapper {
    int insertSelective(Product product);

    int updateByPrimaryKeySelective(Product product);

    int deleteByPrimaryKey(@Param("uuid") String uuid);

    Product selectByPrimaryKey(@Param("uuid") String uuid);

    List<Product> selectAll(ProductQueryCond queryCond);

    ProductGetVo selectByPrimaryKeyWithProducts(@Param("uuid") String uuid);

    List<ProductGetVo> selectAllWithProducts(ProductQueryCond queryCond);

    long countAll(ProductQueryCond queryCond);
}
