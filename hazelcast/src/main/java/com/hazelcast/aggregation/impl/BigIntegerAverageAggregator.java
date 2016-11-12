package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.math.BigInteger;
import java.util.Map;

public class BigIntegerAverageAggregator<K, V> extends AbstractAggregator<BigInteger, K, V> {

    private BigInteger sum = BigInteger.ZERO;
    private long count;

    public BigIntegerAverageAggregator() {
        super();
    }

    public BigIntegerAverageAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        count++;

        BigInteger extractedValue = (BigInteger) extract(entry);
        sum = sum.add(extractedValue);
    }

    @Override
    public void combine(Aggregator aggregator) {
        BigIntegerAverageAggregator typedAggregator = (BigIntegerAverageAggregator) aggregator;
        this.sum = this.sum.add(typedAggregator.sum);
        this.count += typedAggregator.count;
    }

    @Override
    public BigInteger aggregate() {
        if (count == 0) {
            return null;
        }
        return sum.divide(BigInteger.valueOf(count));
    }

}
