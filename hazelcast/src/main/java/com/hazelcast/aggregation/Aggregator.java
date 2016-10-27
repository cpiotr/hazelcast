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

import java.io.Serializable;
import java.util.Map;

/**
 * Base class for all aggregators. Exposes API for parallel two-phase aggregations:
 * - accumulation of entries by multiple instance of aggregators
 * - combining all aggregators into one to calculate the final result
 * <p>
 * Aggregator does not have to be thread-safe.
 * accumulate() and combine() calls may be interwoven.
 * <p>
 * The very instance passed to an aggregate() method will not be used at all. It is just a prototype object
 * that will be cloned using serialization, since each partition gets its own instance of an aggregator.
 * In this way the aggregator is not used by multiple-threads. Each thread gets its own aggregator instance.
 *
 * @param <R> aggregation result
 * @param <K> entry key type
 * @param <V> entry value type
 */
public abstract class Aggregator<R, K, V> implements Serializable {

    /**
     * @param entry entries to accumulate.
     */
    public abstract void accumulate(Map.Entry<K, V> entry);

    /**
     * Called after the last call to combine on a specific instance, enables disposing of the intermediary state.
     */
    public void onAccumulationFinished() {
    }

    /**
     * @param aggregator aggregator providing intermediary results to be combined into the results of this aggregator.
     */
    public abstract void combine(Aggregator aggregator);

    /**
     * Called after the last call to combine on a specific instance, enables disposing of the intermediary state.
     */
    public void onCombinationFinished() {
    }

    /**
     * Returns the result of the aggregation. The result may be calculated in this call or cached by the aggregator.
     *
     * @return returns the result of the aggregation.
     */
    public abstract R aggregate();

}
