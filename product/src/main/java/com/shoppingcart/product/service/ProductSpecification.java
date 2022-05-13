package com.shoppingcart.product.service;

import com.shoppingcart.product.entity.Brand;
import com.shoppingcart.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> getJobSpec(String phrase, List<Integer> brands
                                                    )
    {
        return new Specification<Product>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Join<Product, Brand> brandJoin = root.join("brand", JoinType.INNER);
                List<Predicate> predicates  = new ArrayList<>();
                if(phrase!=null){
                Predicate brandNamePredicate = builder.like(brandJoin.get("brandName").as(String.class),"%"+phrase+"%");

                predicates.add(builder.or(brandNamePredicate));
                 }
                if(brands != null)
                {
                    Predicate pedic8 = builder.or(brandJoin.get("brandId").isNull(),builder.in(brandJoin.get("brandId")).value(brands));
                    predicates.add(builder.and(pedic8));
                }
             //   query.orderBy(builder.desc(builder.selectCase().when(builder.equal(jobRoleJoin.get("jobRoleId").as(Integer.class), Integer.valueOf(sortJobRole)), 1).otherwise(0)), builder.desc(root.get("addedDate")));

                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };

    }
}
