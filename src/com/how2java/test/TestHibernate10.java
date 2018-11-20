package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestHibernate10 {

	/*
		一个Product对应一个Category 
		一个Category对应多个Product 
		
		所以Product和Category是多对一的关系 
		本例讲解如何使用Hibernate实现多对一关系
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
         
        /*
         	在这个测试例子中，增加了一个新的Category对象"c1" 
			并将其设置为id=8的product的category
         */
        Category c =new Category();
        c.setName("c1");
        s.save(c);
         
        Product p = (Product) s.get(Product.class, 8);
        p.setCategory(c);
        s.update(p);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
