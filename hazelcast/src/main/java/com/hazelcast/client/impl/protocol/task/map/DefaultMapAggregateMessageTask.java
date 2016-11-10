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

package com.hazelcast.client.impl.protocol.task.map;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.instance.Node;
import com.hazelcast.map.impl.query.AggregationResult;
import com.hazelcast.nio.Connection;
import com.hazelcast.projection.Projection;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.TruePredicate;
import com.hazelcast.util.IterationType;

import java.util.Collection;

public abstract class DefaultMapAggregateMessageTask<P>
        extends AbstractMapQueryMessageTask<P,
        AggregationResult, AggregationResult, AggregationResult> {

    private static final AggregationResult EMPTY_AGGREGATION_RESULT = new AggregationResult();

    public DefaultMapAggregateMessageTask(ClientMessage clientMessage, Node node, Connection connection) {
        super(clientMessage, node, connection);
    }

    @Override
    protected IterationType getIterationType() {
        return IterationType.ENTRY;
    }

    @Override
    protected Projection<?, ?> getProjection() {
        return null;
    }

    @Override
    protected Predicate getPredicate() {
        return TruePredicate.INSTANCE;
    }

    @Override
    protected void extractAndAppendResult(Collection<AggregationResult> results, AggregationResult aggregationResult) {
        results.add(aggregationResult);
    }

    @Override
    protected AggregationResult reduce(Collection<AggregationResult> results) {
        if (results.isEmpty()) {
            return EMPTY_AGGREGATION_RESULT;
        }

        AggregationResult combinedResult = null;
        for (AggregationResult result : results) {
            if (combinedResult == null) {
                combinedResult = result;
            } else {
                combinedResult.combine(result);
            }
        }
        combinedResult.getAggregator().onCombinationFinished();
        return combinedResult;
    }

}