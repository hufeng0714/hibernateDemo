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
		����id�Ѷ���ӱ���ɾ���� 
		ע��:hibernate��ɾ��һ������֮ǰ����Ҫͨ��id��������¼ȡ����
         */
        Product p =(Product) s.get(Product.class, 39);
        
        s.delete(p);
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
