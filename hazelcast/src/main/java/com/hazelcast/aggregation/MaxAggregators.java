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

import com.hazelcast.aggregation.impl.MaxAggregator;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A utility class to create {@link com.hazelcast.aggregation.Aggregator} instances for calculating maximum value.
 */
public final class MaxAggregators {

    private MaxAggregators() {
        // Utility class
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalMax() {
        return new MaxAggregator<BigDecimal, K, V>();
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalMax(String attributePath) {
        return new MaxAggregator<BigDecimal, K, V>(attributePath);
    }

    public static <K, V> Aggregator<BigInteger, K, V> bigIntegerMax() {
        return new MaxAggregator<BigInteger, K, V>();
    }

    public static <K, V> Aggregator<BigInteger, K, V> bigIntegerMax(String attributePath) {
        return new MaxAggregator<BigInteger, K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> doubleMax() {
        return new MaxAggregator<Double, K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> doubleMax(String attributePath) {
        return new MaxAggregator<Double, K, V>(attributePath);
    }

    public static <K, V> Aggregator<Integer, K, V> integerMax() {
        return new MaxAggregator<Integer, K, V>();
    }

    public static <K, V> Aggregator<Integer, K, V> integerMax(String attributePath) {
        return new MaxAggregator<Integer, K, V>(attributePath);
    }

    public static <K, V> Aggregator<Long, K, V> longMax() {
        return new MaxAggregator<Long, K, V>();
    }

    public static <K, V> Aggregator<Long, K, V> longMax(String attributePath) {
        return new MaxAggregator<Long, K, V>(attributePath);
    }

    public static <R extends Comparable, K, V> Aggregator<R, K, V> comparableMax() {
        return new MaxAggregator<R, K, V>();
    }

    public static <R extends Comparable, K, V> Aggregator<R, K, V> comparableMax(String attributePath) {
        return new MaxAggregator<R, K, V>(attributePath);
    }
}
