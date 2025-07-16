package com.pratham.blogapp.Controllers;


import com.pratham.blogapp.Payloads.ApiResponse;
import com.pratham.blogapp.Payloads.CategoryDto;
import com.pratham.blogapp.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
   public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
    CategoryDto categoryDto1 =    categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(categoryDto1, HttpStatus.CREATED);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto,@PathVariable int categoryId) {
        CategoryDto updated =    categoryService.updateCategory(categoryDto,categoryId);

        return new ResponseEntity<CategoryDto>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable int categoryId) {
          categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is Deleted Successfully",true), HttpStatus.OK);
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable int categoryId) {
        CategoryDto category =categoryService.getCategory(categoryId);

        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
       List <CategoryDto> allcategory =categoryService.getAllCategories();

        return  ResponseEntity.ok(allcategory);
    }




}
