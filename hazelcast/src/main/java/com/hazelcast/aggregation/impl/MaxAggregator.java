package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.util.Map;

public class MaxAggregator<T extends Comparable, K, V> extends AbstractAggregator<T, K, V> {

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
}
