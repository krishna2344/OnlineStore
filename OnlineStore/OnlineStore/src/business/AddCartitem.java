package business;

import java.math.BigDecimal;

import customTools.*;
import model.*;

public class AddCartitem {

	public static void main(String[] args) {
		int quantity = 10;
		double unitPrice = 29.99;
		Cartitem aCartitem = new Cartitem();
		Product aProduct = new Product();
		Shopper aShopper = new Shopper();
		aProduct.setDescription("A great product.");
		aProduct.setName("xyz Pizza");
		aProduct.setPic("Chrysanthemum.jpg");
		aProduct.setUnitprice(new BigDecimal(unitPrice));
		ProductDB.insert(aProduct);
		aCartitem.setProduct(aProduct);
		aShopper.setName("nelson");
		ShopperDB.insert(aShopper);
		aCartitem.setShopper(aShopper);
		aCartitem.setQuantity(new BigDecimal(quantity));
		aCartitem.setTotal(new BigDecimal(quantity * unitPrice));
		CartItemDB.insert(aCartitem);
		
	}

}
