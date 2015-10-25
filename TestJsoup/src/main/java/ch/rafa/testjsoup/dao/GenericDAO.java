package ch.rafa.testjsoup.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface GenericDAO<T, ID extends Serializable> {
	public void save(T t);
	public void delete(T obj);
	public T find(Query query);
	public List<T> findAll(Class<?> clazz);
}
