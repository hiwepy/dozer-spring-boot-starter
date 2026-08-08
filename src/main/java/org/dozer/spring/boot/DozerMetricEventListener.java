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

import org.dozer.DozerEventListener;
import org.dozer.event.DozerEvent;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

/**
 * {@link DozerEventListener} that integrates Dozer mapping events with Dropwizard
 * Metrics, exposing mapping activity through a {@link MetricRegistry}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
public class DozerMetricEventListener implements DozerEventListener {

	MetricRegistry metricRegistry;
	Meter meter;

	/**
	 * Called when a Dozer mapping operation starts.
	 * @param event the Dozer event describing the mapping
	 */
	@Override
	public void mappingStarted(DozerEvent event) {
	}

	/**
	 * Called before a destination value is written during a Dozer mapping.
	 * @param event the Dozer event describing the mapping
	 */
	@Override
	public void preWritingDestinationValue(DozerEvent event) {

	}

	/**
	 * Called after a destination value is written during a Dozer mapping.
	 * @param event the Dozer event describing the mapping
	 */
	@Override
	public void postWritingDestinationValue(DozerEvent event) {

	}

	/**
	 * Called when a Dozer mapping operation finishes.
	 * @param event the Dozer event describing the mapping
	 */
	@Override
	public void mappingFinished(DozerEvent event) {

	}

}
