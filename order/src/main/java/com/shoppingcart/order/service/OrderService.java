package com.shoppingcart.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shoppingcart.order.dto.CartRequest;
import com.shoppingcart.order.entity.Order;
import com.shoppingcart.order.entity.OrderItems;
import com.shoppingcart.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public void placeOrder(String userId) throws JsonProcessingException {

        List<CartRequest> cartRequests =cartService.getCart(userId);
        Order order =createOrder(cartRequests);
        orderRepository.save(order);

    }
    private Order createOrder( List<CartRequest> cartRequests){
        Order order =new Order();
        BigDecimal total = cartRequests.stream()
                .map(CartRequest::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(total);
        List<OrderItems> orderItems =cartRequests.stream().
                map(this::createOrderItems).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        return order;
    }

    private OrderItems createOrderItems(CartRequest cartRequest){
        OrderItems orderItem =new OrderItems();
        orderItem.setAmount(cartRequest.getPrice());
        orderItem.setProductId(cartRequest.getProductId());
        orderItem.setQuantity(cartRequest.getQuantity());

      return orderItem;
    }
}
