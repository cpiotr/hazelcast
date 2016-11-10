package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class BigDecimalSumAggregator<K, V> extends AbstractAggregator<BigDecimal, K, V> {

    private BigDecimal sum;

    public BigDecimalSumAggregator() {
        super();
    }

    public BigDecimalSumAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        Number extract = (Number) extract(entry);
        if (extract instanceof BigInteger) {
            BigInteger bigInteger = (BigInteger) extract;
            accumulate(new BigDecimal(bigInteger));
        } else if (extract instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) extract;
            accumulate(bigDecimal);
        } else {
            accumulate(BigDecimal.valueOf(extract.doubleValue()));
        }
    }

    void accumulate(BigDecimal value) {
        sum = sum.add(value);
    }

    @Override
    public void combine(Aggregator aggregator) {
        BigDecimalSumAggregator longSumAggregator = (BigDecimalSumAggregator) aggregator;
        accumulate(longSumAggregator.sum);
    }

    @Override
    public BigDecimal aggregate() {
        return sum;
    }

}
