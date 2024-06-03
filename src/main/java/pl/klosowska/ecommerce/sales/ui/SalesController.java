package pl.klosowska.ecommerce.sales.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.klosowska.ecommerce.sales.SalesFacade;
import pl.klosowska.ecommerce.sales.offer.AcceptOfferRequest;
import pl.klosowska.ecommerce.sales.offer.Offer;
import pl.klosowska.ecommerce.sales.reservation.ReservationDetails;

@RestController
public class SalesController {
    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer(){
        String customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }
    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(AcceptOfferRequest acceptOfferRequest) {
        String customerId = getCurrentCustomerId();
        ReservationDetails reservationDetails = sales.acceptOffer(customerId, acceptOfferRequest);
        return reservationDetails;
    }

    @PostMapping("/api/add-to-cart/{productId}")
    void addToCart(@PathVariable String productId) {
        String customerId = getCurrentCustomerId();
        sales.addToCart(customerId, productId);
    }

    private String getCurrentCustomerId(){
        return "Monika";
    }
}

