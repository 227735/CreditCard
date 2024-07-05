package pl.klosowska.ecommerce.sales;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import pl.klosowska.ecommerce.sales.cart.InMemoryCartStorage;
import pl.klosowska.ecommerce.sales.offer.Offer;
import pl.klosowska.ecommerce.sales.offer.OfferCalculator;
import pl.klosowska.ecommerce.sales.reservation.ReservationRepository;
import pl.klosowska.ecommerce.sales.reservation.SpyPaymentGateway;

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
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }

    @Test
    void itAllowsToAddProductToCart(){
        var customerId = thereIsExampleCustomer("Monika");
        var productId = thereIsProduct("Product a", BigDecimal.valueOf(10));

        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.ZERO, offer.getTotal());
        assertEquals(0, offer.getItemsCount());
    }

    @Test
    void itAddMultipleProductsToCart(){
        var customerId = thereIsExampleCustomer("Monika");
        var productA = thereIsProduct("Product a", BigDecimal.valueOf(10));
        var productB = thereIsProduct("Product b", BigDecimal.valueOf(20));

        SalesFacade sales = thereIsSalesFacade();
        sales.addToCart(customerId, productA);
        sales.addToCart(customerId, productB);

        Offer offer = sales.getCurrentOffer(customerId);
        assertEquals(BigDecimal.ZERO, offer.getTotal());
        assertEquals(0, offer.getItemsCount());

    }
    @Test
    void itDistinguishCartsByCustomer(){
        var customerA = thereIsExampleCustomer("Monika");
        var customerB = thereIsExampleCustomer("Michal");
        var productA = thereIsProduct("Product a", BigDecimal.valueOf(10));
        var productB = thereIsProduct("Product b", BigDecimal.valueOf(20));

        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerA, productA);
        sales.addToCart(customerB, productB);

        Offer offerA = sales.getCurrentOffer(customerA);
        assertEquals(BigDecimal.ZERO, offerA.getTotal());

        Offer offerB = sales.getCurrentOffer(customerB);
        assertEquals(BigDecimal.ZERO, offerB.getTotal());
    }


    private String thereIsProduct(String name, BigDecimal price) {
        return name;
    }

    @Test
    void itAllowsToRemoveProductFromCart(){

    }

    @Test
    void itAllowsToAcceptOffer(){
    }

    @Test
    void itAllowToPayForReservation(){

    }

}
