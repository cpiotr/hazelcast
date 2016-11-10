package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.util.Map;

public class DoubleSumAggregator<K, V> extends AbstractAggregator<Double, K, V> {

    private double sum;

    public DoubleSumAggregator() {
        super();
    }

    public DoubleSumAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        Number extractedValue = (Number) extract(entry);
        sum += extractedValue.doubleValue();
    }

    @Override
    public void combine(Aggregator aggregator) {
        DoubleSumAggregator longSumAggregator = (DoubleSumAggregator) aggregator;
        this.sum += longSumAggregator.sum;
    }

    @Override
    public Double aggregate() {
        return sum;
    }

}
