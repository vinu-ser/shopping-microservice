package com.shoppingcart.order.entity;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Cart {
    private Integer productId;
    private BigDecimal price;
    private int quantity;
    private String size;
}
