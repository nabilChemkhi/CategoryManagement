package com.example.ctaegorymanagment.service;

import com.example.ctaegorymanagment.dto.SubcategoryDto;
import com.example.ctaegorymanagment.model.Subcategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {
    Subcategory createSubcategory(Subcategory subcategory);

    List<SubcategoryDto> getAllSubcategories();

    Optional<SubcategoryDto> getSubcategoryById(int id);

    SubcategoryDto updateSubcategory(int id, SubcategoryDto updatedSubcategoryDto);

    boolean deleteSubcategory(int id);

    List<Subcategory> getSubcategoriesByCategoryId(int categoryId);

    String saveImage(MultipartFile image);
}
