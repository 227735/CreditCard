package pl.hubers.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal creditLimit;
    private BigDecimal balance;

    public void assignCredit(BigDecimal creditLimit) {
        if (isCreditAlreadyAssigned()){
            throw new CreditCantBeReassignException();
        }
        this.creditLimit = creditLimit;
        this.balance = balance;
    }
    private static boolean isCreditBelowThreshold(BigDecimal creditLimit){
        return creditLimit.compareTo(BigDecimal.valueOf(100)) < 0;
    }

    private boolean isCreditAlreadyAssigned() {
        return this.creditLimit != null;
    }

//        if (isCreditBelowThreshold(creditLimit))
//        if (creditLimit.compareTo(BigDecimal.valueOf(100)) < 0) {
//        throw new CreditBelowThresholdException();

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal money) {
        if (isBelowBalance(money)) {
            throw new TransactionDenyException();
        }
        this.balance = balance.subtract(money);
    }

    private boolean isBelowBalance(BigDecimal money) {
        return balance.subtract(money).compareTo(BigDecimal.valueOf(2000));
    }
}
