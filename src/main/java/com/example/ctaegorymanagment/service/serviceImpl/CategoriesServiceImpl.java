package com.example.ctaegorymanagment.service.serviceImpl;

import com.example.ctaegorymanagment.dto.CategoriesDto;
import com.example.ctaegorymanagment.mappers.CategoriesMapper;
import com.example.ctaegorymanagment.model.Categories;
import com.example.ctaegorymanagment.repository.CategoriesRepository;
import com.example.ctaegorymanagment.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;
    private final CategoriesMapper categoriesMapper;



    @Override
    public CategoriesDto createSubcategory(CategoriesDto categoryDto) {

        Categories categories = categoriesRepository.save(
                categoriesMapper.toCategories(categoryDto));

        return categoriesMapper.toCategoriesDto(categories);

    }

    @Override
    public List<CategoriesDto> getAllSubcategories() {

        return
                categoriesRepository.findAll()
                        .stream()
                        .map(categoriesMapper::toCategoriesDto)
                        .collect(Collectors.toList());

    }

    @Override
    public Optional<CategoriesDto> getCategoryById(int id) {

        return categoriesRepository.findById(id)
                .map(categoriesMapper::toCategoriesDto);


    }

    @Override
    public CategoriesDto updateCategory(int id, CategoriesDto updatedCcategory) {
        if (categoriesRepository.existsById(id)) {
            updatedCcategory.setId(id);

               Categories categories = categoriesRepository.save(
                       categoriesMapper.toCategories(updatedCcategory));

               return categoriesMapper.toCategoriesDto(categories);


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
