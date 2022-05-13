package com.shoppingcart.product.service;

import com.shoppingcart.product.dto.ProductDTO;
import com.shoppingcart.product.dto.ProductListDTO;
import com.shoppingcart.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "categoryId", target = "category.categoryId")
    @Mapping(source = "brandId", target = "brand.brandId")
    Product toEntity(ProductDTO productDTO);

    ProductListDTO toListDTO(Product product);

}
