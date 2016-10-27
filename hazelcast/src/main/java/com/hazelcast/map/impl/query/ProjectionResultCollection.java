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
import com.hazelcast.spi.serialization.SerializationService;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ProjectionResultCollection<E> extends AbstractSet<E> {

    private final Collection<Data> rows;
    private final SerializationService serializationService;

    public ProjectionResultCollection(SerializationService serializationService, boolean unique) {
        this.serializationService = serializationService;
        if (unique) {
            rows = new HashSet<Data>();
        } else {
            rows = new ArrayList<Data>();
        }
    }

    public ProjectionResultCollection(SerializationService serializationService, boolean unique, Collection<Data> result) {
        this(serializationService, unique);
        addAllRows(result);
    }

    // just for testing
    Collection<Data> getRows() {
        return rows;
    }

    public void addAllRows(Collection<Data> collection) {
        rows.addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        return new ProjectionResultIterator(rows.iterator(), serializationService);
    }

    @Override
    public int size() {
        return rows.size();
    }
}
