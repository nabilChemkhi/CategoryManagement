package com.example.ctaegorymanagment.dto;

import com.example.ctaegorymanagment.model.Categories;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubcategoryDto {

    private int id;
    private String name;
    private String image;
    private Categories category;
}
