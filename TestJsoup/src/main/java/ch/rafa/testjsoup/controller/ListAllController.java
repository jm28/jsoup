package ch.rafa.testjsoup.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import ch.rafa.testjsoup.dao.ProductDAO;
import ch.rafa.testjsoup.entities.Product;

public class ListAllController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String uiInfoTextarea;
	
	private ProductDAO dbHandler = new ProductDAO();
	
	@PostConstruct
	public String showInfo() {
		StringBuilder sb = new StringBuilder();
		for (Product p : findAllUsers()) {
			sb.append(p.toString()).append("\n");
		}
		return sb.toString();
	}
	
	
    private List<Product> findAllUsers() {
        //this method is called multiple times
        return dbHandler.toAllProducts();
    }
}
