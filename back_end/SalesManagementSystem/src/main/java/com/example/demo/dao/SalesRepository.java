package com.example.demo.dao;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Order;

public interface SalesRepository extends JpaRepository<Order, Integer> {
	
	
	@Query(value="SELECT o FROM Order o where o.customerName=:name")
	public Optional<Order> findByCustName(String name);
	
	@Query(value="SELECT o FROM Order o where o.soDate BETWEEN :from AND :to")
	public List<Order> findByOrderDate(LocalDate from, LocalDate to);
	
	

}
