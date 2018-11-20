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
		1. ����id��ȡ�ö���
		2. �޸ĸö��������
		3. ͨ��Session��update�����ѱ仯���µ����ݿ���
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
