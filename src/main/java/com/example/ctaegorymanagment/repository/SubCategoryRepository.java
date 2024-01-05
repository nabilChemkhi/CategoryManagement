package com.example.ctaegorymanagment.repository;

import com.example.ctaegorymanagment.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<Subcategory,Long> {
    List<Subcategory> findByCategoryId(Long categoryId);
}
