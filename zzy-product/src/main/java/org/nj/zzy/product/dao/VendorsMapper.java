package org.nj.zzy.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nj.zzy.product.domain.vendors.Vendor;
import org.nj.zzy.product.domain.vendors.VendorGetVo;
import org.nj.zzy.product.domain.vendors.VendorQueryCond;

@Mapper
public interface VendorsMapper {
    int insertSelective(Vendor vendor);

    int updateByPrimaryKeySelective(Vendor vendor);

    int deleteByPrimaryKey(@Param("id") Integer id);

    Vendor selectByPrimaryKey(@Param("id") Integer id);

    List<Vendor> selectAll(VendorQueryCond queryCond);

    VendorGetVo selectByPrimaryKeyWithProducts(@Param("id") Integer id);

    List<VendorGetVo> selectAllWithProducts(VendorQueryCond queryCond);

    long countAll(VendorQueryCond queryCond);
}
