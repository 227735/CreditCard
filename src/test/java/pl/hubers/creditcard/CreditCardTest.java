package pl.hubers.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {

    @Test
    void isAllowsAssignCreditCardLimit() {
        //Arrange
        CreditCard card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(1500));
        //Assert
        assertEquals(
                BigDecimal.valueOf(1500),
                card.getBalance());

    }

    @Test
    void isDenyCreditLimitBelowThreshold() {
        CreditCard card = new CreditCard();

        try {
            card.assignCredit(BigDecimal.valueOf(50));
            fail("Exeption should be raised");
        } catch (CreditBe;pwThreholdException e) {
            assertTrue(true);
        }
    }

    public class CreditBelowThresholdException
        extends IllegalStateException {
        
    }
}

