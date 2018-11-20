package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate14 {

	/*
	属性的延迟加载: 
	当使用load的方式来获取对象的时候，只有访问了这个对象的属性
	，hibernate才会到数据库中进行查询。否则不会访问数据库
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
     
        //在打印log1之前，是不会打印出sql语句的，只有在访问属性“getName()"的时候，才会访问数据库
        Product p = (Product)s.load(Product.class, 41);
        System.out.println("log1");
        System.out.println(p.getName());
        System.out.println("log2");
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
