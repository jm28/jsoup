package ch.rafa.testjsoup.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.validator.routines.UrlValidator;

import ch.rafa.testjsoup.dao.ProductDAO;
import ch.rafa.testjsoup.entities.Product;
import ch.rafa.testjsoup.parser.WebsiteParser;


public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	String websiteUrl;
	String uiInfoTextarea;

	//private DBHandler dbHandler = new DBHandler();
	private ProductDAO dbHandler = new ProductDAO();
	private UrlValidator urlValidator = new UrlValidator();
	
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
	
	public String getUiInfoTextarea() {
		return uiInfoTextarea;
	}

	
	public String submit() {
		if (!urlValidator.isValid(websiteUrl)) {
			FacesContext fc = FacesContext.getCurrentInstance();
		    fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "URL is not valid", null)); 
		}
		else {
			Product extractedProduct = WebsiteParser.parse(websiteUrl);
			dbHandler.save(extractedProduct);
			this.uiInfoTextarea = extractedProduct.toString();
		}
		
		return null;
	}
}
