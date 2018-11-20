package com.how2java.test;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestHibernate11 {

	/*
		一个Product对应一个Category 
		一个Category对应多个Product 
		
		所以Category和Product是一对多的关系 
		本例讲解如何使用Hibernate实现一对多关系
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
         //首先获取id=1的category,然后通过getProducts() 直接取出其所对应的所有product
        Category c = (Category) s.get(Category.class, 1);
        Set<Product> ps = c.getProducts();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
