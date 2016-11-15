/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.aggregation;

import com.hazelcast.aggregation.impl.BigDecimalAverageAggregator;
import com.hazelcast.aggregation.impl.BigIntegerAverageAggregator;
import com.hazelcast.aggregation.impl.DoubleAverageAggregator;
import com.hazelcast.aggregation.impl.IntegerAverageAggregator;
import com.hazelcast.aggregation.impl.LongAverageAggregator;
import com.hazelcast.aggregation.impl.NumberAverageAggregator;

import java.math.BigDecimal;

/**
 * A utility class to create {@link com.hazelcast.aggregation.Aggregator} instances for calculating the average value.
 */
public final class AverageAggregators {

    private AverageAggregators() {
        // Utility class
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalAvg() {
        return new BigDecimalAverageAggregator<K, V>();
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalAvg(String attributePath) {
        return new BigDecimalAverageAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigIntegerAvg() {
        return new BigIntegerAverageAggregator<K, V>();
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigIntegerAvg(String attributePath) {
        return new BigIntegerAverageAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> doubleAvg() {
        return new DoubleAverageAggregator<K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> doubleAvg(String attributePath) {
        return new DoubleAverageAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> integerAvg() {
        return new IntegerAverageAggregator<K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> integerAvg(String attributePath) {
        return new IntegerAverageAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> longAvg() {
        return new LongAverageAggregator<K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> longAvg(String attributePath) {
        return new LongAverageAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> genericAvg() {
        return new NumberAverageAggregator<K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> genericAvg(String attributePath) {
        return new NumberAverageAggregator<K, V>(attributePath);
    }
}
