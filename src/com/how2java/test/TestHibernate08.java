package com.how2java.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.how2java.pojo.Product;

public class TestHibernate08 {
	
	/*
	ʹ��Criteria�������ݲ�ѯ�� 
	��HQL��SQL��������Criteria ��ȫ�� �������ķ�ʽ�ڽ������ݲ�ѯ�������ٿ�����sql���ĺۼ�
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
 
        String name = "iphone";
          
        /*
		ʹ��Criteria ��ѯ����
		1. ͨ��session��createCriteria����һ��Criteria ����
		2. Criteria.add ����Լ���� �ڱ���������һ����name��ģ����ѯ(like)
		3. ����list()�������ز�ѯ����ļ���
         */
        Criteria c= s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        List<Product> ps = c.list();
        for (Product p : ps) {
            System.out.println(p.getName());
        }
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
