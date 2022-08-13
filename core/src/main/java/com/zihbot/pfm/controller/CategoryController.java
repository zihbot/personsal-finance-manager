package com.zihbot.pfm.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zihbot.pfm.dao.Category;
import com.zihbot.pfm.model.CategoryDto;
import com.zihbot.pfm.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping()
	public List<CategoryDto> listCategories() {
		List<Category> categories = categoryService.listCategories();
        return categories.stream()
            .map(m -> new CategoryDto(m))
            .collect(Collectors.toList());
	}

	@PostMapping()
	public CategoryDto insertAccount(@RequestBody CategoryDto category) {
        Category result = categoryService.insertCategory(convertToDao(category));
		return new CategoryDto(result);
	}

    private Category convertToDao(final CategoryDto input) {
        Category result = new Category();
        result.setId(input.getId());
        result.setName(input.getName());
        result.setIcon(input.getIcon());
        result.setColor(input.getColor());
        return result;
    }
}
