package com.example.demo.dao;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.PurchaseOrder;

public interface PurchaseORderRepository extends JpaRepository<PurchaseOrder, Integer> {
	@Query(value="SELECT p FROM PurchaseOrder p where p.date BETWEEN :from AND :to")
	public List<PurchaseOrder> findByOrderDate(LocalDate from, LocalDate to);
}
