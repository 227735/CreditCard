package pl.klosowska.ecommerce.sales.reservation;

import java.math.BigDecimal;

public class ReservationDetails {
    private final String reservationId;
    private final String paymentUrl;

    public ReservationDetails(String reservationId, String paymentUrl) {
        this.reservationId = reservationId;
        this.paymentUrl = paymentUrl;
    }


    public String getReservationId() {
        return reservationId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(10.10);
    }


}
