package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public Orders createOrder(@RequestBody Map<String, Object> body) {
		return orderService.createOrder(body);
	}

	@GetMapping
	public List<Orders> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public Orders getOrder(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

	@PutMapping("/{id}")
	public Orders updateOrder(@PathVariable Long id, @RequestBody Map<String, Object> body) {
		return orderService.updateOrder(id, body);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
}
