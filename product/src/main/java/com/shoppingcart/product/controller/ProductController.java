package com.shoppingcart.product.controller;

import com.shoppingcart.product.entity.Product;
import com.shoppingcart.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final  ProductService productService;



    @PostMapping("/product")
    ResponseEntity<String> addProduct( @RequestBody @Valid Product product) {

         productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body("Product Added");
    }

    @GetMapping("/products")
    List<Product> productList() {
        return productService.listAllProducts();
    }

   /* @GetMapping("/productList/{category}")
    List<Product> productCategoryList( @PathVariable String category) {
        return productService.productCategoryList(category);
    }

    @GetMapping("/product/{id}")
    Product productById(@PathVariable String id) {
        return productService.productById(id);
    }

    @PutMapping("/productUpdate")
    ProductResponse updateProduct(@RequestBody Product product) {

        return productService.updateProduct(product);
    }

    @DeleteMapping("/product/{id}")
    ProductResponse deleteProductById(@PathVariable String id) {
        return productService.deleteProductById(id);
    }*/

}
