package com.zihbot.pfm.service;

import java.util.List;

import com.zihbot.pfm.dao.Label;
import com.zihbot.pfm.repository.CategoryRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
}
