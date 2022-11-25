package com.jbk.Product.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.Product.entity.Product;

public interface ProductService {
	
	public boolean saveProduct(Product product);

	public boolean deleteProduct(int id);

	public Product getProductById(int id);

	public List<Product> getAllProduct();

	public boolean updateProduct(Product product);

	public List<Product> getProductByAscending();

	public List<Product> getProductByDiscending();

	public List<Product> getMaxPrize();

	public double getSumOfPrice();

	public long rowCount();

	public String uploadsheet(MultipartFile file,HttpSession session);

}
