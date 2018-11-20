package com.how2java.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class TestHibernate09 {

	/*
	ͨ����׼SQL�����в�ѯ 
	Hibernate��Ȼ�����˶Ա�׼SQL����֧�֣���һЩ���ϣ����������ϲ�ѯ��
	�����з���ͳ�ƺ���������£���׼SQL�����Ȼ��Ч�ʽϸߵ�һ��ѡ��
	 */
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
 
        Session s = sf.openSession();
        s.beginTransaction();
 
        String name = "iphone";
         /*
		ʹ��Session��createSQLQuery����ִ�б�׼SQL���
		��Ϊ��׼SQL����п��ܷ��ظ��ָ����Ľ�����������ѯ������ͳ�ƽ���ȵȡ�
		 ���ܱ�֤���ѯ����ܹ�װ��һ��Product�����У�
		 ���Է��صļ������ÿһ��Ԫ����һ���������顣 Ȼ����ͨ���±��������������е�����ȡ������
          */
        String sql = "select * from product_ p where p.name like '%"+name+"%'";
         
        Query q= s.createSQLQuery(sql);
        List<Object[]> list= q.list();
        for (Object[] os : list) {
            for (Object filed: os) {
                System.out.print(filed+"\t");
            }
            System.out.println();
        }
         
        s.getTransaction().commit();
        s.close();
        sf.close();
    }
}
