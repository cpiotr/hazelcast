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

import com.hazelcast.aggregation.impl.MinAggregator;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A utility class to create {@link com.hazelcast.aggregation.Aggregator} instances for calculating the minimum value.
 */
public final class MinAggregators {

    private MinAggregators() {
        // Utility class
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalMin() {
        return new MinAggregator<BigDecimal, K, V>();
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalMin(String attributePath) {
        return new MinAggregator<BigDecimal, K, V>(attributePath);
    }

    public static <K, V> Aggregator<BigInteger, K, V> bigIntegerMin() {
        return new MinAggregator<BigInteger, K, V>();
    }

    public static <K, V> Aggregator<BigInteger, K, V> bigIntegerMin(String attributePath) {
        return new MinAggregator<BigInteger, K, V>(attributePath);
    }

    public static <K, V> Aggregator<Double, K, V> doubleMin() {
        return new MinAggregator<Double, K, V>();
    }

    public static <K, V> Aggregator<Double, K, V> doubleMin(String attributePath) {
        return new MinAggregator<Double, K, V>(attributePath);
    }

    public static <K, V> Aggregator<Integer, K, V> integerMin() {
        return new MinAggregator<Integer, K, V>();
    }

    public static <K, V> Aggregator<Integer, K, V> integerMin(String attributePath) {
        return new MinAggregator<Integer, K, V>(attributePath);
    }

    public static <K, V> Aggregator<Long, K, V> longMin() {
        return new MinAggregator<Long, K, V>();
    }

    public static <K, V> Aggregator<Long, K, V> longMin(String attributePath) {
        return new MinAggregator<Long, K, V>(attributePath);
    }

    public static <R extends Comparable, K, V> Aggregator<R, K, V> comparableMin() {
        return new MinAggregator<R, K, V>();
    }

    public static <R extends Comparable, K, V> Aggregator<R, K, V> comparableMin(String attributePath) {
        return new MinAggregator<R, K, V>(attributePath);
    }
}
