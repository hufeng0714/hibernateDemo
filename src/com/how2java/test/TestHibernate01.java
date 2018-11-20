package com.how2java.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.how2java.pojo.Product;

public class TestHibernate01 {

	
	/*
		����һ��Product���󣬲�ͨ��hibernate��������󣬲��뵽���ݿ���
		
		hibernate�Ļ��������ǣ�
		1. ��ȡSessionFactory 
		2. ͨ��SessionFactory ��ȡһ��Session
		3. ��Session�����Ͽ���һ������
		4. ͨ������Session��save�����Ѷ��󱣴浽���ݿ�
		5. �ύ����
		6. �ر�Session
		7. �ر�SessionFactory
	 */
	public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        
        Session s = sf.openSession();
        s.beginTransaction();
 
        Product p = new Product();
        p.setName("iphone7");
        p.setPrice(7000);
        //����
        s.save(p);
        s.getTransaction().commit();
        s.close();
        sf.close();
	}
}
