package com.example.demo.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

	@GetMapping("/test")
	public String test() {
		return "Items Controller is working!";
	}

	@Autowired
	private ItemService itemService;

	/*
	 * #ADD ITEM POST /api/items { "name": "I Phone", "description":
	 * "Ergonomic wireless mouse with long battery life", "price": 1299.00,
	 * "categoryIds": [1,2] Item can be from One or more category }
	 */
	@PostMapping
	public Map<String, Object> create(@RequestBody Map<String, Object> body) {
		Item item = new Item();
		item.setName((String) body.get("name"));
		item.setDescription((String) body.get("description"));
		item.setPrice(Double.valueOf(body.get("price").toString()));

		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) body.get("categoryIds");
		List<Long> categoryIds = ids.stream().map(Long::valueOf).toList();

		Item saved = itemService.createItem(item, categoryIds);

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("id", saved.getId());
		response.put("name", saved.getName());
		response.put("description", saved.getDescription());
		response.put("price", saved.getPrice());

		List<Map<String, Object>> cats = saved.getCategories().stream().map(cat -> {
			Map<String, Object> m = new HashMap<>();
			m.put("id", cat.getId());
			m.put("name", cat.getName());
			return m;
		}).collect(Collectors.toList());

		response.put("categories", cats);
		return response;
	}

	/*
	 * #FATCH ALL ITEMS GET /api/items
	 */
	@GetMapping
	public List<Item> getAll() {
		return itemService.getAllItems();
	}

	/*
	 * #FATCH ITEMS WITH ID GET /api/items/{itemId}
	 */
	@GetMapping("/{id}")
	public Item getOne(@PathVariable Long id) {
		return itemService.getItem(id);
	}

	/*
	 * #UPDATE ITEM WITH ID PUT /api/items/{itemId}
	 * 
	 * { "name":"UPDATE Logitech Wireless Mouse",
	 * "description":"Ergonomic wireless mouse with long battery life", "price":
	 * 1299.00, "categoryIds": [6] }
	 */
	@PutMapping("/{id}")
	public Item updateItem(@PathVariable Long id, @RequestBody Map<String, Object> body) {
		return itemService.updateItem(id, body);
	}

	/*
	 * #DELETE ITEMS WITH ID DELETE /api/items/{itemId}
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		itemService.deleteItem(id);
	}

	/*
	 * #FILTERING AND SEARCHING #FATCH ITEMS WITH CATEGORY-NAME GET
	 * /api/items/category/{categoryName}
	 * 
	 * #FATCH ITEMS WITH ITEM-NAME GET /api/items/name/{itemName}
	 * 
	 * #FATCH ITEMS WITH PRICE GET /api/items/price/{price}
	 */
	@GetMapping("/category/{categoryName}")
	public List<Map<String, Object>> getByCategoryName(@PathVariable String categoryName) {
		List<Item> items = itemService.getItemsByCategoryName(categoryName);

		return items.stream().map(item -> {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("id", item.getId());
			map.put("name", item.getName());
			map.put("description", item.getDescription());
			map.put("price", item.getPrice());

			List<Map<String, Object>> catList = item.getCategories().stream().map(cat -> {
				Map<String, Object> c = new HashMap<>();
				c.put("id", cat.getId());
				c.put("name", cat.getName());
				return c;
			}).toList();

			map.put("categories", catList);
			return map;
		}).toList();
	}

	@GetMapping("/name/{itemName}")
	public List<Map<String, Object>> getByItemName(@PathVariable String itemName) {
		List<Item> items = itemService.getItemsByName(itemName);

		return items.stream().map(item -> {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("id", item.getId());
			map.put("name", item.getName());
			map.put("description", item.getDescription());
			map.put("price", item.getPrice());

			List<Map<String, Object>> catList = item.getCategories().stream().map(cat -> {
				Map<String, Object> c = new HashMap<>();
				c.put("id", cat.getId());
				c.put("name", cat.getName());
				return c;
			}).toList();

			map.put("categories", catList);
			return map;
		}).toList();
	}

	@GetMapping("/price/{price}")
	public List<Map<String, Object>> getByItemPrice(@PathVariable Double price) {
		List<Item> items = itemService.getItemsByPrice(price);

		return items.stream().map(item -> {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("id", item.getId());
			map.put("name", item.getName());
			map.put("description", item.getDescription());
			map.put("price", item.getPrice());

			List<Map<String, Object>> catList = item.getCategories().stream().map(cat -> {
				Map<String, Object> c = new HashMap<>();
				c.put("id", cat.getId());
				c.put("name", cat.getName());
				return c;
			}).toList();

			map.put("categories", catList);
			return map;
		}).toList();
	}

}
