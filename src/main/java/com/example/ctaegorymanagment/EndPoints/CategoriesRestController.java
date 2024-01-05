package com.example.ctaegorymanagment.EndPoints;

import com.example.ctaegorymanagment.model.Categories;


import com.example.ctaegorymanagment.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1//categories")
public class CategoriesRestController {

    private final CategoriesService categoriesService;




    @PostMapping("/create")
    public ResponseEntity<Categories> createCategory(@RequestBody Categories category) {
        Categories createdcategory = categoriesService.createSubcategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdcategory);
    }
        @GetMapping("/getAllCategories")
        public List<Categories> getAllCategories() {
            return categoriesService.getAllSubcategories();
        }

        @GetMapping("/getById/{id}")
        public ResponseEntity<Categories> getCategoriesById(@PathVariable Long id) {
            return   categoriesService.getCategoryById(id)
                    .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<Categories> updateSubcategory(@PathVariable Long id, @RequestBody Categories updatedSubcategory) {
            Categories category = categoriesService.updateCategory(id, updatedSubcategory);
            return (category != null) ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
            boolean deleted = categoriesService.deleteCategory(id);
            return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }

}
