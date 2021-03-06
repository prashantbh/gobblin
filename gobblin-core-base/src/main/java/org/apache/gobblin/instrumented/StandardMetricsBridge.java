/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.gobblin.instrumented;

import java.util.Collection;

import org.apache.gobblin.metrics.ContextAwareCounter;
import org.apache.gobblin.metrics.ContextAwareGauge;
import org.apache.gobblin.metrics.ContextAwareHistogram;
import org.apache.gobblin.metrics.ContextAwareMeter;
import org.apache.gobblin.metrics.ContextAwareTimer;

/**
 * This interface indicates a class will expose its metrics to some external systems.
 */
public interface StandardMetricsBridge extends Instrumentable {

  StandardMetrics getStandardMetrics();

  interface StandardMetrics {
    String getName();
    Collection<ContextAwareGauge<?>> getGauges();
    Collection<ContextAwareCounter> getCounters();
    Collection<ContextAwareMeter> getMeters();
    Collection<ContextAwareTimer> getTimers();
    Collection<ContextAwareHistogram> getHistograms();
  }
}
