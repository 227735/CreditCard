package pl.klosowska.ecommerce.sales;

import pl.klosowska.ecommerce.sales.cart.Cart;
import pl.klosowska.ecommerce.sales.cart.InMemoryCartStorage;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private final OfferCalculator calculator;
    private OfferCalculator offerCalculator;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator calculator){
        this.cartStorage = cartStorage;
        this.calculator = calculator;
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);
        return offerCalculator.calculate(cart.getLines());
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomerId(customerId)
                .orElse(Cart.empty());
    }


    public ReservationDetails acceptOffer(String customerId) {
        return new ReservationDetails();
    }


}
