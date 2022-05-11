package com.hobby.blogapi.services;

import com.hobby.blogapi.entities.Category;
import com.hobby.blogapi.exceptions.ResourceNotFoundException;
import com.hobby.blogapi.payloads.CategoryDto;
import com.hobby.blogapi.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);
        Category addedCategory = categoryRepository.save(category);

        return modelMapper.map(addedCategory, CategoryDto.class);
    }


    public CategoryDto updateCategory(CategoryDto categoryDto, Long catId) {

        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", catId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);

    }

    public void deleteCategory(Long catId) {

        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", catId));
        categoryRepository.delete(category);
    }

    public CategoryDto getCategory(Long catId) {

        Category category = categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Calegory", "Category Id", catId));

        return modelMapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll()
                .forEach(categories::add);

        List<CategoryDto> categoryDtoList = categories
                .stream()
                .map((category -> modelMapper.map(category, CategoryDto.class))).collect(Collectors.toList());
        return categoryDtoList;
    }

}
