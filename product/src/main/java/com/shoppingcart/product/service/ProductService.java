package com.shoppingcart.product.service;

import com.shoppingcart.product.entity.Product;
import com.shoppingcart.product.exception.ResourceNotFoundException;
import com.shoppingcart.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;


    public void addProduct(Product product) {

      productRepository.save(product);

    }

    public List<Product> listAllProducts() {

        return  productRepository.findAll();
    }

   /* public List<Product> productCategoryList(String category) {

        List<Product> productsByCategory = productRepository.findByCategory(category);
        if (productsByCategory.isEmpty()) {
            throw new ProductNotFoundException("No product found for the category-" + category);
        }
        return productsByCategory;
    }*/

    public Product productById(String id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id - " + id));

    }

 /*   public ProductResponse updateProduct(Product product) {

        Optional<Product> prod = productRepository.findById(product.getId());
        if (!prod.isPresent()) {
            return new ProductResponse("FAILED", "Product to be updated not found in the system");
        }

        Product updatedProduct = productRepository.save(product);

        return new ProductResponse("SUCCESS", "Product Updated - " + updatedProduct.getName());
    }

    public ProductResponse deleteProductById(String id) {
        Optional<Product> prod = productRepository.findById(id);
        if (!prod.isPresent()) {
            return new ProductResponse("FAILED", "Product to be deleted not found in the system");
        }

        productRepository.deleteById(id);

        return new ProductResponse("SUCCESS", "Product Deleted");
    }*/
}
