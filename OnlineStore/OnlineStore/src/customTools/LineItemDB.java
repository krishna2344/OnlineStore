package customTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import business.*;
import model.*;

public class LineItemDB {

	public static void insert(Lineitem item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Lineitem item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Lineitem item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(item));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static List<Lineitem> getLineItems(int lineitemId) { // cart id is
															// actually shopper
															// id
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		List<Lineitem> tList = new ArrayList<Lineitem>();
		String qString = "select e from Lineitem e where e.shopper_id = :lineitemId";
		TypedQuery<Lineitem> q = (TypedQuery<Lineitem>) em.createQuery(qString,
				Lineitem.class);
		List<Lineitem> items = null;
		try {
			items = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
		return items;
	}

	public static Lineitem selectLineitem(int lineitemId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String qString = "select e from Lineitem e where e.id = :lineitemId";
		TypedQuery<Lineitem> q = (TypedQuery<Lineitem>) em.createQuery(qString,
				Lineitem.class);
		Lineitem item = null;
		try {
			item = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
		return item;
	}

	public static Cartitem Line2Cart(Lineitem theLineitem) {
		Cartitem aCartitem = new Cartitem();
		aCartitem.setId(theLineitem.getId());
		aCartitem.setQuantity(theLineitem.getQuantity());
		aCartitem.setTotal(theLineitem.getTotal());
		aCartitem.setProduct(theLineitem.getProduct());
		aCartitem.setShopper(theLineitem.getShopper());
		return aCartitem;
	}

	public static java.sql.Date currentSqlDate() {
		// sort of equivalent to sql sysdate
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
		String formattedDate = sdf.format(today);
		java.util.Date utilDate = null;
		try {
			utilDate = sdf.parse(formattedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
}
