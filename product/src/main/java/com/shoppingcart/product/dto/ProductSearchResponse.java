package com.shoppingcart.product.dto;

import com.shoppingcart.product.entity.Brand;
import com.shoppingcart.product.entity.Category;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductSearchResponse {

    private int currentPage;
    private int totalPages;
    private long totalResults;
    private List<ProductListDTO> productList;



}
