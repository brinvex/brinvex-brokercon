package com.brinvex.brokercon.adapter.ibkr.api.model.statement;

import com.brinvex.fintypes.enu.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CashTransaction(
        LocalDate reportDate,
        Currency currency,
        BigDecimal fxRateToBase,
        AssetCategory assetCategory,
        AssetSubCategory assetSubCategory,
        String symbol,
        String description,
        String securityID,
        SecurityIDType securityIDType,
        String figi,
        String isin,
        String listingExchange,
        CashTransactionType type,
        String transactionID,
        String actionID,
        LocalDate settleDate,
        BigDecimal amount,
        String extraDateTimeStr
) {
}
