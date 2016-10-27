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

package com.hazelcast.map.impl.query;

import com.hazelcast.nio.serialization.Data;
import com.hazelcast.projection.Projection;
import com.hazelcast.query.impl.QueryableEntry;
import com.hazelcast.spi.serialization.SerializationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Responsible for populating {@link ProjectionResult}s
 */
public class ProjectionResultProcessor implements ResultProcessor<ProjectionResult> {

    private final SerializationService serializationService;

    public ProjectionResultProcessor(SerializationService serializationService) {
        this.serializationService = serializationService;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ProjectionResult populateResult(Query query, long resultLimit, Collection<QueryableEntry> entries,
                                           Collection<Integer> partitionIds) {
        Projection projection = query.getProjection();
        List<Data> projectedEntries = new ArrayList<Data>();
        for (QueryableEntry entry : entries) {
            projectedEntries.add(serializationService.toData(projection.transform(entry)));
        }
        ProjectionResult result = new ProjectionResult(projectedEntries);
        result.setPartitionIds(partitionIds);
        return result;

    }

    @Override
    public ProjectionResult populateResult(Query query, long resultLimit) {
        return new ProjectionResult();
    }
}
