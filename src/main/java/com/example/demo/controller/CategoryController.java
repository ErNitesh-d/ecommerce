package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@GetMapping("/test")
	public String test() {
		return "Category Controller is working!";
	}

	@Autowired
	private CategoryService categoryService;

	/*
	 * #ADD SUB-CATEGORY #/api/categories { "name": "Mobile Phones", "parentId": 1 }
	 */

	/*
	 * If it's a top-level category: #ADD CATEGORY { "name": "Electronics",
	 * "parentId": null }
	 */
	@PostMapping
	public Category create(@RequestBody Map<String, Object> body) {
		String name = (String) body.get("name");
		Long parentId = body.get("parentId") != null ? Long.valueOf(body.get("parentId").toString()) : null;
		return categoryService.createCategory(name, parentId);
	}

	/*
	 * #FATCH CATEGORIESS GET /api/categories
	 */
	@GetMapping
	public List<Category> getAll() {
		return categoryService.getAllCategories();
	}
}
