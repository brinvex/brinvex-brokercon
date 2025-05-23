package com.brinvex.brokercon.core.api.domain;

import com.brinvex.fintypes.vo.DateAmount;

import java.util.List;
import java.util.SequencedCollection;

public record PtfActivity(
        SequencedCollection<FinTransaction> transactions,
        SequencedCollection<DateAmount> netAssetValues
) {

    public PtfActivity(SequencedCollection<FinTransaction> transactions, SequencedCollection<DateAmount> netAssetValues) {
        this.transactions = transactions == null ? null : List.copyOf(transactions);
        this.netAssetValues = netAssetValues == null ? null : List.copyOf(netAssetValues);
    }
}
