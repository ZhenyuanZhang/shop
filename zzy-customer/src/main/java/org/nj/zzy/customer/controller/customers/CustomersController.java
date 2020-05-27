package org.nj.zzy.customer.controller.customers;

import org.nj.zzy.customer.domain.customers.Customer;
import org.nj.zzy.customer.domain.customers.CustomerQueryCond;
import org.nj.zzy.customer.service.CustomersServiceMysqlImpl;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.common.http.ResponseBean;
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
@RequestMapping("/customers/customer")
@Slf4j
public class CustomersController {
    private CustomersServiceMysqlImpl customersServiceMysqlImpl;

    public CustomersController(@Autowired CustomersServiceMysqlImpl customersServiceMysqlImpl) {
        this.customersServiceMysqlImpl = customersServiceMysqlImpl;
    }

    @PostMapping()
    public ResponseBean<?> insert(@RequestBody Customer customer) {
        log.info("[CustomersController] create customer: {}", customer);
        customersServiceMysqlImpl.create(customer);
        return ResponseBean.getOK();
    }

    @PutMapping()
    public ResponseBean<?> update(@RequestBody Customer customer) {
        log.info("[CustomersController] update customer: {}", customer);
        customersServiceMysqlImpl.update(customer);
        return ResponseBean.getOK();
    }

    @DeleteMapping("/{id}")
    public ResponseBean<?> deleteCustomers(@PathVariable Integer id) {
        log.info("[CustomersController] delete customer: {}", id);
        customersServiceMysqlImpl.delete(id);
        return ResponseBean.getOK();
    }

    @GetMapping()
    public ResponseBean<GetListWrapper<?>> selectCustomers(CustomerQueryCond queryCond) {
        log.info("[CustomersController] get customer: {}", queryCond);
        ResponseBean<GetListWrapper<?>> response = ResponseBean.getOK();
        response.setData(customersServiceMysqlImpl.selectAll(queryCond));
        return response;
    }

}
