package com.example.ctaegorymanagment.service;

import com.example.ctaegorymanagment.model.Categories;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {



    Categories createSubcategory(Categories category);

    List<Categories> getAllSubcategories();


    Optional<Categories> getCategoryById(int id);

    Categories updateCategory(int id, Categories updatedSubcategory);

    boolean deleteCategory(int id);
}
