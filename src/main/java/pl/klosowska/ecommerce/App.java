package pl.klosowska.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.klosowska.ecommerce.catalog.ArrayListProductStorage;
import pl.klosowska.ecommerce.catalog.ProductCatalog;
import pl.klosowska.ecommerce.infrastructure.PayUPaymentGateway;
import pl.klosowska.ecommerce.sales.offer.OfferCalculator;
import pl.klosowska.ecommerce.sales.SalesFacade;
import pl.klosowska.ecommerce.sales.cart.InMemoryCartStorage;
import pl.klosowska.ecommerce.sales.reservation.ReservationRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Here we go");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        var pid1 = catalog.addProduct("Lego set 8083", "nice one");
        catalog.changePrice(pid1, BigDecimal.valueOf(100.10));

        var pid2 = catalog.addProduct("Cobi set 8083", "nice one");
        catalog.changePrice(pid2, BigDecimal.valueOf(50.10));

        return catalog;
    }

    @Bean
    SalesFacade createSales(){
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                new PayUPaymentGateway(),
                new ReservationRepository()
        );
    }
}
