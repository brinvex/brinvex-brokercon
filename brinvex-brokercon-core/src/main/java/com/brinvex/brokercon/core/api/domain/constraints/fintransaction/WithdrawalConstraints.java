package com.brinvex.brokercon.core.api.domain.constraints.fintransaction;

import com.brinvex.fintypes.enu.Currency;
import com.brinvex.brokercon.core.api.domain.Asset;
import com.brinvex.brokercon.core.api.domain.FinTransaction;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public class WithdrawalConstraints extends FinTransactionConstraints {

    WithdrawalConstraints(FinTransaction finTransaction) {
        super(finTransaction);
    }

    @NotNull
    @Override
    public Currency getCcy() {
        return super.getCcy();
    }

    @Negative
    @Override
    public BigDecimal getNetValue() {
        return super.getNetValue();
    }

    @Min(0)
    @Max(0)
    @Override
    public BigDecimal getQty() {
        return super.getQty();
    }

    @Null
    @Override
    public BigDecimal getPrice() {
        return super.getPrice();
    }

    @Null
    @Override
    public Asset getAsset() {
        return super.getAsset();
    }

    @Negative
    @Override
    public BigDecimal getGrossValue() {
        return super.getGrossValue();
    }

    @Min(0)
    @Max(0)
    @NotNull
    @Override
    public BigDecimal getTax() {
        return super.getTax();
    }

    @NegativeOrZero
    @Override
    public BigDecimal getFee() {
        return super.getFee();
    }

}
