package pl.klosowska.ecommerce.sales;

import pl.klosowska.ecommerce.sales.cart.Cart;
import pl.klosowska.ecommerce.sales.cart.InMemoryCartStorage;
import pl.klosowska.ecommerce.sales.offer.AcceptOfferRequest;
import pl.klosowska.ecommerce.sales.offer.Offer;
import pl.klosowska.ecommerce.sales.offer.OfferCalculator;
import pl.klosowska.ecommerce.sales.payment.PaymentDetails;
import pl.klosowska.ecommerce.sales.payment.PaymentGateway;
import pl.klosowska.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.klosowska.ecommerce.sales.reservation.Reservation;
import pl.klosowska.ecommerce.sales.reservation.ReservationDetails;
import pl.klosowska.ecommerce.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private InMemoryCartStorage cartStorage;
    private final OfferCalculator calculator;
    private OfferCalculator offerCalculator;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator calculator, PaymentGateway paymentGateway, ReservationRepository reservationRepository){
        this.cartStorage = cartStorage;
        this.calculator = calculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }



    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomerId(customerId)
                .orElse(Cart.empty());
    }


    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest) {
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);

        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails);

        reservationRepository.add(reservation);

        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl());

    }


}
