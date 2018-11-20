package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate03 {

	public static void main(String[] args) {
		  
		/*
		new 了一个Product();，在数据库中还没有对应的记录，这个时候Product对象的状态是瞬时的。 
		通过Session的save把该对象保存在了数据库中，该对象也和Session之间产生了联系，此时状态是持久的。
		最后把Session关闭了，这个对象在数据库中虽然有对应的数据，但是已经和Session失去了联系，相当于脱离了管理，状态就是脱管的
		 */
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
        Product p = new Product();
        p.setName("p1");
        System.out.println("此时p是瞬时状态");
        s.save(p);
        System.out.println("此时p是持久状态");
        s.getTransaction().commit();
        s.close();
        System.out.println("此时p是脱管状态");
        sf.close();
    }
}
