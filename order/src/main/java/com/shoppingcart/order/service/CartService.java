package com.shoppingcart.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.order.dto.CartRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CartService {

private final ObjectMapper objectMapper;

    private final StringRedisTemplate stringRedisTemplate;


    public void addtoCart(@Valid CartRequest cartRequest) throws JsonProcessingException {
        List<CartRequest> cartRequests = new ArrayList<>();
        if(stringRedisTemplate.hasKey("1")){
            String cartCache = stringRedisTemplate.opsForValue().get("1");
            ObjectMapper objectMapper = new ObjectMapper();
            CartRequest[] pojos = objectMapper.readValue(cartCache, CartRequest[].class);
            cartRequests = new ArrayList<>(Arrays.asList(pojos));
        }
        cartRequests.add(cartRequest);
        String cartString = objectMapper.writeValueAsString(cartRequests);

        stringRedisTemplate.opsForValue().set("1", cartString);


    }

    public List<CartRequest> getCart(String userId) throws JsonProcessingException {
        List<CartRequest> cartRequests = new ArrayList<>();
        if(stringRedisTemplate.hasKey(userId)){
            String cartCache = stringRedisTemplate.opsForValue().get(userId);
            ObjectMapper objectMapper = new ObjectMapper();
            CartRequest[] pojos = objectMapper.readValue(cartCache, CartRequest[].class);
            cartRequests = new ArrayList<>(Arrays.asList(pojos));
        }
        return  cartRequests;
    }
}
