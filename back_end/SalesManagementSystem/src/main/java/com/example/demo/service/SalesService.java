package com.example.demo.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.SalesRepository;
import com.example.demo.model.Bill;
import com.example.demo.model.Order;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.Product;


@Service
@Transactional
public class SalesService{
	@Autowired
	 SalesRepository salesRepository;
	@Autowired
	OrderProductService orderProductService;
	@Autowired
	EntityManager em;

	//-------------------------------------Adding a new Sales Order-----------------------------------------------
	
	public Bill addOrder(Order order) {
		Order o=new Order();
		em.persist(o);
		double sum=0;
		int totalQuantity=0;
		order.setSoId(o.getSoId());
		Set<OrderProducts> orderProducts=order.getOrderProducts();
		order.setSoDate(order.getSoDate());
		order.setCustomerName(order.getCustomerName());
		order.setCustomerAddress(order.getCustomerAddress());
		order.setCustomerEmail(order.getCustomerEmail());
		order.setCustomerPhone(order.getCustomerPhone());
		order.setSoStatus(order.getSoStatus());
		order.setSellerId(order.getSellerId());
		order.setOrderProducts(orderProducts);
		for(OrderProducts product:orderProducts) {
		Product productDetail=getProductDetails(product.getProductId());
		product.setOrder(o.getSoId());
		product.setPrice(productDetail.getpSellingPrice());
		product.setProductId(product.getProductId());
		product.setQuantity(product.getQuantity());
		totalQuantity+=product.getQuantity();
		sum+=productDetail.getpSellingPrice()*product.getQuantity();
		productDetail.setpQuantity(productDetail.getpQuantity()-product.getQuantity());
		saveProductDetails(productDetail);
		orderProductService.saveOrderProducts(product);
		}
		
		order.setTotalQuantity(totalQuantity);
		//order.setTotalQuantity(order.getTotalQuantity());
		order.setTax(sum*0.15);
		//order.setTax(order.getSubTotal()*0.15);
		order.setTotal(sum+order.getTax());
		//order.setTotal(order.getSubTotal()+order.getTax());
		order.setSubTotal(sum);
		//order.setSubTotal(order.getSubTotal());
		salesRepository.save(order);
		return generateBill(order);
	}
	public Bill generateBill(Order order) {
		Bill bill = new Bill();
		bill.setSoId(order.getSoId());
		bill.setSoDate(order.getSoDate());
		bill.setCustomerName(order.getCustomerName());
		bill.setCustomerEmail(order.getCustomerEmail());
		bill.setCustomerAddress(order.getCustomerAddress());
		bill.setCustomerPhone(order.getCustomerPhone());
		bill.setSoStatus(order.getSoStatus());
		bill.setSellerId(order.getSellerId());
		bill.setSubTotal(order.getSubTotal());
		bill.setTax(order.getTax());
		bill.setTotal(order.getTotal());
		bill.setTotalQuantity(order.getTotalQuantity());
		return bill;
	}
	//-------------------------------------Deleting a Sales Order-----------------------------------------------
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		Optional<Order> order=salesRepository.findById(id);
		 if(order.isPresent()) {
			 List<OrderProducts> orderProducts=orderProductService.getOrderProducts(id);
			 for(OrderProducts product:orderProducts) {
					Product productDetail=getProductDetails(product.getProductId());
					productDetail.setpQuantity(productDetail.getpQuantity()+product.getQuantity());
					saveProductDetails(productDetail);
				}
		salesRepository.deleteById(id);
		 }
	}
	//------------------------------------Fetching Sales Order----------------------------------------------------------------------------------
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return salesRepository.findAll();
	}
	//--------------------------------------------------Search Sales Order by Id---------------------------------------------------------------------
	public Optional<Order> getOrderById(int id) {
		// TODO Auto-generated method stub
		return salesRepository.findById(id);
	}
	//--------------------------------------------------Search Sales Order by Name---------------------------------------------------------------------
	public Optional<Order> getOrderByCustomerName(String name)
	{
		return salesRepository.findByCustName(name);
	}
	//------------------------------------------------Updating Sales Order--------------------------------------------------------------------------------
	public Bill updateOrder(int id, Order orderDetails) {
		// TODO Auto-generated method stub
		Order order= new Order();
		order=salesRepository.getOne(id);
		orderDetails.setSoId(id);
		order.setSoId(orderDetails.getSoId());
		Set<OrderProducts> orderProducts=orderDetails.getOrderProducts();
		double sum=0;
		int totalQuantity=0;
		order.setSoId(orderDetails.getSoId());
		order.setSoDate(orderDetails.getSoDate());
		order.setCustomerName(orderDetails.getCustomerName());
		order.setCustomerAddress(orderDetails.getCustomerAddress());
		order.setCustomerEmail(orderDetails.getCustomerEmail());
		order.setCustomerPhone(orderDetails.getCustomerPhone());
		order.setSoStatus(orderDetails.getSoStatus());
		order.setSellerId(orderDetails.getSellerId());
		for(OrderProducts product:order.getOrderProducts()) {
		orderProductService.deleteOrderProducts(id,product.getProductId());
		}
		for(OrderProducts product:orderProducts)
		{
		Product productDetail=getProductDetails(product.getProductId());
		product.setOrder(id);
		product.setPrice(productDetail.getpSellingPrice());
		product.setProductId(product.getProductId());
		product.setQuantity(product.getQuantity());
		totalQuantity+=product.getQuantity();
		sum+=productDetail.getpSellingPrice()*product.getQuantity();
		productDetail.setpQuantity(productDetail.getpQuantity()-product.getQuantity());
		saveProductDetails(productDetail);
		orderProductService.saveOrderProducts(product);
	}
		order.setTotalQuantity(totalQuantity);
		order.setTax(sum*0.15);
		order.setTotal(sum+order.getTax());
		order.setSubTotal(sum);
		order.setSubTotal(sum);
		salesRepository.save(order);
		return generateBill(order);
		
	}
	//-------------------------------------------------Getting details of product-------------------------------------------------------------------------------
	public Product getProductDetails(int productId) {
		
		final String uri = "http://localhost:8005/product/searchproduct/"+productId;
		    RestTemplate restTemplate = new RestTemplate();
		    Product result = restTemplate.getForObject(uri, Product.class);   
		    System.out.println(result);
		    return result;
	}
	//-------------------------------------------------Save details of product-------------------------------------------------------------------------------

	public void saveProductDetails(Product product) {
		final String uri = "http://localhost:8005/product/addproduct";
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.postForObject(uri,product, Product.class);   
	}
	//---------------------------------Getting Orders according to Order Date------------------------------
	public List<Order> getReport(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		List<Order> orders=salesRepository.findByOrderDate(from, to);
		return orders;		
	}
}
