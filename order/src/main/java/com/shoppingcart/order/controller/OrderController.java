package com.shoppingcart.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingcart.order.service.CartService;
import com.shoppingcart.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    ResponseEntity<String> placeOrder() throws JsonProcessingException {
        String userId="1";
        orderService.placeOrder(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order Placed");
    }

}
