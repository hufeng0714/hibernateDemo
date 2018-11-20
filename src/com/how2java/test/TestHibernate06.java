package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate06 {

    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        /*
		1. 根据id获取该对象
		2. 修改该对象的属性
		3. 通过Session的update方法把变化更新到数据库中
         */
        Product p =(Product) s.get(Product.class, 40);
         
        System.out.println(p.getName());
         
        p.setName("iphone-modified");
         
        s.update(p);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
