package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate14 {

	/*
	���Ե��ӳټ���: 
	��ʹ��load�ķ�ʽ����ȡ�����ʱ��ֻ�з�����������������
	��hibernate�Żᵽ���ݿ��н��в�ѯ�����򲻻�������ݿ�
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
     
        //�ڴ�ӡlog1֮ǰ���ǲ����ӡ��sql���ģ�ֻ���ڷ������ԡ�getName()"��ʱ�򣬲Ż�������ݿ�
        Product p = (Product)s.load(Product.class, 41);
        System.out.println("log1");
        System.out.println(p.getName());
        System.out.println("log2");
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
