package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Category;

	//延迟加载又叫lazyload，在one-many many-many的时候都可以使用关系的延迟加载
public class TestHibernate15 {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
        /*
		执行20行的时候，只会查询Category表的信息，不会查询product_表
		只有在执行23行，通过category取products的时候，才会进行对product_表的查询
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
