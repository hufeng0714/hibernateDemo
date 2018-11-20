package com.how2java.test;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestHibernate11 {

	/*
		һ��Product��Ӧһ��Category 
		һ��Category��Ӧ���Product 
		
		����Category��Product��һ�Զ�Ĺ�ϵ 
		�����������ʹ��Hibernateʵ��һ�Զ��ϵ
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
         //���Ȼ�ȡid=1��category,Ȼ��ͨ��getProducts() ֱ��ȡ��������Ӧ������product
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
