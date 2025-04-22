package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(String name, Long parentId) {
		Category category = new Category();
		category.setName(name);
		if (parentId != null) {
			category.setParent(
					categoryRepository.findById(parentId).orElseThrow(() -> new RuntimeException("Parent not found")));
		}
		return categoryRepository.save(category);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
}
