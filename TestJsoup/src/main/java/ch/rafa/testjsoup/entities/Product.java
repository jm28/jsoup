package ch.rafa.testjsoup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// This is the Product object used to store the required information
@Entity
@Table(name="product")
public class Product {

	private int id;
	private String productName;
	private int itemNum;
	private String currency;
	private double price;
	
	public Product() {}
	
	public Product(String productName, int itemNum, String currency, double price) {
		this.productName = productName;
		this.itemNum = itemNum;
		this.currency = currency;
		this.price = price;
	}
	
	@Id
	@SequenceGenerator(name="product_pid_seq", sequenceName="product_pid_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="product_pid_seq")
	@Column(name="pid")
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(name="productname", length=255)
	public String getProductName() {return productName;}
	public void setProductName(String productName) {this.productName = productName;}
	
	@Column(name="itemnumber")
	public int getItemNum() {return itemNum;}
	public void setItemNum(int itemNum) {this.itemNum = itemNum;}
	
	@Column(name="currency", length=4)
	public String getCurrency() {return currency;}
	public void setCurrency(String currency) {this.currency = currency;}
	
	@Column(name="price")
	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;}	
	
	
	@Override
	public String toString() {
		return "ID:    " + getId() + "\n" +
		"Name:  " + getProductName() + "\n" +
		"Item:  " + getItemNum() + "\n" + 
		"preis: " + getCurrency() + " " + getPrice();
	}
}
