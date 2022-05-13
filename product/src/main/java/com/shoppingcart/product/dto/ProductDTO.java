package com.shoppingcart.product.dto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class   ProductDTO {
    private Integer id;

    private String name;
    private BigDecimal price;
    private String colour;
    private String gender;
    private String description;

    private Integer categoryId;

    private Integer brandId;

}
