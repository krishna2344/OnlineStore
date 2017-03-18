package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SHOPPER database table.
 * 
 */
@Entity
@NamedQuery(name="Shopper.findAll", query="SELECT s FROM Shopper s")
public class Shopper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;

	private String name;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="shopper")
	private List<Cartitem> carts;

	//bi-directional many-to-one association to Lineitem
	@OneToMany(mappedBy="shopper")
	private List<Lineitem> lineitems;

	public Shopper() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Cartitem> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cartitem> carts) {
		this.carts = carts;
	}

	public Cartitem addCart(Cartitem cart) {
		getCarts().add(cart);
		cart.setShopper(this);

		return cart;
	}

	public Cartitem removeCart(Cartitem cart) {
		getCarts().remove(cart);
		cart.setShopper(null);

		return cart;
	}

	public List<Lineitem> getLineitems() {
		return this.lineitems;
	}

	public void setLineitems(List<Lineitem> lineitems) {
		this.lineitems = lineitems;
	}

	public Lineitem addLineitem(Lineitem lineitem) {
		getLineitems().add(lineitem);
		lineitem.setShopper(this);

		return lineitem;
	}

	public Lineitem removeLineitem(Lineitem lineitem) {
		getLineitems().remove(lineitem);
		lineitem.setShopper(null);

		return lineitem;
	}

}