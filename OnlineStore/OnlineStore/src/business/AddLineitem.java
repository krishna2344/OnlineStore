package business;

import java.math.BigDecimal;

import model.*;
import customTools.*;

public class AddLineitem {

	public static void main(String[] args) {
		int quantity = 10;
		double unitPrice = 29.99;
		Lineitem aLineitem = new Lineitem();
		Product aProduct = new Product();
		Shopper aShopper = new Shopper();
		aProduct.setDescription("A cheap product.");
		aProduct.setName("ABC Burger");
		aProduct.setPic("Desert.jpg");
		aProduct.setUnitprice(new BigDecimal(unitPrice));
		ProductDB.insert(aProduct);
		aLineitem.setProduct(aProduct);
		aShopper.setName("anshy");
		ShopperDB.insert(aShopper);
		aLineitem.setShopper(aShopper);
		aLineitem.setQuantity(new BigDecimal(quantity));
		aLineitem.setTotal(new BigDecimal(quantity * unitPrice));
		aLineitem.setPurchasedate(LineItemDB.currentSqlDate());

		LineItemDB.insert(aLineitem);
	}
}
