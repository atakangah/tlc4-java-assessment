import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exchanges.ConcreteParisTradedProducts;
import hanlders.ProductAlreadyRegisteredException;
import products.Futures;
import products.Options;
import products.Product;
import products.Stocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductTest {
    ConcreteParisTradedProducts parisTradedProducts;

    @BeforeEach
    void setUp() {
        this.parisTradedProducts = new ConcreteParisTradedProducts();
    }

    @Test 
    void testProductsAdd() throws ProductAlreadyRegisteredException {
        Product product = mock(Product.class); 
        this.parisTradedProducts.addNewProduct(product);
        assertEquals(1, this.parisTradedProducts.getAvailableProductsSize(), "Failed to add product");
    }

    @Test 
    void testThrowProductExistsException() throws ProductAlreadyRegisteredException {      
        Product product = new Stocks("myProd", 500, "AAPL", "World Trade Center");
        this.parisTradedProducts.addNewProduct(product);
       
        assertThrows(ProductAlreadyRegisteredException.class, () -> {
            this.parisTradedProducts.addNewProduct(product);
        }, "Duplicate product was added without throwing exception");
    }

    @Test 
    void testUnavailalbeProductsTradeable() {
        Product product = mock(Product.class); 
        this.parisTradedProducts.trade(product, 2);
        assertEquals(0, this.parisTradedProducts.getTradedProductsSize(), "Able to trade unavailable products");
    }

    @Test 
    void testTradingAvailableProduct() throws ProductAlreadyRegisteredException {
        Product product = new Futures("myProd", 500, "World Trade Center", "GZ-SOMETHING-1", 5, 2021);
        this.parisTradedProducts.addNewProduct(product);
        this.parisTradedProducts.trade(product, 4);
        assertEquals(1, this.parisTradedProducts.getTradedProductsSize(), "Unable to trade available product");
    }


    @Test
    void testQuantityOfTradedProducts() throws ProductAlreadyRegisteredException {
        Product product = new Stocks("myProd", 500, "AAPL", "World Trade Center");
        this.parisTradedProducts.addNewProduct(product);
        this.parisTradedProducts.trade(product, 4);
        assertEquals(4, this.parisTradedProducts.totalTradeQuantityForDay(), "Number of products traded is invalid");
    }


    @Test
    void testValueOfTradedProducts() throws ProductAlreadyRegisteredException {
        Product product = mock(Stocks.class); 
        when(product.getProductID()).thenReturn("myProd"); 
        when(product.getCurrentPrice()).thenReturn(500.0); 

        this.parisTradedProducts.addNewProduct(product); 
        this.parisTradedProducts.trade(product, 4); 

        assertEquals(2000.0, this.parisTradedProducts.totalValueOfDaysTradedProducts(), "Total traded products invalid");
    }
}