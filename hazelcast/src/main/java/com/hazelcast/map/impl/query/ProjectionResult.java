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

import com.hazelcast.map.impl.MapDataSerializerHook;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Contains the result of a projection evaluation.
 * <p>
 * A ProjectionResult is a collections of {@link Data} instances.
 */
public class ProjectionResult implements Result<ProjectionResult>, IdentifiedDataSerializable {

    private final Collection<Data> rows;
    private Collection<Integer> partitionIds;

    public ProjectionResult() {
        this.rows = new LinkedList<Data>();
    }

    public ProjectionResult(List<Data> rows) {
        this.rows = rows;
    }

    public int size() {
        return rows.size();
    }

    public boolean isEmpty() {
        return rows.isEmpty();
    }

    public Collection<Integer> getPartitionIds() {
        return partitionIds;
    }

    @Override
    public void combine(ProjectionResult result) {
        if (partitionIds == null) {
            partitionIds = new ArrayList<Integer>(result.getPartitionIds().size());
        }
        partitionIds.addAll(result.getPartitionIds());
        rows.addAll(result.getRows());
    }

    public void setPartitionIds(Collection<Integer> partitionIds) {
        this.partitionIds = partitionIds;
    }

    public Collection<Data> getRows() {
        return rows;
    }

    @Override
    public int getFactoryId() {
        return MapDataSerializerHook.F_ID;
    }

    @Override
    public int getId() {
        return MapDataSerializerHook.PROJECTION_RESULT;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        int partitionSize = (partitionIds == null) ? 0 : partitionIds.size();
        out.writeInt(partitionSize);
        if (partitionSize > 0) {
            for (Integer partitionId : partitionIds) {
                out.writeInt(partitionId);
            }
        }

        int resultSize = rows.size();
        out.writeInt(resultSize);
        if (resultSize > 0) {
            for (Data row : rows) {
                out.writeData(row);
            }
        }
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        int partitionSize = in.readInt();
        if (partitionSize > 0) {
            partitionIds = new ArrayList<Integer>(partitionSize);
            for (int i = 0; i < partitionSize; i++) {
                partitionIds.add(in.readInt());
            }
        }

        int resultSize = in.readInt();
        if (resultSize > 0) {
            for (int i = 0; i < resultSize; i++) {
                Data row = in.readData();
                rows.add(row);
            }
        }
    }

}
