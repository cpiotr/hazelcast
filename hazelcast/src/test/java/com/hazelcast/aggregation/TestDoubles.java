package com.hazelcast.aggregation;

import com.hazelcast.map.impl.MapEntrySimple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TestDoubles {
    public static final int NUMBER_OF_SAMPLE_VALUES = 10000;

    static <T> Map.Entry<T, T> createEntryWithValue(T value) {
        return new MapEntrySimple<T, T>(value, value);
    }

    static <T extends Number> List<T> sampleValues(RandomNumberSupplier<T> randomNumberSupplier) {
        List<T> numbers = new ArrayList<T>();
        for (int i = 0; i < NUMBER_OF_SAMPLE_VALUES; i++) {
            numbers.add(randomNumberSupplier.get());
        }
        return numbers;
    }
}
