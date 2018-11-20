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
        System.out.println("��ʱp��˲ʱ״̬");
        p.setPrice(7000);
        //����
        s.save(p);
        System.out.println("��ʱp�ǳ־�״̬");      
        
        /*
        for(int i=0;i<10;i++){
        	Product p = new Product();
        	p.setName("iphone"+i);
        	p.setPrice(i);
        	s.save(p);
        }
         */
        
        //��ȡ,��һ��ѯ
        Product p6 =(Product) s.get(Product.class, 6);
        System.out.println("id=6�Ĳ�Ʒ������: "+p.getName());
        
        //ɾ��
        Product pd = (Product) s.get(Product.class, 1);
        //s.delete(pd);
        
        //�޸�
        Product pu = (Product) s.get(Product.class, 6);
        pu.setName("iphone7 u");
        s.update(pu);
        
        System.out.println("id=6�Ĳ�Ʒ������: "+p6.getName());
        
        /*
			HQL��Hibernate Query Language����hibernateר�����ڲ�ѯ���ݵ���䣬�б���SQL��HQL ���ӽ�����������˼ά��ʽ�� 
			����ʹ�õ����������Product,���Ǳ�������product_
         */
        String name = "iphone";
        Query q =s.createQuery("from Product p where p.name like ?");
        q.setString(0, "%"+name+"%");
        List<Product> ps= q.list();
        for (Product p1 : ps) {
            System.out.println(p1.getName());
        }
        
        /*
			ʹ��Criteria�������ݲ�ѯ�� 
			��HQL��SQL��������Criteria ��ȫ�� �������ķ�ʽ�ڽ������ݲ�ѯ�������ٿ�����sql���ĺۼ�
         */
        
        Criteria c= s.createCriteria(Product.class);
        c.add(Restrictions.like("name", "%"+name+"%"));
        List<Product> ps2 = c.list();
        for (Product p2 : ps2) {
            System.out.println(p2.getName());
        }
        
        /*
			ͨ����׼SQL�����в�ѯ 
			Hibernate��Ȼ�����˶Ա�׼SQL����֧�֣�
			��һЩ���ϣ����������ϲ�ѯ�������з���ͳ�ƺ���������£���׼SQL�����Ȼ��Ч�ʽϸߵ�һ��ѡ��
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
        
        //���һ
        //��������������У�������һ���µ�Category����"c1" ����������Ϊid=8��product��category
        
        Category c2 =new Category();
        c2.setName("c1");
        s.save(c2);
         
        Product p3 = (Product) s.get(Product.class, 8);
        p.setCategory(c2);
        s.update(p);
        
        //��Զ�
        //���Ȼ�ȡid=1��category,Ȼ��ͨ��getProducts() ֱ��ȡ��������Ӧ������product
        Category c3 = (Category) s.get(Category.class, 1);
        Set<Product> ps3 = c3.getProducts();
        for (Product p4 : ps) {
            System.out.println(p.getName());
        }
        
        s.getTransaction().commit();
        s.close();
        System.out.println("��ʱp���ѹ�״̬");
        sf.close();
	}
}
