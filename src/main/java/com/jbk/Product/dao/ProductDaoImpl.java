package com.jbk.Product.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import com.jbk.Product.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	SessionFactory sf;

	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded=false;
		Session session=sf.openSession();
		try {
			Product prd = session.get(Product.class, product.getProductId());
			if(prd==null) {
				Transaction tr=session.beginTransaction();
				session.save(product);
				tr.commit();
				isAdded=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return isAdded;
	}
 
	@Override
	public boolean deleteProductById(int productId) {
		Session session=sf.openSession();
		Product product1=null; 
		Boolean isDelete=false;
		try {
			Transaction tr=session.beginTransaction();
			 product1=session.get(Product.class, productId);
			 if(product1!=null) {
				 session.delete(product1);
				 tr.commit();
				 isDelete=true;
			 }else {
				 System.out.println("Product not deleted");
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return isDelete;
	}

	@Override
	public Product getProductById(int id) {
		
		Session session=sf.openSession();
		Product prd =null;
		try {
			 prd = session.get(Product.class, id);
			if(prd!=null) {
				System.out.println(prd);
			}else {
				System.out.println("product not available");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) 
				session.close();
			}
		return prd ;
		
	}

	@Override
	public List<Product> getAllProduct() {
		Session session =sf.openSession();
		List<Product> list=null;
		Product product =null;
		try {
			Criteria criteria=session.createCriteria(Product.class);
			 list=criteria.list();
		     
		      if(list!=null) {
		    	  product =list.get(0);
		    	  
		    	  System.out.println( product);
		      }else {
		    	  System.out.println("product not available");
		      }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return list;
		
	}

	@Override
	public boolean updateProduct(Product product) {
        Session session=sf.openSession();
     
        boolean isUpdated=false;
        try {
        	   Transaction tr=  session.beginTransaction();
        	//Product product1 = session.get(Product.class, product);
        	
        		session.update(product);
            	tr.commit();
            	isUpdated=true;
            	
		} catch (Exception e) {
			e.printStackTrace();
		}
        finally {
        	if(session!=null) {
        		session.close();
        	}
        }
		return isUpdated;
	}

	@Override
	public List<Product> getProductByAscending() {
		
		Session session = sf.openSession();
		List<Product> list=null;
		try {
			Criteria criteria =session.createCriteria(Product.class);
			 criteria.addOrder(Order.asc("productId"));
			list= criteria.list();
			if(list!=null) {
				Product prd =list.get(0);
				System.out.println(prd);
			}
			else {
				System.out.println("product not avilable");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
	        	if(session!=null) {
	        		session.close();
	        	}
		 }
		return list;
	}

	@Override
	public List<Product> getProductByDiscending() {
		
		Session session = sf.openSession();
		List<Product> list=null;
		try {
			Criteria criteria =session.createCriteria(Product.class);
			 criteria.addOrder(Order.desc("productId"));
			list= criteria.list();
			if(list!=null) {
				Product prd =list.get(0);
				System.out.println(prd);
			}
			else {
				System.out.println("product not avilable");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {
	        	if(session!=null) {
	        		session.close();
	        	}
		 }
		return list;

	}

	@Override
	public List<Product> getMaxPrize(){
		Session session = sf.openSession();
		List<Double> list=null;
		Product prd=null;
		 double maxPrice=0;
		 List<Product> maxPrpduct =null;
		try {
			Criteria maxcriteria= session.createCriteria(Product.class);
			maxcriteria.setProjection(Projections.max("productCost"));
			 list=maxcriteria.list();
			  maxPrice=list.get(0);
				Criteria eqcriteria= session.createCriteria(Product.class);
				eqcriteria.equals(Restrictions.eq("productCost", maxPrice));
				 maxPrpduct =eqcriteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(session!=null) {
				session.close();
			}
		}
		return maxPrpduct;
	}

	
	
	@Override
	public double getSumOfPrice() {
		Session session= sf.openSession();
		double sum=0;
		List<Double> list= null; 
		try {
			
		Criteria criteria=	session.createCriteria(Product.class);
		criteria.setProjection(Projections.sum("productCost"));
		 list=  criteria.list();
		
		if(!list.isEmpty()) {
			 sum=list.get(0);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) {
				session.close();
			}
		}
		return sum;
		
	}

	@Override
	public long rowCount() {
		Session session = sf.openSession();
		List<Long> list =null;
		long count =0;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.rowCount());
			 list =criteria.list();
			if(list!=null) {
				 count =list.get(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return count;
	}
}
	
	
	
	
	
	
	
	
	

	
