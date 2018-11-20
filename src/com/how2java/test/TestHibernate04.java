package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate04 {

	
	 public static void main(String[] args) {
	        SessionFactory sf = new Configuration().configure().buildSessionFactory();
	  
	        Session s = sf.openSession();
	        s.beginTransaction();
	  
	        Product p =(Product) s.get(Product.class, 6);
	        //通过id获取对象
	        System.out.println("id=6的产品名称是: "+p.getName());
	          
	        s.getTransaction().commit();
	        s.close();
	        sf.close();
	    }
}
