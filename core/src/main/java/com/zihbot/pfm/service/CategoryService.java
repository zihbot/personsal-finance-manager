package com.zihbot.pfm.service;

import java.util.List;

import com.zihbot.pfm.dao.Category;
import com.zihbot.pfm.repository.CategoryRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public Category insertCategory(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }
}
