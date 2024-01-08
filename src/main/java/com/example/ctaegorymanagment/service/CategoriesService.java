package com.example.ctaegorymanagment.service;

import com.example.ctaegorymanagment.dto.CategoriesDto;
import com.example.ctaegorymanagment.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {



    CategoriesDto createSubcategory(CategoriesDto categoryDto);

    List<CategoriesDto> getAllSubcategories();


    Optional<CategoriesDto> getCategoryById(int id);

    CategoriesDto updateCategory(int id, CategoriesDto updatedSubcategory);

    boolean deleteCategory(int id);
}
