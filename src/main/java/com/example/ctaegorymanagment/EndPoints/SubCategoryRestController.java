package com.example.ctaegorymanagment.EndPoints;

import com.example.ctaegorymanagment.dto.SubcategoryDto;
import com.example.ctaegorymanagment.mappers.SubcategoryMapper;
import com.example.ctaegorymanagment.model.Categories;
import com.example.ctaegorymanagment.model.Subcategory;
import com.example.ctaegorymanagment.repository.CategoriesRepository;
import com.example.ctaegorymanagment.service.SubCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "SubCategory")
public class SubCategoryRestController {

    private final SubCategoryService subCategoryService;
    private final CategoriesRepository categoryRepository;
    private final SubcategoryMapper subcategoryMapper;



    @Operation(
            description = "Create a new subCategory"
    )
    @PostMapping("/create")
    public ResponseEntity<SubcategoryDto> createSubcategory(
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
        return ResponseEntity.status(HttpStatus.CREATED).body(
            subcategoryMapper.toSubcategoryDto(createdSubcategory)
        );
    }

    @Operation(
            description = "get list of all subCategories"
    )
    @GetMapping("/getAllSubCategories")
    public List<SubcategoryDto> getAllSubcategories() {

        return subCategoryService.getAllSubcategories();
    }

    @Operation(
            description = "Get subcategory by given id"
    )
    @GetMapping("/getById/{id}")
    public ResponseEntity<SubcategoryDto> getSubcategoryById(@PathVariable int id) {
        return   subCategoryService.getSubcategoryById(id)
                .map(subcategory -> new ResponseEntity<>(subcategory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            description = "Update a given subcategory"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<SubcategoryDto> updateSubcategory(@PathVariable int id, @RequestBody SubcategoryDto updatedSubcategoryDto) {
        SubcategoryDto subcategoryDto = subCategoryService.updateSubcategory(id, updatedSubcategoryDto);
        return (subcategoryDto != null) ? ResponseEntity.ok(subcategoryDto) : ResponseEntity.notFound().build();
    }

    @Operation(
            description = "Delete a subCategory by given Id"
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable int id) {
        boolean deleted = subCategoryService.deleteSubcategory(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @Operation(
            description = "Get list of subcategories for a given category Id"
    )
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<SubcategoryDto>> getSubcategoriesByCategoryId(@PathVariable int categoryId) {
        try {
            List<Subcategory> subcategories = subCategoryService.getSubcategoriesByCategoryId(categoryId);
            return ResponseEntity.ok(subcategories
                    .stream()
                    .map(subcategoryMapper::toSubcategoryDto)
                    .toList());
        } catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }
}
