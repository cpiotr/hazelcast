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

import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelTest.class})
public class AvgAggregationTest {

    public static final double ERROR = 1e-8;

    @Test(timeout = 60000)
    public void testBigDecimalAvg() throws Exception {

        List<BigDecimal> values = TestDoubles.sampleValues(new RandomNumberSupplier<BigDecimal>() {
            @Override
            protected BigDecimal mapFrom(Number value) {
                return BigDecimal.valueOf(value.doubleValue());
            }
        });

        BigDecimal expectation = Sums.sumBigDecimals(values);
        expectation = expectation.divide(BigDecimal.valueOf(values.size()));

        Aggregator<BigDecimal, BigDecimal, BigDecimal> aggregation = Aggregators.bigDecimalAvg();
        for (BigDecimal value : values) {
            aggregation.accumulate(TestDoubles.createEntryWithValue(value));
        }
        BigDecimal result = aggregation.aggregate();

        assertThat(result, is(equalTo(expectation)));
    }

    @Test(timeout = 60000)
    public void testBigIntegerAvg() throws Exception {

        List<BigInteger> values = TestDoubles.sampleValues(new RandomNumberSupplier<BigInteger>() {
            @Override
            protected BigInteger mapFrom(Number value) {
                return BigInteger.valueOf(value.longValue());
            }
        });

        BigInteger expectation = Sums.sumBigIntegers(values)
                .divide(BigInteger.valueOf(values.size()));

        Aggregator<BigInteger, BigInteger, BigInteger> aggregation = Aggregators.bigIntegerAvg();
        for (BigInteger value : values) {
            aggregation.accumulate(TestDoubles.createEntryWithValue(value));
        }
        BigInteger result = aggregation.aggregate();

        assertThat(result, is(equalTo(expectation)));
    }

    @Test(timeout = 60000)
    public void testDoubleAvg() throws Exception {

        List<Double> values = TestDoubles.sampleValues(new RandomNumberSupplier<Double>() {
            @Override
            protected Double mapFrom(Number value) {
                return value.doubleValue();
            }
        });

        double expectation = Sums.sumDoubles(values) / (double) values.size();

        Aggregator<Double, Double, Double> aggregation = Aggregators.doubleAvg();
        for (Double value : values) {
            aggregation.accumulate(TestDoubles.createEntryWithValue(value));
        }
        Double result = aggregation.aggregate();

        assertThat(result, is(closeTo(expectation, ERROR)));
    }

    @Test(timeout = 60000)
    public void testIntegerAvg() throws Exception {

        List<Integer> values = TestDoubles.sampleValues(new RandomNumberSupplier<Integer>() {
            @Override
            protected Integer mapFrom(Number value) {
                return value.intValue();
            }
        });

        double expectation = (double) Sums.sumIntegers(values) / (double) values.size();

        Aggregator<Double, Integer, Integer> aggregation = Aggregators.integerAvg();
        for (Integer value : values) {
            aggregation.accumulate(TestDoubles.createEntryWithValue(value));
        }
        double result = aggregation.aggregate();

        assertThat(result, is(closeTo(expectation, ERROR)));
    }

    @Test(timeout = 60000)
    public void testLongAvg() throws Exception {

        List<Long> values = TestDoubles.sampleValues(new RandomNumberSupplier<Long>() {
            @Override
            protected Long mapFrom(Number value) {
                return value.longValue();
            }
        });

        double expectation = (double) Sums.sumLongs(values) / (double) values.size();

        Aggregator<Double, Long, Long> aggregation = Aggregators.longAvg();
        for (Long value : values) {
            aggregation.accumulate(TestDoubles.createEntryWithValue(value));
        }
        double result = aggregation.aggregate();

        assertThat(result, is(closeTo(expectation, ERROR)));
    }

}