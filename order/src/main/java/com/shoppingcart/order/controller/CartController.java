package com.shoppingcart.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingcart.order.dto.CartRequest;
import com.shoppingcart.order.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;



    @PostMapping("/cart")
    ResponseEntity<String> addProduct(@RequestBody @Valid CartRequest cartRequest) throws JsonProcessingException {

        cartService.addtoCart(cartRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("Added to cart");
    }
       @GetMapping("/cart/{userId}")
       List<CartRequest> productCategoryList(@PathVariable String userId) throws JsonProcessingException {
        return cartService.getCart(userId);
    }

   /* @GetMapping("/product/{id}")
    Product productById(@PathVariable Integer id) {
        return productService.productById(id);
    }
    @PostMapping("/product/image/{id}")
    public ResponseEntity<String> productFileUpload(@RequestParam("file") MultipartFile file,
                                                    @PathVariable Integer id
                                  ) {
        productService.addProductImage(file,id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product Added");
    }
    @GetMapping("/product/search")
    public ResponseEntity<ResponseDTO<ProductSearchResponse>>
          productFileUpload(@RequestParam(value = "phrase", required = false) String phrase,
                   @RequestParam(value = "brand", required = false) List<Integer> brands,
                  @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        ResponseDTO<ProductSearchResponse> res = new ResponseDTO<>();
        Pageable pageable = PageRequest.of(page - 1, 10);

       ProductSearchResponse result= productService.search(phrase,brands,pageable);
        res.setMessage("Products Found");
        res.setData(result);
        return new ResponseEntity<ResponseDTO<ProductSearchResponse>>(res, HttpStatus.OK);
    }
*/
/*

    @GetMapping("/products")
    List<Product> productList() {
        return productService.listAllProducts();
    }
*/

   /* @GetMapping("/productList/{category}")
    List<Product> productCategoryList( @PathVariable String category) {
        return productService.productCategoryList(category);
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
