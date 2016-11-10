package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        count++;
        sum = sum.add(value);
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
