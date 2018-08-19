/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.dubbo.common.store.support;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.dubbo.common.store.DataStore;

/**
 * 基于内存的数据存储实现
 * 放了线程池
 */
public class SimpleDataStore implements DataStore {

    // <component name or id, <data-name, data-value>>
    private ConcurrentMap<String, ConcurrentMap<String, Object>> data = new ConcurrentHashMap<String, ConcurrentMap<String, Object>>();

    public Map<String, Object> get(String componentName) {
        ConcurrentMap<String, Object> value = data.get(componentName);
        if (value == null) return new HashMap<String, Object>();

        return new HashMap<String, Object>(value);
    }

    public Object get(String componentName, String key) {
        if (!data.containsKey(componentName)) {
            return null;
        }
        return data.get(componentName).get(key);
    }

    public void put(String componentName, String key, Object value) {
        Map<String, Object> componentData = data.get(componentName);
        if (null == componentData) {
            data.putIfAbsent(componentName, new ConcurrentHashMap<String, Object>());
            componentData = data.get(componentName);
        }
        componentData.put(key, value);
    }

    public void remove(String componentName, String key) {
        if (!data.containsKey(componentName)) {
            return;
        }
        data.get(componentName).remove(key);
    }

}