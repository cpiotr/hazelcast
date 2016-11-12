package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.util.Map;

public class MinAggregator<T extends Comparable, K, V> extends AbstractAggregator<T, K, V> {

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
}
