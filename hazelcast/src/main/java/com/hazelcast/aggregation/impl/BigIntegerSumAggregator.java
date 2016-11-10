package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class BigIntegerSumAggregator<K, V> extends AbstractAggregator<BigInteger, K, V> {

    private BigInteger sum;

    public BigIntegerSumAggregator() {
        super();
    }

    public BigIntegerSumAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        Number extract = (Number) extract(entry);
        if (extract instanceof BigInteger) {
            BigInteger bigInteger = (BigInteger) extract;
            accumulate(bigInteger);
        } else if (extract instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) extract;
            accumulate(bigDecimal.toBigInteger());
        } else {
            accumulate(BigInteger.valueOf(extract.longValue()));
        }
    }

    void accumulate(BigInteger value) {
        sum = sum.add(value);
    }

    @Override
    public void combine(Aggregator aggregator) {
        BigIntegerSumAggregator longSumAggregator = (BigIntegerSumAggregator) aggregator;
        accumulate(longSumAggregator.sum);
    }

    @Override
    public BigInteger aggregate() {
        return sum;
    }

}
