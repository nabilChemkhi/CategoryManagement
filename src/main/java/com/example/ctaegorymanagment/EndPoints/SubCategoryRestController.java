package com.example.ctaegorymanagment.EndPoints;

import com.example.ctaegorymanagment.model.Categories;
import com.example.ctaegorymanagment.model.Subcategory;
import com.example.ctaegorymanagment.repository.CategoriesRepository;
import com.example.ctaegorymanagment.service.SubCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/subCategory")
public class SubCategoryRestController {

    private final SubCategoryService subCategoryService;
    private final CategoriesRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<Subcategory> createSubcategory(
            @RequestPart("image") MultipartFile image,
            @RequestParam String name,
            @RequestParam int categoryId) {

        String imagePath = subCategoryService.saveImage(image);

        Subcategory subcategory = new Subcategory();
        subcategory.setImage(imagePath);
        subcategory.setName(name);
        Categories category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + subcategory.getCategory().getId() + " not found"));
        subcategory.setCategory(category);

        Subcategory createdSubcategory = subCategoryService.createSubcategory(subcategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubcategory);
    }

    @GetMapping("/getAllSubCategories")
    public List<Subcategory> getAllSubcategories() {

        return subCategoryService.getAllSubcategories();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable int id) {
        return   subCategoryService.getSubcategoryById(id)
                .map(subcategory -> new ResponseEntity<>(subcategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable int id, @RequestBody Subcategory updatedSubcategory) {
        Subcategory subcategory = subCategoryService.updateSubcategory(id, updatedSubcategory);
        return (subcategory != null) ? ResponseEntity.ok(subcategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable int id) {
        boolean deleted = subCategoryService.deleteSubcategory(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategoryId(@PathVariable int categoryId) {
        try {
            List<Subcategory> subcategories = subCategoryService.getSubcategoriesByCategoryId(categoryId);
            return ResponseEntity.ok(subcategories);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }
}
