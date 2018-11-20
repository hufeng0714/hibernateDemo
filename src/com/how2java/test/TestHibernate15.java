package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Category;

	//�ӳټ����ֽ�lazyload����one-many many-many��ʱ�򶼿���ʹ�ù�ϵ���ӳټ���
public class TestHibernate15 {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
        /*
		ִ��20�е�ʱ��ֻ���ѯCategory�����Ϣ�������ѯproduct_��
		ֻ����ִ��23�У�ͨ��categoryȡproducts��ʱ�򣬲Ż���ж�product_��Ĳ�ѯ
         */
        Category c = (Category) s.get(Category.class, 1);
 
        System.out.println("log1");
        System.out.println(c.getProducts());
        System.out.println("log1");
 
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
