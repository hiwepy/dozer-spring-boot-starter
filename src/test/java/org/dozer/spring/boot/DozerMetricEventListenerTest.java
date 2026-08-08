/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.dozer.spring.boot;

import org.dozer.event.DozerEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codahale.metrics.MetricRegistry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for {{ @link DozerMetricEventListener }}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("DozerMetricEventListener Tests")
class DozerMetricEventListenerTest {

    @Test
    @DisplayName("Instance can be created via default constructor")
    void instance() {
        DozerMetricEventListener listener = new DozerMetricEventListener();
        assertThat(listener).isNotNull();
    }

    @Test
    @DisplayName("metricRegistry and meter fields should be mutable")
    void fieldsAreMutable() throws Exception {
        DozerMetricEventListener listener = new DozerMetricEventListener();
        MetricRegistry registry = new MetricRegistry();

        java.lang.reflect.Field regField = DozerMetricEventListener.class.getDeclaredField("metricRegistry");
        regField.setAccessible(true);
        regField.set(listener, registry);
        assertThat(regField.get(listener)).isSameAs(registry);

        java.lang.reflect.Field meterField = DozerMetricEventListener.class.getDeclaredField("meter");
        meterField.setAccessible(true);
        meterField.set(listener, registry.meter("dozer"));
        assertThat(meterField.get(listener)).isNotNull();
    }

    @Test
    @DisplayName("All four lifecycle callbacks should accept a DozerEvent without throwing")
    void lifecycleCallbacksShouldAcceptEvent() {
        DozerMetricEventListener listener = new DozerMetricEventListener();
        DozerEvent event = mock(DozerEvent.class);

        listener.mappingStarted(event);
        listener.preWritingDestinationValue(event);
        listener.postWritingDestinationValue(event);
        listener.mappingFinished(event);
    }

    @Test
    @DisplayName("Lifecycle callbacks should tolerate null events")
    void lifecycleCallbacksShouldTolerateNull() {
        DozerMetricEventListener listener = new DozerMetricEventListener();
        listener.mappingStarted(null);
        listener.preWritingDestinationValue(null);
        listener.postWritingDestinationValue(null);
        listener.mappingFinished(null);
    }

}
