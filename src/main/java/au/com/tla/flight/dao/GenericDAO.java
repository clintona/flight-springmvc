package au.com.tla.flight.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

public class GenericDAO <T, PK extends Serializable> {
	private Class<T> type;

	public GenericDAO(Class<T> type) {
	    this.type = type;
	}
	
	public PK create(T o) {
		Session sess = getSession();
		PK key = (PK) sess.save(o);
		sess.flush();
	    return key;
	}
	
	public T read(PK id) {
	    return (T) getSession().get(type, id);
	}
	
	public void update(T o) {
		Session sess = getSession();
		sess.update(o);
		sess.flush();
	}
	
	public void delete(T o) {
		Session sess = getSession();
	    sess.delete(o);
	    sess.flush();
	}
	
	public void save(T o) {
		Session sess = getSession();
		sess.saveOrUpdate(o);
		sess.flush();
	}

	public Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

}