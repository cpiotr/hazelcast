package com.hazelcast.aggregation.impl;

import com.hazelcast.aggregation.Aggregator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DistinctValuesAggregator<R, K, V> extends AbstractAggregator<Set<R>, K, V> {
    Set<R> values = new HashSet<R>();

    public DistinctValuesAggregator() {
        super();
    }

    public DistinctValuesAggregator(String attributePath) {
        super(attributePath);
    }

    @Override
    public void accumulate(Map.Entry<K, V> entry) {
        R extractedValue = (R) extract(entry);
        values.add(extractedValue);
    }

    @Override
    public void combine(Aggregator aggregator) {
        DistinctValuesAggregator distinctValuesAggregator = (DistinctValuesAggregator) aggregator;
        this.values.addAll(distinctValuesAggregator.values);
    }

    @Override
    public Set<R> aggregate() {
        return values;
    }
}
