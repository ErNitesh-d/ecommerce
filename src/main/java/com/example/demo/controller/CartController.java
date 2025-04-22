package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

	@Autowired
	private CartService cartService;

	// 1. Create Cart for a user
	@PostMapping
	public ResponseEntity<Map<String, Object>> createCart(@RequestBody Map<String, Long> request) {
		Long userId = request.get("userId");
		Cart cart = cartService.createCart(userId);

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "Cart created successfully.");
		response.put("cartId", cart.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// 2. Add Item to Cart for a user
	@PostMapping("/{cartId}/items")
	public ResponseEntity<Map<String, Object>> addItemToCart(@RequestBody Map<String, Object> request,
			@PathVariable Long cartId) {
		Long userId = Long.valueOf(request.get("userId").toString());
		Long itemId = Long.valueOf(request.get("itemId").toString());
		int quantity = Integer.parseInt(request.get("quantity").toString());

		CartItem item = cartService.addItemToCart(userId, cartId, itemId, quantity);

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "Item added to cart successfully.");
		response.put("cartId", cartId);
		response.put("itemId", item.getItem().getId());
		response.put("quantity", item.getQuantity());
		return ResponseEntity.ok(response);
	}

	// 3. Get Cart Items for a user
	@GetMapping("/{cartId}")
	public ResponseEntity<Map<String, Object>> getCartItems(@RequestParam Long userId, @PathVariable Long cartId) {
		List<CartItem> items = cartService.getItemsInCart(userId, cartId);

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("cartId", cartId);
		response.put("items", items.stream().map(item -> {
			Map<String, Object> itemMap = new LinkedHashMap<>();
			itemMap.put("itemId", item.getItem().getId());
			itemMap.put("name", item.getItem().getName());
			itemMap.put("description", item.getItem().getDescription());
			itemMap.put("price", item.getItem().getPrice());
			itemMap.put("quantity", item.getQuantity());
			return itemMap;
		}).collect(Collectors.toList()));
		return ResponseEntity.ok(response);
	}

	// 4. Remove Item from Cart for a user
	@DeleteMapping("/{cartId}/items/{itemId}")
	public ResponseEntity<Map<String, Object>> removeItemFromCart(@RequestParam Long userId, @PathVariable Long cartId,
			@PathVariable Long itemId) {
		cartService.removeItemFromCart(userId, cartId, itemId);

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "Item removed from cart successfully.");
		response.put("cartId", cartId);
		response.put("itemId", itemId);
		return ResponseEntity.ok(response);
	}

	// 5. Checkout Cart for a user
	@PostMapping("/{cartId}/checkout")
	public ResponseEntity<Map<String, Object>> checkoutCart(@RequestParam Long userId, @PathVariable Long cartId) {
		Long orderId = cartService.checkoutCart(userId, cartId);

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("message", "Checkout successful. Your order is being processed.");
		response.put("orderId", orderId);
		return ResponseEntity.ok(response);
	}
}
