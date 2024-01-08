package com.example.ctaegorymanagment.EndPoints;
import com.example.ctaegorymanagment.dto.CategoriesDto;
import com.example.ctaegorymanagment.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories")
public class CategoriesRestController {

    private final CategoriesService categoriesService;




        @Operation(
                description = "Create a new Category"
        )
        @PostMapping("/create")
        public ResponseEntity<CategoriesDto> createCategory(@RequestBody CategoriesDto categoryDto) {
            CategoriesDto createdCategory = categoriesService.createSubcategory(categoryDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        }

        @Operation(
                description = "Get list of all categories"
        )
        @GetMapping("/getAllCategories")
        public List<CategoriesDto> getAllCategories() {
            return categoriesService.getAllSubcategories();
        }


        @Operation(
                description = "Get Category by a given Id"
        )
        @GetMapping("/getById/{id}")
        public ResponseEntity<CategoriesDto> getCategoriesById(@PathVariable int id) {
            return   categoriesService.getCategoryById(id)
                    .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @Operation(
                description = "Update a Category"
        )
        @PutMapping("/update/{id}")
        public ResponseEntity<CategoriesDto> updateSubcategory(@PathVariable int id, @RequestBody CategoriesDto updatedSubcategory) {
            CategoriesDto category = categoriesService.updateCategory(id, updatedSubcategory);
            return (category != null) ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
        }

        @Operation(
                description = "Delete a Category by given Id"
        )
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
            boolean deleted = categoriesService.deleteCategory(id);
            return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }

}
