package com.outdoor.foodcalc.endpoint;

import com.outdoor.foodcalc.model.CategoryWithProducts;
import com.outdoor.foodcalc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST Endpoint for Product related operations
 *
 * @author Anton Borovyk
 */
@RestController
@RequestMapping("${spring.data.rest.basePath}/products")
public class ProductEndpoint {

    private ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @RequestMapping(produces = APPLICATION_JSON_VALUE)
    public List<CategoryWithProducts> allProducts() {
        return productService.getAllProducts();
    }
}
