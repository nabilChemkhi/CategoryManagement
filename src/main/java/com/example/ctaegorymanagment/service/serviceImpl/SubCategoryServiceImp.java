package com.example.ctaegorymanagment.service.serviceImpl;

import com.example.ctaegorymanagment.model.Categories;
import com.example.ctaegorymanagment.model.Subcategory;
import com.example.ctaegorymanagment.repository.CategoriesRepository;
import com.example.ctaegorymanagment.repository.SubCategoryRepository;
import com.example.ctaegorymanagment.service.SubCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class SubCategoryServiceImp implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoriesRepository categoryRepository;


    @Override
    public Subcategory createSubcategory(Subcategory subcategory) {

        Categories category = categoryRepository.findById(subcategory.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + subcategory.getCategory().getId() + " not found"));

        subcategory.setCategory(category);

        String imageUrl = "/images/" + subcategory.getImage();
        subcategory.setImage(imageUrl);

        return subCategoryRepository.save(subcategory);
    }

    @Override
    public List<Subcategory> getAllSubcategories() {

        return subCategoryRepository.findAll();
    }

    @Override
    public Optional<Subcategory> getSubcategoryById(int id) {

        return subCategoryRepository.findById(id);
    }

    @Override
    public Subcategory updateSubcategory(int id, Subcategory updatedSubcategory) {
        Subcategory existingSubcategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subcategory with ID " + id + " not found"));

        updatedSubcategory.setId(id);
        updatedSubcategory.setCategory(existingSubcategory.getCategory());

        return subCategoryRepository.save(updatedSubcategory);
    }


    @Override
    public boolean deleteSubcategory(int id) {
        if (subCategoryRepository.existsById(id)) {
            subCategoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Subcategory> getSubcategoriesByCategoryId(int categoryId) {

            if (!categoryRepository.existsById(categoryId)) {
                throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
            }
            return subCategoryRepository.findByCategoryId(categoryId);
        }
    


}
