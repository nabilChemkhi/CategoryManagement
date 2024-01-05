package com.example.ctaegorymanagment.service.serviceImpl;

import com.example.ctaegorymanagment.model.Categories;
import com.example.ctaegorymanagment.repository.CategoriesRepository;
import com.example.ctaegorymanagment.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;



    @Override
    public Categories createSubcategory(Categories category) {

        return categoriesRepository.save(category);
    }

    @Override
    public List<Categories> getAllSubcategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Optional<Categories> getCategoryById(int id) {

        return categoriesRepository.findById(id);
    }

    @Override
    public Categories updateCategory(int id, Categories updatedCcategory) {
        if (categoriesRepository.existsById(id)) {
            updatedCcategory.setId(id);
            return categoriesRepository.save(updatedCcategory);
        }
        return null;
    }

    @Override
    public boolean deleteCategory(int id) {
        if (categoriesRepository.existsById(id)) {
            categoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
