package ch.rafa.testjsoup.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOImpl<T, ID extends Serializable> implements GenericDAO<T, Serializable> {

	protected Session getSession() {return HibernateUtil.getSessionFactory().getCurrentSession();}

	@Override
	public void save(T t) {
		Session hibernateSession = this.getSession();
		Transaction trans = hibernateSession.beginTransaction();
		hibernateSession.save(t);
		trans.commit();
	}

	@Override
	public void delete(T obj) {
		Session hibernateSession = this.getSession();
		hibernateSession.saveOrUpdate(obj);
	}

	@Override
	public T find(Query query) {
		try {
			@SuppressWarnings("unchecked")
			T uniqueResult = (T) query.uniqueResult();
			return uniqueResult;
		}
		catch(HibernateException e) {
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<?> clazz) {
		try {
			System.out.println("findAll2 ..........");
			Session hibernateSession = this.getSession();
			Transaction trans = hibernateSession.beginTransaction();
			Query query = hibernateSession.createQuery("FROM " + clazz.getName());
			System.out.println("query ==> " + query.getQueryString());
			List<T> listT = query.list();
			trans.commit();
			System.out.println("listT size => " + listT.size());
			return listT;
		}
		catch(HibernateException e) {
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}
	
	
}
