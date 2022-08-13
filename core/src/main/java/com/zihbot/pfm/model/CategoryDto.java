package com.zihbot.pfm.model;

import com.zihbot.pfm.dao.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String icon;
    private String color;

    public CategoryDto(final Category input) {
        id = input.getId();
        name = input.getName();
        icon = input.getIcon();
        color = input.getColor();
    }
}
