package products;

import services.ProductPricingService;

public abstract class Product {
	private String productID;
	private double currentPrice;

	public Product(String productID, double currentPrice) {
		this.productID = productID;
		this.currentPrice = currentPrice;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public abstract double getPrice(ProductPricingService pricingService);
}
