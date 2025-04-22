package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Item createItem(Item item, List<Long> categoryIds) {
		List<Category> categories = categoryRepository.findAllById(categoryIds);
		if (categories.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more categories not found");
		}
		item.setCategories(categories);
		return itemRepository.save(item);
	}

	public List<Item> getAllItems() {
		List<Item> items = itemRepository.findAll();
		if (items.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items found");
		}
		return items;
	}

	public Item getItem(Long id) {
		return itemRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with ID " + id + " not found"));
	}

	public void deleteItem(Long id) {
		if (!itemRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with ID " + id + " not found");
		} else {
			itemRepository.deleteById(id);
			throw new ResponseStatusException(HttpStatus.ACCEPTED, "Item with ID " + id + " DELETED SUCCESSFULLY");
		}

	}

	public List<Item> getItemsByCategoryName(String categoryName) {
		List<Item> items = itemRepository.findByCategoryName(categoryName);
		if (items.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items found under category: " + categoryName);
		}
		return items;
	}

	public List<Item> getItemsByName(String name) {
		List<Item> items = itemRepository.findByNameContainingIgnoreCase(name);
		if (items.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items found with name: " + name);
		}
		return items;
	}

	public List<Item> getItemsByPrice(Double price) {
		List<Item> items = itemRepository.findByPrice(price);
		if (items.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No items found with price: " + price);
		}
		return items;
	}

	public Item updateItem(Long id, Map<String, Object> body) {
		Item item = itemRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with ID " + id + " not found"));

		item.setName((String) body.get("name"));
		item.setDescription((String) body.get("description"));
		item.setPrice(Double.parseDouble(body.get("price").toString()));

		if (body.get("categoryIds") != null) {
			@SuppressWarnings("unchecked")
			List<Integer> ids = (List<Integer>) body.get("categoryIds");
			List<Long> categoryIds = ids.stream().map(Long::valueOf).toList();
			List<Category> categories = categoryRepository.findAllById(categoryIds);
			if (categories.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more categories not found for update");
			}
			item.setCategories(categories);
		}

		return itemRepository.save(item);
	}

	public List<Item> getItemsByIds(List<Long> itemIds) {
		return itemRepository.findByIdIn(itemIds);
	}
}
