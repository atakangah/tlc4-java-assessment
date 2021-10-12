import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exchanges.ConcreteParisTradedProducts;
import products.Futures;
import products.Options;
import products.Product;
import products.Stocks;
import services.ProductPricingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PricingServiceTest {

    ProductPricingService productPricingService;
    ConcreteParisTradedProducts parisTradedProducts;

    @BeforeEach
    void setUp() {
        productPricingService = mock(ProductPricingService.class);
        parisTradedProducts = new ConcreteParisTradedProducts();
        when(productPricingService.price(anyString(), anyString())).thenReturn(10.0);
        when(productPricingService.price(anyString(), anyString(), anyInt(), anyInt())).thenReturn(20.0);
        when(productPricingService.price(anyString(), anyString(), anyInt())).thenReturn(50.0);
    }

    @Test
    void testStockPrice() {
        Product aStock = new Stocks("myProd", 500.0 , "AAPL", "World Trade Center");
        assertEquals(10.0, aStock.getPrice(this.productPricingService), "Stock price not valid");
    }

    @Test
    void testFuturesPrice() {
    	Product aFuture = new Futures("myProd", 500.0, "World Trade Center", "GZ-SOMETHING-1", 5, 2021);
        assertEquals(20.0, aFuture.getPrice(this.productPricingService), "Futures price not valid");
    }
    
    @Test
    void testOptionsPrice() {
    	Product aOption = new Options("myProd", 500.0, "World Trade Center", "AAPL", 6);
        assertEquals(50.0, aOption.getPrice(this.productPricingService), "Options price not valid");
    }
    
}