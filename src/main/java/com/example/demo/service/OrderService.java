package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Item;
import com.example.demo.entity.Orders;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	public Orders createOrder(Map<String, Object> body) {
		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) body.get("itemIds");
		List<Long> itemIds = ids.stream().map(Long::valueOf).toList();

		List<Item> items = itemRepository.findAllById(itemIds);
		if (items.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No valid items found for order");
		}

		double total = items.stream().mapToDouble(Item::getPrice).sum();

		Orders order = new Orders();
		order.setOrderDate(LocalDateTime.now());
		order.setItems(items);
		order.setTotalAmount(total);

		return orderRepository.save(order);
	}

	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	public Orders getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with ID " + id + " not found"));
	}

	public Orders updateOrder(Long id, Map<String, Object> body) {
		Orders order = getOrderById(id);

		@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) body.get("itemIds");
		List<Long> itemIds = ids.stream().map(Long::valueOf).toList();

		List<Item> items = itemRepository.findAllById(itemIds);
		if (items.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No valid items found for update");
		}

		double total = items.stream().mapToDouble(Item::getPrice).sum();

		order.setItems(items);
		order.setTotalAmount(total);

		return orderRepository.save(order);
	}

	public void deleteOrder(Long id) {
		if (!orderRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with ID " + id + " not found");
		}
		orderRepository.deleteById(id);
	}
}