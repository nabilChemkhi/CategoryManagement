package com.example.ctaegorymanagment.service.serviceImpl;

import com.example.ctaegorymanagment.model.Categories;
import com.example.ctaegorymanagment.model.Subcategory;
import com.example.ctaegorymanagment.repository.CategoriesRepository;
import com.example.ctaegorymanagment.repository.SubCategoryRepository;
import com.example.ctaegorymanagment.service.SubCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class SubCategoryServiceImp implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoriesRepository categoryRepository;


    @Override
    public Subcategory createSubcategory(Subcategory subcategory) {

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

    @Override
    public String saveImage(MultipartFile image) {
        String uploadDir = "E:\\ctaegoryManagment\\src\\main\\resources\\static\\images";
        String filename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(image.getOriginalFilename());

        try {
            File file = new File(uploadDir + File.separator + filename);
            image.transferTo(file);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }


}
