package customTools;

import java.util.ArrayList;
import java.util.List;

//import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Shopper;

public class ShopperDB {

	public static void insert(Shopper user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Shopper user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Shopper user) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(user));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
    //may not need this one
	public static List<Shopper> selectAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		em.getTransaction();
		List<Shopper> tList = new ArrayList<Shopper>();
		String qString = "select e from Tuser e";
		TypedQuery<Shopper> q = (TypedQuery<Shopper>) em.createQuery(qString, Shopper.class);
		try {
			q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tList;
	}
	
	public static Shopper getUserById(int userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		em.getTransaction();
		String qString = "select e from Shopper e where e.id = :userId";
		TypedQuery<Shopper> q = (TypedQuery<Shopper>) em.createQuery(qString, Shopper.class);
		Shopper tuser = null;
		try {
			tuser = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tuser;
	}
	
	public static boolean checktUser(String loginName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		em.getTransaction();
		boolean shopperExist = false;
		String qString = "select e from Shopper e where e.name = :loginName";
		TypedQuery<Shopper> q = (TypedQuery<Shopper>) em.createQuery(qString, Shopper.class);
		q.setParameter("loginName", loginName);
		Shopper tuser = null;
		try {
			tuser = q.getSingleResult();
			if (tuser != null) shopperExist = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return shopperExist ;
	}
	public static Shopper getUserByName(String loginName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		em.getTransaction();

		String qString = "select e from Shopper e where e.name = :loginName";
		TypedQuery<Shopper> q = (TypedQuery<Shopper>) em.createQuery(qString, Shopper.class);
		q.setParameter("loginName", loginName);
		Shopper tuser = null;
		try {
			tuser = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return tuser ;
	}	 
/*	public static TheShopper internalCopy(Shopper aShopper) {
		TheShopper theShopper = new TheShopper();
		theShopper.setId(aShopper.getId());
		theShopper.setName(aShopper.getName());

		return theShopper;
	}	 
	public static Shopper dbCopy(TheShopper aShopper) {
		Shopper theShopper = new Shopper();
		theShopper.setId(aShopper.getId());
		theShopper.setName(aShopper.getName());

		return theShopper;
	}
*/}