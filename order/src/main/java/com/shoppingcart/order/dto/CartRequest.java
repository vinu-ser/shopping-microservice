package com.shoppingcart.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartRequest {
    private Integer productId;
    private BigDecimal price;
    private int quantity;
    private String size;

}
