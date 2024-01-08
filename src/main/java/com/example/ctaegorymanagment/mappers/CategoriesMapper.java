package com.example.ctaegorymanagment.mappers;

import com.example.ctaegorymanagment.dto.CategoriesDto;
import com.example.ctaegorymanagment.model.Categories;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {


    CategoriesDto toCategoriesDto(Categories categories);
    //List<CategoriesDto> toCategoriesDtoList(List<Categories> categories);

    Categories toCategories(CategoriesDto categoriesDto);

}
