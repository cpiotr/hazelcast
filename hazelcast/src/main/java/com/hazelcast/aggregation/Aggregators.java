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

import com.hazelcast.aggregation.impl.CountAggregator;
import com.hazelcast.aggregation.impl.DistinctValuesAggregator;

import java.util.Set;

/**
 * A utility class to create basic {@link com.hazelcast.aggregation.Aggregator} instances. <br/>
 * Specialized utility classes exist to provide aggregations based on mathematical operations:
 * <ul>
 *     <li>{@link com.hazelcast.aggregation.AverageAggregators}</li>
 *     <li>{@link com.hazelcast.aggregation.SumAggregators}</li>
 *     <li>{@link com.hazelcast.aggregation.MinAggregators}</li>
 *     <li>{@link com.hazelcast.aggregation.MaxAggregators}</li>
 * </ul>
 */
public final class Aggregators {

    private Aggregators() {
    }

    public static <K, V> Aggregator<Long, K, V> count() {
        return new CountAggregator<K, V>();
    }

    public static <K, V> Aggregator<Long, K, V> count(String attributePath) {
        return new CountAggregator<K, V>(attributePath);
    }

    public static <R, K, V> Aggregator<Set<R>, K, V> distinct() {
        return new DistinctValuesAggregator<R, K, V>();
    }

    public static <R, K, V> Aggregator<Set<R>, K, V> distinct(String attributePath) {
        return new DistinctValuesAggregator<R, K, V>(attributePath);
    }
}
