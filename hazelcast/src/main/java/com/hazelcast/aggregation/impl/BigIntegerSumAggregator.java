package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

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
        BigInteger extractedValue = (BigInteger) extract(entry);
        sum = sum.add(extractedValue);
    }

    @Override
    public void combine(Aggregator aggregator) {
        BigIntegerSumAggregator longSumAggregator = (BigIntegerSumAggregator) aggregator;
        sum = sum.add(longSumAggregator.sum);
    }

    @Override
    public BigInteger aggregate() {
        return sum;
    }

}
