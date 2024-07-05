package pl.klosowska.ecommerce.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import pl.klosowska.ecommerce.catalog.ProductCatalog;
import org.springframework.http.ResponseEntity;
import pl.klosowska.ecommerce.sales.offer.AcceptOfferRequest;
import pl.klosowska.ecommerce.sales.reservation.ReservationDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHTTPTest {
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate http;

    @Autowired
    ProductCatalog catalog;

    @Test
    void itAllowToAcceptOffer(){
        //ARRANGE
        String productId = thereIsExampleProduct("Example Product", BigDecimal.valueOf(10.10));

        //ACT
        //add product to cart
        String addProductToCartURL = String.format("http://localhost:%s/%s/%s", port, "api/add-to-cart/", productId);
        ResponseEntity<Object> addProductResponse = http.postForEntity(addProductToCartURL, null, Object.class);

        AcceptOfferRequest acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("john.doe@example.com");

        String acceptOfferURL = String.format("http://localhost:%s/%s", port, "api/accept-offer/");
        ResponseEntity<ReservationDetails> reservationDetailResponseEntity = http.postForEntity(acceptOfferURL,acceptOfferRequest, ReservationDetails.class);

        assertThat(reservationDetailResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(reservationDetailResponseEntity.getBody().getReservationId());
        assertNotNull(reservationDetailResponseEntity.getBody().getPaymentUrl());
        assertEquals(BigDecimal.valueOf(10.10), reservationDetailResponseEntity.getBody().getTotal());
    }

    private String thereIsExampleProduct(String name, BigDecimal price) {
        var prodId = catalog.addProduct(name, name);
        catalog.changePrice(prodId, price);

        return prodId;
    }
    private String asBaseURL(String addToCartUri) {
        return String.format("http://localhost:%s/%s", port, addToCartUri);
    }

}
