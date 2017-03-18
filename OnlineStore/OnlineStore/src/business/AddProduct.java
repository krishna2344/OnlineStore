package business;

import java.math.BigDecimal;

import model.Product;
import customTools.ProductDB;

public class AddProduct {

	public static void main(String[] args) {

		double unitPrice = 19.99;
		Product aProduct = new Product();
		aProduct.setDescription("A interesting book.");
		aProduct.setName("Dream High");
		aProduct.setPic("Hydrangeas.jpg");
		aProduct.setUnitprice(new BigDecimal(unitPrice));
		ProductDB.insert(aProduct);
	}

}
