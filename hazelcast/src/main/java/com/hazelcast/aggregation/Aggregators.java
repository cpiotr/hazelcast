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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A utility class to create {@link com.hazelcast.aggregation.Aggregator} instances.
 */
public final class Aggregators {

    private Aggregators() {
    }

    public static <K, V> Aggregator<BigDecimal, K, V> bigDecimalAvg() {
        return new BigDecimalAverageAggregator<K, V>();
    }


    public static Aggregator<BigInteger, BigInteger, BigInteger> bigIntegerAvg() {
        return new BigIntegerAverageAggregator<BigInteger, BigInteger>();
    }

    public static Aggregator<Double, Double, Double> doubleAvg() {
        return new DoubleAverageAggregator<Double, Double>();
    }

    public static Aggregator<Double, Integer, Integer> integerAvg() {
        return new DoubleAverageAggregator<Integer, Integer>();
    }

    public static Aggregator<Double, Long, Long> longAvg() {
        return new DoubleAverageAggregator<Long, Long>();
    }
}
