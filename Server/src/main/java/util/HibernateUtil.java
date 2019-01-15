package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * @Name HibernateUtil
 * @Description 建立hibernateUtil类
 * @author zhangping
 * @date 2017/12/13 0013 17:51
 */

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            //读取hibernate.cfg.xml文件
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");

            //建立SessionFactory
            sessionFactory = cfg.buildSessionFactory();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    //获得工厂
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //获取当前连接
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



}
