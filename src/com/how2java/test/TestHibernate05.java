package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate05 {

    public static void main(String[] args) {
    	 
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        /*
		根据id把对象从表里删除掉 
		注意:hibernate在删除一条数据之前，先要通过id把这条记录取出来
         */
        Product p =(Product) s.get(Product.class, 39);
        
        s.delete(p);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
