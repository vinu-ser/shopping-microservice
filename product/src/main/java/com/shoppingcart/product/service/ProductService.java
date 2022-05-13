package com.shoppingcart.product.service;

import com.shoppingcart.product.dto.ProductDTO;
import com.shoppingcart.product.dto.ProductSearchResponse;
import com.shoppingcart.product.entity.Product;
import com.shoppingcart.product.exception.ResourceNotFoundException;
import com.shoppingcart.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public void addProduct(@Valid ProductDTO productDTO) {
        Product product =productMapper.toEntity(productDTO);

      productRepository.save(product);

    }

   /* public List<Product> listAllProducts() {

        return  productRepository.findAll();
    }*/

   /* public List<Product> productCategoryList(String category) {

        List<Product> productsByCategory = productRepository.findByCategory(category);
        if (productsByCategory.isEmpty()) {
            throw new ProductNotFoundException("No product found for the category-" + category);
        }
        return productsByCategory;
    }*/

    public Product productById(Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for id - " + id));

    }

    public void addProductImage(MultipartFile file,Integer id) {
        final String PRODUCT_IMAGE_DIR = System.getProperty("user.home")+"/"+"product-images";
        try {
            if(!file.isEmpty()) {
                File dirPath = new File(PRODUCT_IMAGE_DIR+"/"+id+"/"+file.getOriginalFilename());
                if (!dirPath.exists()) {
                    dirPath.mkdirs();
                }

                file.transferTo(dirPath);
                Product product =productRepository.findById(id)  .orElseThrow(() -> new ResourceNotFoundException("Product not found for id - " + id));
                product.setImageURL(dirPath.getAbsolutePath());
                productRepository.save(product);
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ProductSearchResponse search(String phrase, List<Integer> brands, Pageable pageable) {
        Page<Product> productList = null;
        ProductSearchResponse result = new ProductSearchResponse();

        productList = productRepository.findAll(ProductSpecification.getJobSpec(phrase,brands),pageable);

        result.setProductList(productList.getContent().stream().map(productMapper::toListDTO).collect(toList()));
        result.setCurrentPage(pageable.getPageNumber() + 1);

        result.setTotalPages(productList.getTotalPages());
        result.setTotalResults(productList.getTotalElements());

        return result;

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
