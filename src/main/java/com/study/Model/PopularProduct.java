package com.study.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PopularProduct {
    private String cod;
    private String name;
    private Double price;
    private Integer quantity;
    private String country;
    private Integer minimAge;
    private Category category;
    private Long sales;
}