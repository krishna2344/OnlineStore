package customTools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Lineitem;
import model.Cartitem;

public class CartItemDB {
	public static void insert(Cartitem item) {
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

	public static void update(Cartitem item) {
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

	public static void delete(Cartitem item) {
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
    //may not need this one
	public static List<Cartitem> getCartItems(int shopperId) { 
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		List<Cartitem> tList = new ArrayList<Cartitem>();
		String qString = "select e from Cart e where e.shopper_id = :shopperId";
		TypedQuery<Cartitem> q = (TypedQuery<Cartitem>) em.createQuery(qString, Cartitem.class);
		List<Cartitem> items = null;
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
	
	public static Cartitem selectCart(int CartId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String qString = "select e from Cart e where e.id = :CartId";
		TypedQuery<Cartitem> q = (TypedQuery<Cartitem>) em.createQuery(qString, Cartitem.class);
		Cartitem item = null;
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
	
	public static Lineitem Cart2Line(Cartitem theCartitem) {
		Lineitem aLineitem = new Lineitem();
		aLineitem.setId(theCartitem.getId());
		aLineitem.setQuantity(theCartitem.getQuantity());
		aLineitem.setTotal(theCartitem.getTotal());
		aLineitem.setProduct(theCartitem.getProduct());
		aLineitem.setShopper(theCartitem.getShopper());
		return aLineitem;
	}
}
