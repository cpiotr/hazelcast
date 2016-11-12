package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.math.BigDecimal;
import java.util.Map;

public class BigDecimalSumAggregator<K, V> extends AbstractAggregator<BigDecimal, K, V> {

    private BigDecimal sum = BigDecimal.ZERO;

    public BigDecimalSumAggregator() {
        super();
    }

    public BigDecimalSumAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        BigDecimal extractedValue = (BigDecimal) extract(entry);
        sum = sum.add(extractedValue);
    }

    @Override
    public void combine(Aggregator aggregator) {
        BigDecimalSumAggregator longSumAggregator = (BigDecimalSumAggregator) aggregator;
        sum = sum.add(longSumAggregator.sum);
    }

    @Override
    public BigDecimal aggregate() {
        return sum;
    }

}
