package com.example.demo.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bill;
import com.example.demo.model.Order;
import com.example.demo.service.SalesService;

@CrossOrigin
@RestController
@RequestMapping("order") 
public class SalesController {
private final SalesService salesService;
	
	@Autowired
	public SalesController(SalesService salesService) {
		this.salesService=salesService;	
	}
	@PostMapping(value="/addorder",consumes="application/json")
	public Bill addOrder(@RequestBody Order order ) {
		return salesService.addOrder(order);
	}
	@DeleteMapping(value="/deleteorder/{id}")
	public void deleteOrder(@PathVariable int id) {
		salesService.deleteOrder(id);
	}
	@GetMapping(value="/allorders", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getAllOrders(){
		return salesService.getAllOrders();
	}
	@GetMapping(value="/orderbyid/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Optional<Order> getOrderById(@PathVariable int id){
		return salesService.getOrderById(id);
	}
	@GetMapping(value="/orderbycustomernumber/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Optional<Order> getOrderByCustomerName(@PathVariable String name){
		return salesService.getOrderByCustomerName(name);
	}
	@PutMapping(value="/updateorder/{id}")
	public Bill updateOrder(@PathVariable int id, @RequestBody Order orderDetails) {
		return salesService.updateOrder(id,orderDetails);
	}
	@CrossOrigin
	@GetMapping(value="/report", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Order> getReport(@RequestParam String from,@RequestParam String to){
		LocalDate fromDate=LocalDate.parse(from);
		LocalDate toDate=LocalDate.parse(to);
		return salesService.getReport(fromDate,toDate);
	}
	
}
