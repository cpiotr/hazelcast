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

import com.hazelcast.aggregation.impl.BigDecimalSumAggregator;
import com.hazelcast.aggregation.impl.BigIntegerSumAggregator;
import com.hazelcast.aggregation.impl.DoubleSumAggregator;
import com.hazelcast.aggregation.impl.FixedSumAggregator;
import com.hazelcast.aggregation.impl.FloatingPointSumAggregator;
import com.hazelcast.aggregation.impl.IntegerSumAggregator;
import com.hazelcast.aggregation.impl.LongSumAggregator;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A utility class to create {@link com.hazelcast.aggregation.Aggregator} instances for calculating the sum.
 */
public final class SumAggregators {

    private SumAggregators() {
        // Utility class
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalSum() {
        return new BigDecimalSumAggregator<K, V>();
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalSum(String attributePath) {
        return new BigDecimalSumAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<BigInteger, K, V> bigIntegerSum() {
        return new BigIntegerSumAggregator<K, V>();
    }

    public static <K, V> Aggregator<BigInteger, K, V> bigIntegerSum(String attributePath) {
        return new BigIntegerSumAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> doubleSum() {
        return new DoubleSumAggregator<K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> doubleSum(String attributePath) {
        return new DoubleSumAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Long, K, V> integerSum() {
        return new IntegerSumAggregator<K, V>();
    }

    public static <K, V> Aggregator<Long, K, V> integerSum(String attributePath) {
        return new IntegerSumAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Long, K, V> longSum() {
        return new LongSumAggregator<K, V>();
    }

    public static <K, V> Aggregator<Long, K, V> longSum(String attributePath) {
        return new LongSumAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Long, K, V> fixedPointSum() {
        return new FixedSumAggregator<K, V>();
    }

    public static <K, V> Aggregator<Long, K, V> fixedPointSum(String attributePath) {
        return new FixedSumAggregator<K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> floatingPointSum() {
        return new FloatingPointSumAggregator<K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> floatingPointSum(String attributePath) {
        return new FloatingPointSumAggregator<K, V>(attributePath);
    }
}
