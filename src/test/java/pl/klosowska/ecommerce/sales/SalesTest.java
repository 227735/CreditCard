package pl.klosowska.ecommerce.sales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class SalesTest {
    @Test
    void itShowsOffer(){
        SalesFacade sales = thereIsSalesFacade();
        String customerId = thereIsExampleCustomer("Monika");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    private String thereIsExampleCustomer(String id) {
        return id;
    }

    private SalesFacade thereIsSalesFacade() {
        return new SalesFacade();
    }

    @Test
    void itAllowsToAddProductToCart(){
        String productId = thereIsProduct("Example", BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Monika");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(1,offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(10), offer.getTotal());
    }

    @Test
    void itAllowsToAddMultipleProductsToCart(){
        var customerId = thereIsExampleCustomer("Emil");
        var productA = thereIsProduct("product a", BigDecimal.valueOf(10));
        var productB = thereIsProduct("product b", BigDecimal.valueOf(20));

        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerId, productA);
        sales.addToCart(customerId, productB);

        Offer offer = sales.getCurrentOffer(customerId);
        assertEquals(BigDecimal.valueOf(30), offer.getTotal());
        assertEquals(2, offer.getItemsCount());

    }
    @Test
    void itDistinguishCartsByCustomer(){
        var productA = thereIsProduct("Example a", BigDecimal.valueOf(10));
        var productB = thereIsProduct("Example b", BigDecimal.valueOf(20));
        var customerA = thereIsExampleCustomer("Monika");
        var customerB = thereIsExampleCustomer("Michal");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerA, productA);
        sales.addToCart(customerB, productB);

        Offer offerA = sales.getCurrentOffer(customerA);
        Offer offerB = sales.getCurrentOffer(customerB);

        assertEquals(1,offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(10), offerA.getTotal());

        assertEquals(1,offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(20), offerB.getTotal());

    }


    private String thereIsProduct(String example, BigDecimal bigDecimal) {
        return null;
    }

    @Test
    void itAllowsToRemoveProductFromCart(){

    }

    @Test
    void itAllowsToAcceptOffer(){
    }

}
