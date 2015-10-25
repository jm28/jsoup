package ch.rafa.testjsoup.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ch.rafa.testjsoup.entities.Product;


public class WebsiteParser {

	//Specify the HTML elements to search for to extract data
	private static final String PRODUCT_NAME_ELEMENT_CLASS  = ".product-name";
	private static final String PRICE_ELEMENT_CLASS = ".product-price";
	private static final String PRODUCT_ARTIKELNR_CLASS = ".detail-element-content li:first-child";
	private static final String PRODUCT_PRICE_APPENDIX = ".product-price-appendix";
	
	public static Product parse(String urlToParse) {
		try {
			Document doc = Jsoup.connect(urlToParse).get();
			
			// extract the product name
			String validProductName = extractElement(doc, PRODUCT_NAME_ELEMENT_CLASS);
			System.out.println("pname => " + validProductName);
			
			// extract the product article number
			int productArticleNumber = transformToValidArticleNumber(extractElement(doc, PRODUCT_ARTIKELNR_CLASS));
			System.out.println("article Number => " + productArticleNumber);
			
			// extract the currency and price
			String price = extractElement(doc, PRICE_ELEMENT_CLASS);
			String priceAppendix = extractElement(doc, PRODUCT_PRICE_APPENDIX);
			
			String articleCurrency = extractCurrencyFromPrice(price);
			System.out.println("article Currency => " + articleCurrency);
			if (priceAppendix!=null)
				price = price.replaceAll(priceAppendix,"");
			double validPrice = transformToValidPrice(price);
			System.out.println("validPrice => " + validPrice);

			return new Product(validProductName, productArticleNumber, articleCurrency, validPrice);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	private static String extractElement(Document doc, String elementToExtract) {
		Element el = doc.select(elementToExtract).first();
		return el.text();
	}
	
	
	private static int transformToValidArticleNumber(String rawArticleNumber) {
		//we get the article number as "Artikelnummer: xxxx" so we just replace all non numeric characters
		rawArticleNumber = rawArticleNumber.replaceAll("[^\\d]+", "");
		return Integer.parseInt(rawArticleNumber);
	}
	
	
	private static String extractCurrencyFromPrice(String rawProductPrice) {
		//Usually it's something like CHF XX.- , so we splitt and retrieve the first element
		return rawProductPrice.split(" ")[0];
	}
	
	
	private static double transformToValidPrice(String price) {
		System.out.println("price => " + price);
		// first we replace - with 00, because the price is returned sometimes as xx.-
		price = price.replaceAll("-", "00");
		
		//Replace all non-digits except for '.'
		price = price.replaceAll("[^\\d.]+", "");
		
		return Double.parseDouble(price);
	}
	

	public static void main(String[] args) {
		WebsiteParser.parse("https://www.digitec.ch/de/s1/product/intel-nuc-canyon-nuc5i7ryh-usb-30-intel-core-i7-5557u-barebone-4671724");
		WebsiteParser.parse("https://www.digitec.ch/de/s1/product/mad-catz-rat-tournament-edition-schwarz-kabel-maus-2436981");
	}
}
