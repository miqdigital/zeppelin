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
package org.apache.zeppelin.socket;

import java.util.List;

import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import jakarta.websocket.server.ServerEndpointConfig.Configurator;

import org.apache.zeppelin.util.WatcherSecurityKey;
import org.apache.zeppelin.utils.CorsUtils;
import org.glassfish.hk2.api.ServiceLocator;

/**
 * This class set headers to websocket sessions and inject hk2 when initiating instances by ServerEndpoint annotation.
 */
public class SessionConfigurator extends Configurator {

  private final ServiceLocator serviceLocator;

  public SessionConfigurator(ServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }

  @Override
  public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request,
                              HandshakeResponse response) {
    List<String> holder;
    holder = request.getHeaders().get(WatcherSecurityKey.HTTP_HEADER);
    sec.getUserProperties().put(WatcherSecurityKey.HTTP_HEADER,
        null != holder && !holder.isEmpty() ? holder.get(0) : null);
    holder = request.getHeaders().get(CorsUtils.HEADER_ORIGIN);
    sec.getUserProperties().put(CorsUtils.HEADER_ORIGIN,
        null != holder && !holder.isEmpty() ? holder.get(0) : null);
  }

  @Override
  public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
    return serviceLocator.getService(endpointClass);
  }
}
