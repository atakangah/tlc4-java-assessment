package products;

import services.ProductPricingService;

public class Options extends Product {
	private String exchange, ticker;
	private int validityTime;

	public Options(String productID, double currentPrice, String exchange, String ticker, int validityTime) {
		super(productID, currentPrice);
		this.exchange = exchange;
		this.ticker = ticker;
		this.validityTime = validityTime;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exhange) {
		this.exchange = exhange;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public int getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(int validityTime) {
		this.validityTime = validityTime;
	}

	@Override
	public double getPrice(ProductPricingService pricingService) {
		// TODO Auto-generated method stub
		return pricingService.price(this.exchange, this.ticker, this.validityTime);
	}

}
