/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.isis.core.runtime.services.eventbus;

import java.util.Map;

import com.google.common.collect.Maps;

import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.SimpleEventBus;

/**
 * A wrapper for an Axon {@link org.axonframework.eventhandling.SimpleEventBus},
 * allowing arbitrary events to be posted and subscribed to.
 */
public class AxonSimpleEventBusAdapter extends EventBusAdapter {

    private static SimpleEventBus simpleEventBus = new SimpleEventBus();

    private static Map<Object, AxonEventListenerAdapter> adapters = Maps.newConcurrentMap();

    private static AxonEventListenerAdapter adapterFor(final Object domainService) {
        AxonEventListenerAdapter annotationEventListenerAdapter = adapters.get(domainService);
        if (annotationEventListenerAdapter == null) {
            annotationEventListenerAdapter = new AxonEventListenerAdapter(domainService);
            adapters.put(domainService, annotationEventListenerAdapter);
        }
        return annotationEventListenerAdapter;
    }

    @Override
    public void register(final Object domainService) {
        AxonSimpleEventBusAdapter.simpleEventBus.subscribe(AxonSimpleEventBusAdapter.adapterFor(domainService));

    }

    @Override
    public void unregister(final Object domainService) {
        // Seems it's needed to be a no-op (See EventBusService).
        // AxonSimpleEventBusAdapter.simpleEventBus.unsubscribe(AxonSimpleEventBusAdapter.adapterFor(domainService));

    }

    /*
     * {@inheritDoc} 
     * <p> 
     * Logic equivalent to Guava Event Bus. Despite that,
     * event processing cannot be followed after an Exception is thrown.
     */
    @Override
    public void post(final Object event) {
        AxonSimpleEventBusAdapter.simpleEventBus.publish(GenericEventMessage.asEventMessage(event));
    }

}