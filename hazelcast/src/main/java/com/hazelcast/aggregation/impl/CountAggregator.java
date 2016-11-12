package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.util.Map;

public class CountAggregator<K, V> extends AbstractAggregator<Long, K, V> {
    private long count;

    public CountAggregator() {
        super();
    }

    public CountAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        count++;
    }

    @Override
    public void combine(Aggregator aggregator) {
        CountAggregator countAggregator = (CountAggregator) aggregator;
        this.count += countAggregator.count;
    }

    @Override
    public Long aggregate() {
        return count;
    }
}
