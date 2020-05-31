package org.nj.zzy.customer.service;

import java.util.List;

import org.nj.zzy.customer.dao.CustomersMapper;
import org.nj.zzy.customer.domain.customers.Customer;
import org.nj.zzy.customer.domain.customers.CustomerQueryCond;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.common.validate.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Service
public class CustomersServiceMysqlImpl {

    private CustomersMapper customersMapper;

    @Autowired(required = false)
    public CustomersServiceMysqlImpl(CustomersMapper customersMapper) {
        this.customersMapper = customersMapper;
    }

    /**
     * 创建顾客信息业务实现
     *
     * @param customer 顾客信息业务模型
     */
    @Transactional
    public void create(Customer customer) {
        int isSuccess = customersMapper.insertSelective(customer);
        CheckUtil.checkBusinessException(isSuccess != 1, "create customer failed");
    }

    /**
     * 更新顾客信息业务实现
     *
     * @param customer 顾客信息业务模型
     */
    @Transactional
    public void update(Customer customer) {
        int isSuccess = customersMapper.updateByPrimaryKeySelective(customer);
        CheckUtil.checkBusinessException(isSuccess != 1, "update customer failed");
    }

    /**
     * 删除顾客信息业务实现
     *
     * @param id 顾客信息业务模型
     */
    @Transactional
    public void delete(Integer id) {
        int isSuccess = customersMapper.deleteByPrimaryKey(id);
        CheckUtil.checkBusinessException(isSuccess != 1, "delete customer failed");
    }

    public GetListWrapper<?> selectAll(CustomerQueryCond customersQueryCond) {
        Integer id = customersQueryCond.getId();
        GetListWrapper<Customer> getListWrapper = new GetListWrapper<>();
        if (id != null) {
            Customer customerGetVo = customersMapper.selectByPrimaryKey(id);
            getListWrapper.setGetVoList(Lists.newArrayList(customerGetVo));

        } else {
            long count = customersMapper.countAll(customersQueryCond);
            customersQueryCond.getPage().setCount(count);
            List<Customer> customerGetVos = customersMapper.selectAll(customersQueryCond);
            getListWrapper.setGetVoList(customerGetVos);
            getListWrapper.setPage(customersQueryCond.getPage());
        }
        return getListWrapper;
    }
}
