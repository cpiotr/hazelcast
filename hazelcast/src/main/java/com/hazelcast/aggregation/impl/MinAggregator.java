package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;
import com.hazelcast.map.impl.MapEntrySimple;

import java.math.BigInteger;
import java.util.Map;

public class MinAggregator<T extends Number & Comparable, K, V> extends AbstractAggregator<T, K, V> {

    private T min;

    public MinAggregator() {
        super();
    }

    public MinAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        T extractedValue = (T) extract(entry);

        if (min == null || isCurrentlyGreaterThan(extractedValue)) {
            min = extractedValue;
        }
    }

    private boolean isCurrentlyGreaterThan(T otherValue) {
        return min.compareTo(otherValue) > 0;
    }

    @Override
    public void combine(Aggregator aggregator) {
        MinAggregator maxAggregator = (MinAggregator) aggregator;
        T valueFromOtherAggregator = (T) maxAggregator.min;
        if (isCurrentlyGreaterThan(valueFromOtherAggregator)) {
            this.min = valueFromOtherAggregator;
        }
    }

    @Override
    public T aggregate() {
        return min;
    }

    public static void main(String[] args) {
        MinAggregator<BigInteger, String, BigInteger> aggregator = new MinAggregator<BigInteger, String, BigInteger>();
        aggregator.accumulate(new MapEntrySimple<String, BigInteger>("a", BigInteger.valueOf(746464)));
        aggregator.accumulate(new MapEntrySimple<String, BigInteger>("b", BigInteger.ONE));
        aggregator.accumulate(new MapEntrySimple<String, BigInteger>("c", BigInteger.TEN));
        System.out.println(aggregator.aggregate());

    }
}
