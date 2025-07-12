package com.pratham.blogapp.Service.Impl;

import com.pratham.blogapp.Entity.Category;
import com.pratham.blogapp.Exceptions.ResourceNotFoundException;
import com.pratham.blogapp.Payloads.CategoryDto;
import com.pratham.blogapp.Repository.CategoryRepo;
import com.pratham.blogapp.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
   public ModelMapper modelMapper;




    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category cat = modelMapper.map(categoryDto,Category.class);
        Category add = categoryRepo.save(cat);

        return modelMapper.map(add,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

       Category update = categoryRepo.save(cat);

        return modelMapper.map(update,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
         categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));

        return modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category>categories = categoryRepo.findAll();
        List<CategoryDto> categoryDto = categories.stream().map(c->modelMapper.map(c,CategoryDto.class)).collect(Collectors.toList());

        return categoryDto;
    }
}
