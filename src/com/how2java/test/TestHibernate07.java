package com.how2java.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate07 {

    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        /*
        HQL��Hibernate Query Language����hibernateר�����ڲ�ѯ���ݵ���䣬�б���SQL��HQL ���ӽ�����������˼ά��ʽ�� 
		����ʹ�õ����������Product,���Ǳ�������product_
		
		1. ���ȸ���hql����һ��Query����
		2. ���ò���(�ͻ�1��PreparedStatement��һ����Query�ǻ�0��)
		3. ͨ��Query�����list()���������ز�ѯ�Ľ���ˡ�

		ע�� ʹ��hql��ʱ���õ�������Product,�����Ǳ���product_
		ע�� ʹ��hql��ʱ�򣬲���Ҫ��ǰ��� select *
         */
        
        String name = "iphone";
        Query q =s.createQuery("from Product p where p.name like ?");
        q.setString(0, "%"+name+"%");
        List<Product> ps= q.list();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
