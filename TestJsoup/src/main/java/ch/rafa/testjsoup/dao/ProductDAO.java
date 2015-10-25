package ch.rafa.testjsoup.dao;

import java.io.Serializable;
import java.util.List;

import ch.rafa.testjsoup.entities.Product;

public class ProductDAO extends DAOImpl<Product, Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<Product> toAllProducts() {
		System.out.println("toAllProducts.........");
		List<Product> allProducts = findAll(Product.class);
		return allProducts;
	}
}
