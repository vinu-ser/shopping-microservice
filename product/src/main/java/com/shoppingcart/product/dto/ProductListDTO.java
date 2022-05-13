package com.shoppingcart.product.dto;

import com.shoppingcart.product.entity.Brand;
import com.shoppingcart.product.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductListDTO {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String imageURL;
    private String colour;
    private String gender;
    private String description;
    private Category category;
    private Brand brand;
}
