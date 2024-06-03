package pl.klosowska.ecommerce.infrastructure;

import pl.klosowska.ecommerce.sales.payment.PaymentDetails;
import pl.klosowska.ecommerce.sales.payment.PaymentGateway;
import pl.klosowska.ecommerce.sales.payment.RegisterPaymentRequest;

public class PayUPaymentGateway implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }
}
