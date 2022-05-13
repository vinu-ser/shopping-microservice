package com.shoppingcart.product.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Min(0)
    private BigDecimal price;
    private String imageURL;
    private String colour;
    private String gender;
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;
  /*  @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategoryId", nullable = false)
    private SubCategory subCategory;*/
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;

}
