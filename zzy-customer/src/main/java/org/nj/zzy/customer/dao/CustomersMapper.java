package org.nj.zzy.customer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nj.zzy.customer.domain.customers.Customer;
import org.nj.zzy.customer.domain.customers.CustomerQueryCond;

@Mapper
public interface CustomersMapper {
    int insertSelective(Customer product);

    int updateByPrimaryKeySelective(Customer product);

    int deleteByPrimaryKey(@Param("id") Integer id);

    Customer selectByPrimaryKey(@Param("id") Integer id);

    List<Customer> selectAll(CustomerQueryCond queryCond);

    long countAll(CustomerQueryCond queryCond);
}
