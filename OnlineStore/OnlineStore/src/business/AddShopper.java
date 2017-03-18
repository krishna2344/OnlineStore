package business;

import java.math.BigDecimal;

import model.Shopper;
import customTools.ShopperDB;

public class AddShopper {

	public static void main(String[] args) {
		Shopper aShopper = new Shopper();
		aShopper.setName("John");	
		ShopperDB.insert(aShopper);
	}
}
