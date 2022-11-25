package com.jbk.Product.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.Product.entity.Product;
import com.jbk.Product.service.ProductService;

@RestController
public class ProductsController {
	@Autowired
	ProductService ps;

	@PostMapping("/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
		boolean isPresent = ps.saveProduct(product);
		if (isPresent == true) {
			return new ResponseEntity<Boolean>(isPresent, HttpStatus.CREATED);
		} else {

		}
		return new ResponseEntity<Boolean>(isPresent, HttpStatus.OK);
	}

	@DeleteMapping("/deleteproduct/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable int id) {

		boolean isPresent = ps.deleteProduct(id);
		if (isPresent) {
			return new ResponseEntity<Boolean>(isPresent, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isPresent, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product product = ps.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(product, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getallproduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> list = ps.getAllProduct();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/updateProduct/{productId}")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean isUpdated = ps.updateProduct(product);
		if (isUpdated = true) {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get product by assending
	@GetMapping("/getProductByAscending")
	public ResponseEntity<List<Product>> getProductByAscending() {
		List<Product> list = ps.getProductByAscending();
		if (list != null) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get product by assending
	@GetMapping("/getProductByDiscending")
	public ResponseEntity<List<Product>> getProductByDiscending() {
		List<Product> list = ps.getProductByDiscending();
		if (list != null) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get product whose max price
	@GetMapping("/getMaxPrize")
	public ResponseEntity<List<Product>> getMaxPrize() {
		List<Product> list = ps.getMaxPrize();

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);

	}

	// get Total sum product price
	@GetMapping("/getSumOfPrice")
	public ResponseEntity<Double> getSumOfPrice() {
		double sum = ps.getSumOfPrice();

		return new ResponseEntity<Double>(sum, HttpStatus.FOUND);
	}

	// get total count of column
	@GetMapping("/totalrowcount")
	public ResponseEntity<Long> rowCount() {
		long count = ps.rowCount();
		return new ResponseEntity<Long>(count, HttpStatus.OK);
	}

	@PostMapping("/uploadsheet")
	public ResponseEntity<String> uploadsheet(@RequestParam MultipartFile file, HttpSession session) {
		String msg = ps.uploadsheet(file, session);
		
		return null;

	}

}
