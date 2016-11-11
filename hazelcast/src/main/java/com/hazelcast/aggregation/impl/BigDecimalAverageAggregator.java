package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.math.BigDecimal;
import java.util.Map;

public class BigDecimalAverageAggregator<K, V> extends AbstractAggregator<BigDecimal, K, V> {

    private BigDecimal sum = BigDecimal.ZERO;
    private long count;

    public BigDecimalAverageAggregator() {
        super();
    }

    public BigDecimalAverageAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        count++;

        BigDecimal extractedValue = (BigDecimal) extract(entry);
        sum = sum.add(extractedValue);
    }

    @Override
    public void combine(Aggregator aggregator) {
        BigDecimalAverageAggregator doubleAverageAggregator = (BigDecimalAverageAggregator) aggregator;
        this.sum = this.sum.add(doubleAverageAggregator.sum);
        this.count += doubleAverageAggregator.count;
    }

    @Override
    public BigDecimal aggregate() {
        if (count == 0) {
            return null;
        }
        return sum.divide(BigDecimal.valueOf(count));
    }

}
