package com.jbk.Product.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.Product.dao.ProductDao;
import com.jbk.Product.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;

	@Override
	public boolean saveProduct(Product product) {
		boolean isPresent = dao.saveProduct(product);
		return isPresent;
	}

	@Override
	public boolean deleteProduct(int id) {
		boolean isPresent = dao.deleteProductById(id);
		return isPresent;
	}

	@Override
	public Product getProductById(int id) {
		Product product = dao.getProductById(id);
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = dao.getAllProduct();
		return list;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdate = dao.updateProduct(product);
		return isUpdate;
	}

	@Override
	public List<Product> getProductByAscending() {
		List<Product> list = dao.getProductByAscending();
		return list;
	}

	@Override
	public List<Product> getProductByDiscending() {
		List<Product> list = dao.getProductByDiscending();
		return list;
	}

	@Override
	public List<Product> getMaxPrize() {
		List<Product> list = dao.getMaxPrize();
		return list;
	}

	@Override
	public double getSumOfPrice() {
		double sum = dao.getSumOfPrice();
		return sum;
	}

	@Override
	public long rowCount() {
		long count = dao.rowCount();
		return count;
	}

	public List<Product> readExcel(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			Workbook workbook =new XSSFWorkbook(fis);
			Sheet sheet =workbook.getSheet(path);
			Iterator<Row> rows=sheet.iterator();
			int cnt=0;
			Product product=null;
              while(rows.hasNext()) {
            	  product = new Product();
            	  if(cnt==0) {
            		  cnt++;
            		  continue;
            	  }
            	 Row row= rows.next();
            	Iterator<Cell>cells= row.iterator();
            	while(cells.hasNext()) {
            		Cell cell=cells.next();
            		cell.getNumericCellValue();
            	}
            	 
              }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	@Override
	public String uploadsheet(MultipartFile file, HttpSession session) {
		String path = session.getServletContext().getRealPath("/uploaded");
		String fileName = file.getOriginalFilename();
		try {
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(path + File.separator + fileName));
			fos.write(data);
			List<Product> list = readExcel(path + File.separator + fileName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
