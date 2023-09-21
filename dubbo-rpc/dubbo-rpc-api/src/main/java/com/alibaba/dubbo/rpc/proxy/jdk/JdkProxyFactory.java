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
package com.alibaba.dubbo.rpc.proxy.jdk;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.proxy.AbstractProxyFactory;
import com.alibaba.dubbo.rpc.proxy.AbstractProxyInvoker;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JavaassistRpcProxyFactory
 */
public class JdkProxyFactory extends AbstractProxyFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] interfaces) {
        /**
         * ClassLoader loader：它是类加载器类型，你不用去理睬它，你只需要知道怎么可以获得它就可以了：MyInterface.class.getClassLoader()就可以获取到ClassLoader对象，没错，只要你有一个Class对象就可以获取到ClassLoader对象；Class[] interfaces：指定newProxyInstance()方法返回的对象要实现哪些接口，没错，可以指定多个接口，例如上面例子只我们只指定了一个接口：Class[] cs = {MyInterface.class};InvocationHandler h：它是最重要的一个参数！它是一个接口！它的名字叫调用处理器！无论你调用代理对象的什么方法，它都是在调用InvocationHandler的invoke()方法！
         */
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, new InvokerInvocationHandler(invoker));
    }

    public <T> Invoker<T> getInvoker(T proxy, Class<T> type, URL url) {
        return new AbstractProxyInvoker<T>(proxy, type, url) {
            @Override
            protected Object doInvoke(T proxy, String methodName,
                                      Class<?>[] parameterTypes,
                                      Object[] arguments) throws Throwable {
                // 获得方法
                Method method = proxy.getClass().getMethod(methodName, parameterTypes);
                // 调用方法
                return method.invoke(proxy, arguments);
            }
        };
    }

}