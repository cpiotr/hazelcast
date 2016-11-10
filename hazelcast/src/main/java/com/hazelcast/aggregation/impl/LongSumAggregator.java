package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.util.Map;

public class LongSumAggregator<K, V> extends AbstractAggregator<Long, K, V> {

    private long sum;

    public LongSumAggregator() {
        super();
    }

    public LongSumAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        Number extractedValue = (Number) extract(entry);
        sum += extractedValue.longValue();
    }

    @Override
    public void combine(Aggregator aggregator) {
        LongSumAggregator longSumAggregator = (LongSumAggregator) aggregator;
        this.sum += longSumAggregator.sum;
    }

    @Override
    public Long aggregate() {
        return sum;
    }

}
