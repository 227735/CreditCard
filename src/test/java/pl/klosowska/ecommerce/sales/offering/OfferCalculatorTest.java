package pl.klosowska.ecommerce.sales.offering;

import org.junit.jupiter.api.Test;
import pl.klosowska.ecommerce.sales.offer.Offer;
import pl.klosowska.ecommerce.sales.offer.OfferCalculator;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;

public class OfferCalculatorTest {
    @Test
    void zeroOfferForEmptyItems() {
        OfferCalculator offerCalculator = new OfferCalculator();

        Offer offer = offerCalculator.calculate(Collections.emptyList());

        assertThat(((Offer) offer).getTotal()).isEqualTo(BigDecimal.ZERO);
    }
}
