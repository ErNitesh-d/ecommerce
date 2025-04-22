package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Item;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ItemRepository itemRepository;

	// 1. Create Cart for a user
	public Cart createCart(Long userId) {
		Cart cart = new Cart();
		cart.setUserId(userId); // Set the cart owner userId
		return cartRepository.save(cart);
	}

	// 2. Add Item to Cart for a user
	public CartItem addItemToCart(Long userId, Long cartId, Long itemId, int quantity) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

		// Ensure the cart belongs to the user
		if (!cart.getUserId().equals(userId)) {
			throw new RuntimeException("Unauthorized access to this cart");
		}

		Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

		// Create a new CartItem and add it to the Cart
		CartItem cartItem = new CartItem();
		cartItem.setCart(cart);
		cartItem.setItem(item);
		cartItem.setQuantity(quantity);

		cart.getCartItems().add(cartItem);
		cartRepository.save(cart); // Save the updated cart

		return cartItem;
	}

	// 3. Get Items in Cart for a user
	public List<CartItem> getItemsInCart(Long userId, Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

		if (!cart.getUserId().equals(userId)) {
			throw new RuntimeException("Unauthorized access to this cart");
		}

		return cart.getCartItems();
	}

	// 4. Remove Item from Cart for a user
	public void removeItemFromCart(Long userId, Long cartId, Long itemId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

		if (!cart.getUserId().equals(userId)) {
			throw new RuntimeException("Unauthorized access to this cart");
		}

		cart.getCartItems().removeIf(cartItem -> cartItem.getItem().getId().equals(itemId));
		cartRepository.save(cart);
	}

	// 5. Checkout Cart for a user
	public Long checkoutCart(Long userId, Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

		if (!cart.getUserId().equals(userId)) {
			throw new RuntimeException("Unauthorized access to this cart");
		}

		// Simulate order processing (generate order ID)
		return 12345L; // Order ID
	}
}
