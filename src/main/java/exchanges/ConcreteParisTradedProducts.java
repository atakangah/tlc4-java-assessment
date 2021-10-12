package exchanges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hanlders.ProductAlreadyRegisteredException;
import products.Product;

public class ConcreteParisTradedProducts implements ParisTradedProducts {
	List<Product> availableProducts = new ArrayList<Product>();
	Map<Product, Integer> tradedProducts = new HashMap<Product, Integer>();

	public void addNewProduct(final Product newProduct) throws ProductAlreadyRegisteredException {
		// TODO Auto-generated method stub
		Optional<Product> optionalProduct = this.availableProducts.stream()
				.filter(product -> product.getProductID().equals(newProduct.getProductID())).findFirst();

		if (optionalProduct.isPresent()) {
			throw new ProductAlreadyRegisteredException(
					"Product with id " + newProduct.getProductID() + " already exist");
		}

		this.availableProducts.add(newProduct);
	}

	public void trade(Product product, int quantity) {
		// TODO Auto-generated method stub
		if (!this.availableProducts.contains(product))
			return;

		if (this.tradedProducts.containsKey(product)) {
			this.tradedProducts.put(product, this.tradedProducts.get(product) + quantity);
			return;
		}
		this.tradedProducts.put(product, quantity);
	}

	public int totalTradeQuantityForDay() {
		// TODO Auto-generated method stub
		return this.tradedProducts.values().stream().mapToInt(quantity -> quantity).sum();
	}

	public double totalValueOfDaysTradedProducts() {
		// TODO Auto-generated method stub
		return this.tradedProducts.keySet().stream()
				.mapToDouble(product -> product.getCurrentPrice() * this.tradedProducts.get(product)).sum();
	}

	public int getAvailableProductsSize() {
		return this.availableProducts.size();
	}
	
	public int getTradedProductsSize() {
		return this.tradedProducts.size();
	}

}
