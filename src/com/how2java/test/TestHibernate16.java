package com.how2java.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.how2java.pojo.Product;

public class TestHibernate16 {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
  
        Session s = sf.openSession();
        s.beginTransaction();
  
        String name = "iphone";
        /*
		Hibernate使用Criteria 来进行分页查询
		c.setFirstResult(2); 表示从第3条数据开始
		c.setMaxResults(5); 表示一共查询5条数据
         */
        Criteria c= s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        c.setFirstResult(2);
        c.setMaxResults(5);
         
        List<Product> ps = c.list();
        for (Product p : ps) {
            System.out.println(p.getName());
             
        }
          
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
