package com.example.ctaegorymanagment.service;

import com.example.ctaegorymanagment.model.Subcategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {
    Subcategory createSubcategory(Subcategory subcategory);

    List<Subcategory> getAllSubcategories();

    Optional<Subcategory> getSubcategoryById(Long id);

    Subcategory updateSubcategory(Long id, Subcategory updatedSubcategory);

    boolean deleteSubcategory(Long id);

    List<Subcategory> getSubcategoriesByCategoryId(Long categoryId);
}
