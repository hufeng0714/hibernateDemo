package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestHibernate10 {

	/*
		һ��Product��Ӧһ��Category 
		һ��Category��Ӧ���Product 
		
		����Product��Category�Ƕ��һ�Ĺ�ϵ 
		�����������ʹ��Hibernateʵ�ֶ��һ��ϵ
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
         
        /*
         	��������������У�������һ���µ�Category����"c1" 
			����������Ϊid=8��product��category
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
