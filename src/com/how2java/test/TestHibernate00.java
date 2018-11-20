package com.how2java.test;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestHibernate00 {

	public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        
        Session s = sf.openSession();
        s.beginTransaction();
 
        Product p = new Product();
        p.setName("iphone7");
        System.out.println("此时p是瞬时状态");
        p.setPrice(7000);
        //插入
        s.save(p);
        System.out.println("此时p是持久状态");      
        
        /*
        for(int i=0;i<10;i++){
        	Product p = new Product();
        	p.setName("iphone"+i);
        	p.setPrice(i);
        	s.save(p);
        }
         */
        
        //获取,单一查询
        Product p6 =(Product) s.get(Product.class, 6);
        System.out.println("id=6的产品名称是: "+p.getName());
        
        //删除
        Product pd = (Product) s.get(Product.class, 1);
        //s.delete(pd);
        
        //修改
        Product pu = (Product) s.get(Product.class, 6);
        pu.setName("iphone7 u");
        s.update(pu);
        
        System.out.println("id=6的产品名称是: "+p6.getName());
        
        /*
			HQL（Hibernate Query Language）是hibernate专门用于查询数据的语句，有别于SQL，HQL 更接近于面向对象的思维方式。 
			比如使用的是类的名字Product,而非表格的名字product_
         */
        String name = "iphone";
        Query q =s.createQuery("from Product p where p.name like ?");
        q.setString(0, "%"+name+"%");
        List<Product> ps= q.list();
        for (Product p1 : ps) {
            System.out.println(p1.getName());
        }
        
        /*
			使用Criteria进行数据查询。 
			与HQL和SQL的区别是Criteria 完全是 面向对象的方式在进行数据查询，将不再看到有sql语句的痕迹
         */
        
        Criteria c= s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        List<Product> ps2 = c.list();
        for (Product p2 : ps2) {
            System.out.println(p2.getName());
        }
        
        /*
			通过标准SQL语句进行查询 
			Hibernate依然保留了对标准SQL语句的支持，
			在一些场合，比如多表联合查询，并且有分组统计函数的情况下，标准SQL语句依然是效率较高的一种选择
         */
        
        String sql = "select * from product_ p where p.name like '%"+name+"%'";
        
        Query q2= s.createSQLQuery(sql);
        List<Object[]> list= q2.list();
        for (Object[] os : list) {
            for (Object filed: os) {
                System.out.print(filed+"\t");
            }
            System.out.println();
        }
        
        //多对一
        //在这个测试例子中，增加了一个新的Category对象"c1" 并将其设置为id=8的product的category
        
        Category c2 =new Category();
        c2.setName("c1");
        s.save(c2);
         
        Product p3 = (Product) s.get(Product.class, 8);
        p.setCategory(c2);
        s.update(p);
        
        //多对多
        //首先获取id=1的category,然后通过getProducts() 直接取出其所对应的所有product
        Category c3 = (Category) s.get(Category.class, 1);
        Set<Product> ps3 = c3.getProducts();
        for (Product p4 : ps) {
            System.out.println(p.getName());
        }
        
        s.getTransaction().commit();
        s.close();
        System.out.println("此时p是脱管状态");
        sf.close();
	}
}
