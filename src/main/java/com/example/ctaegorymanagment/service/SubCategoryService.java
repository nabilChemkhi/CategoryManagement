package com.example.ctaegorymanagment.service;

import com.example.ctaegorymanagment.model.Subcategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {
    Subcategory createSubcategory(Subcategory subcategory);

    List<Subcategory> getAllSubcategories();

    Optional<Subcategory> getSubcategoryById(int id);

    Subcategory updateSubcategory(int id, Subcategory updatedSubcategory);

    boolean deleteSubcategory(int id);

    List<Subcategory> getSubcategoriesByCategoryId(int categoryId);

    String saveImage(MultipartFile image);
}
