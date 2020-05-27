package org.nj.zzy.product.controller.products;

import org.nj.zzy.common.http.ResponseBean;
import org.nj.zzy.common.domain.GetListWrapper;
import org.nj.zzy.product.domain.products.Product;
import org.nj.zzy.product.domain.products.ProductQueryCond;
import org.nj.zzy.product.service.ProductsServiceMysqlImpl;
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
@RequestMapping("/products/product")
@Slf4j
public class ProductsController {
    private ProductsServiceMysqlImpl productsServiceMysqlImpl;

    public ProductsController(@Autowired ProductsServiceMysqlImpl productsServiceMysqlImpl) {
        this.productsServiceMysqlImpl = productsServiceMysqlImpl;
    }

    @PostMapping()
    public ResponseBean<?> insert(@RequestBody Product product) {
        log.info("[ProductsController] create product: {}", product);
        productsServiceMysqlImpl.create(product);
        return ResponseBean.getOK();
    }

    @PutMapping()
    public ResponseBean<?> update(@RequestBody Product product) {
        log.info("[ProductsController] update product: {}", product);
        productsServiceMysqlImpl.update(product);
        return ResponseBean.getOK();
    }

    @DeleteMapping("/{id}")
    public ResponseBean<?> deleteProducts(@PathVariable String id) {
        log.info("[ProductsController] delete product: {}", id);
        productsServiceMysqlImpl.delete(id);
        return ResponseBean.getOK();
    }

    @GetMapping()
    public ResponseBean<GetListWrapper<?>> selectProducts(ProductQueryCond queryCond) {
        log.info("[ProductsController] get product: {}", queryCond);
        ResponseBean<GetListWrapper<?>> response = ResponseBean.getOK();
        response.setData(productsServiceMysqlImpl.selectAll(queryCond));
        return response;
    }

}
