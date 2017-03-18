package customTools;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
//import model.*;



import model.Lineitem;
import model.Product;
/*import business.ALineitem;
import business.TheProduct;*/

public class ProductDB {

	public static void insert(model.Product aProduct) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(aProduct);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(model.Product aProduct) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(aProduct);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(model.Product aProduct) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(aProduct));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static ArrayList<model.Product> selectAllProducts() {
		List<model.Product> pList = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String qString = "select e from Product e";
		TypedQuery<model.Product> q = (TypedQuery<model.Product>) em.createQuery(qString, model.Product.class);
		try {
			pList = q.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return  new ArrayList<model.Product>( pList);
	}

	public static model.Product getProduct(int pid) {
		model.Product product = null;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		String qString = "select e from Product e where e.id=" + pid;
		TypedQuery<model.Product> q = (TypedQuery<model.Product>) em.createQuery(qString, model.Product.class);
//		TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM DemoCustomer c WHERE c.customerId = 2L", Long.class);
		try {
			product = q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return  product;
	}	 
/*	public static TheProduct internalCopy(Product aProduct) {
		TheProduct theProduct = new TheProduct();
		theProduct.setId(aProduct.getId());
		theProduct.setName(aProduct.getName());
		theProduct.setDescription(aProduct.getDescription());
		theProduct.setUnitprice(aProduct.getUnitprice());
		theProduct.setPic(aProduct.getPic());
		return theProduct;
	}	 
	public static Product dbCopy(TheProduct aProduct) {
		Product theProduct = new Product();
		theProduct.setId(aProduct.getId());
		theProduct.setName(aProduct.getName());
		theProduct.setDescription(aProduct.getDescription());
		theProduct.setUnitprice(aProduct.getUnitprice());
		theProduct.setPic(aProduct.getPic());
		return theProduct;
	}
*/}
