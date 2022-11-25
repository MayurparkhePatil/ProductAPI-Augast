package com.jbk.Product.dao;

import java.util.List;

import com.jbk.Product.entity.Product;

public interface ProductDao {

	public boolean saveProduct(Product product);
	
	public boolean deleteProductById(int id);
	
	public Product getProductById(int id);
	
	public List<Product> getAllProduct();
	
	public boolean updateProduct(Product product);
	
	public List<Product> getProductByAscending();

	public List<Product> getProductByDiscending();

	public List<Product> getMaxPrize();

	public double getSumOfPrice();

	public long rowCount();
}
