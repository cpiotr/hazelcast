package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;
import com.hazelcast.map.impl.MapEntrySimple;

import java.math.BigInteger;
import java.util.Map;

public class MaxAggregator<T extends Number & Comparable, K, V> extends AbstractAggregator<T, K, V> {

    private T max;

    public MaxAggregator() {
        super();
    }

    public MaxAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        T extractedValue = (T) extract(entry);

        if (max == null || isCurrentlyLessThan(extractedValue)) {
            max = extractedValue;
        }
    }

    private boolean isCurrentlyLessThan(T extractedValue) {
        return max.compareTo(extractedValue) < 0;
    }

    @Override
    public void combine(Aggregator aggregator) {
        MaxAggregator maxAggregator = (MaxAggregator) aggregator;
        T valueFromOtherAggregator = (T) maxAggregator.max;
        if (isCurrentlyLessThan(valueFromOtherAggregator)) {
            this.max = valueFromOtherAggregator;
        }
    }

    @Override
    public T aggregate() {
        return max;
    }

    public static void main(String[] args) {
        MaxAggregator<BigInteger, String, BigInteger> aggregator = new MaxAggregator<BigInteger, String, BigInteger>();
        aggregator.accumulate(new MapEntrySimple<String, BigInteger>("a", BigInteger.ONE));
        aggregator.accumulate(new MapEntrySimple<String, BigInteger>("b", BigInteger.TEN));
        aggregator.accumulate(new MapEntrySimple<String, BigInteger>("c", BigInteger.valueOf(746464)));
        System.out.println(aggregator.aggregate());

    }
}
