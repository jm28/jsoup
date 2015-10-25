package ch.rafa.testjsoup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
			//Create the SessionFactory from standard (hibernate.cfg.xml) config file
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session beginTransaction() {
		Session hibernateSession = getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	
	public static void commitTransaction() {
		getSession().getTransaction().commit();
	}
	
	public static void rollbackTransaction() {
		getSession().getTransaction().rollback();
	}
	
	public static void closeSession() {
		getSession().close();
	}
}
