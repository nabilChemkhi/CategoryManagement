package com.example.ctaegorymanagment.mappers;

import com.example.ctaegorymanagment.dto.SubcategoryDto;
import com.example.ctaegorymanagment.model.Subcategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {

    SubcategoryDto toSubcategoryDto(Subcategory subcategory);
    Subcategory toSubcategory(SubcategoryDto subcategoryDto);
}
