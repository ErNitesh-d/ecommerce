package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

	@Query("SELECT i FROM Item i JOIN i.categories c WHERE LOWER(c.name) = LOWER(:categoryName)")
	List<Item> findByCategoryName(@Param("categoryName") String categoryName);

	List<Item> findByNameContainingIgnoreCase(String name);

	List<Item> findByPrice(Double price);

	List<Item> findByIdIn(List<Long> itemIds);
}
