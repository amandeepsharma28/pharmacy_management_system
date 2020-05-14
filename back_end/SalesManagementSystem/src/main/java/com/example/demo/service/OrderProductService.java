package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.OrderProductsRepository;
import com.example.demo.model.OrderProducts;
import com.example.demo.model.Product;

@Service
@Transactional
public class OrderProductService{
	@Autowired
	OrderProductsRepository orderProductRepository;
	
	
	public void saveOrderProducts(OrderProducts orderProduct)
	{
		orderProductRepository.save(orderProduct);
	}
	public void deleteOrderProducts(int orderId,int productId)
	{
		Product productDetail=getProductDetails(productId);
		OrderProducts orderProduct=orderProductRepository.findOrderProducts(orderId, productId);
		productDetail.setpQuantity(productDetail.getpQuantity()+orderProduct.getQuantity());
		saveProductDetails(productDetail);
		orderProductRepository.deleteOrderProducts(orderId,productId);
	}
	public List<OrderProducts> getOrderProducts(int orderId)
	{
		return orderProductRepository.findOrderProductsByOrder(orderId);
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
}
