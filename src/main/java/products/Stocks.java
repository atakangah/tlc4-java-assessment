package products;

import services.ProductPricingService;

public class Stocks extends Product {
	private String ticker, exchange;
	ProductPricingService pricingService;
	
	public Stocks(String productID, double currentPrice, String ticker, String exchange) {
		super(productID, currentPrice);
		this.ticker = ticker;
		this.exchange = exchange;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	@Override
	public double getPrice(ProductPricingService pricingService) {
		return pricingService.price(this.exchange, this.ticker);
	}
	
}
