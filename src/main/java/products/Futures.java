package products;

import services.ProductPricingService;

public class Futures extends Product {
	private String exchange, contractCode;
	private int contractMonth, contractYear;

	public Futures(String productID, double currentPrice, String exchange, String contractCode, int contractMonth,
			int contractYear) {
		super(productID, currentPrice);
		this.exchange = exchange;
		this.contractCode = contractCode;
		this.contractMonth = contractMonth;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public int getContractMonth() {
		return contractMonth;
	}

	public void setContractMonth(int contractMonth) {
		this.contractMonth = contractMonth;
	}

	public int getContractYear() {
		return contractYear;
	}

	public void setContractYear(int contractYear) {
		this.contractYear = contractYear;
	}

	@Override
	public double getPrice(ProductPricingService pricingService) {
		// TODO Auto-generated method stub
		return pricingService.price(this.exchange, this.contractCode, this.contractMonth, this.contractYear);
	}
}
