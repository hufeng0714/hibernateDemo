package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate03 {

	public static void main(String[] args) {
		  
		/*
		new ��һ��Product();�������ݿ��л�û�ж�Ӧ�ļ�¼�����ʱ��Product�����״̬��˲ʱ�ġ� 
		ͨ��Session��save�Ѹö��󱣴��������ݿ��У��ö���Ҳ��Session֮���������ϵ����ʱ״̬�ǳ־õġ�
		����Session�ر��ˣ�������������ݿ�����Ȼ�ж�Ӧ�����ݣ������Ѿ���Sessionʧȥ����ϵ���൱�������˹���״̬�����ѹܵ�
		 */
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
        Product p = new Product();
        p.setName("p1");
        System.out.println("��ʱp��˲ʱ״̬");
        s.save(p);
        System.out.println("��ʱp�ǳ־�״̬");
        s.getTransaction().commit();
        s.close();
        System.out.println("��ʱp���ѹ�״̬");
        sf.close();
    }
}
