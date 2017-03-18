package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PRODUCT database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCT_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_ID_GENERATOR")
	private long id;

	private String description;

	private String name;

	private String pic;

	private BigDecimal unitprice;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="product")
	private List<Cartitem> carts;

	//bi-directional many-to-one association to Lineitem
	@OneToMany(mappedBy="product")
	private List<Lineitem> lineitems;

	public Product() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public BigDecimal getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	public List<Cartitem> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cartitem> carts) {
		this.carts = carts;
	}

	public Cartitem addCart(Cartitem cart) {
		getCarts().add(cart);
		cart.setProduct(this);

		return cart;
	}

	public Cartitem removeCart(Cartitem cart) {
		getCarts().remove(cart);
		cart.setProduct(null);

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
		lineitem.setProduct(this);

		return lineitem;
	}

	public Lineitem removeLineitem(Lineitem lineitem) {
		getLineitems().remove(lineitem);
		lineitem.setProduct(null);

		return lineitem;
	}

}