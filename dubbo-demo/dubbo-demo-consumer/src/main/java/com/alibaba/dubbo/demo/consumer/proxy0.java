package com.alibaba.dubbo.demo.consumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

import com.alibaba.dubbo.common.bytecode.ClassGenerator;
import com.alibaba.dubbo.demo.DemoService;
import com.alibaba.dubbo.demo.ParamCallback;
import com.alibaba.dubbo.demo.entity.User;
import com.alibaba.dubbo.demo.enumx.Sex;
import com.alibaba.dubbo.rpc.service.EchoService;

public class proxy0
        implements ClassGenerator.DC, EchoService, DemoService
{
    public static Method[] methods;
    private InvocationHandler handler;

    public String sayHello(String paramString)
    {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramString;
        Object localObject = this.handler.invoke(this, methods[0], arrayOfObject);
        return ((String)localObject);
    }

    @Override
    public void bye(Object o) {

    }

    @Override
    public void callbackParam(String msg, ParamCallback callback) {

    }

    @Override
    public String say01(String msg) {
        return null;
    }

    @Override
    public String[] say02() {
        return new String[0];
    }

    @Override
    public void say03() {

    }

    @Override
    public Void say04() {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user, Boolean vip) {

    }

    @Override
    public void saves(Collection<User> users) {

    }

    @Override
    public void saves(User[] users) {

    }

    @Override
    public void demo(String name, String password, User user) {

    }

    @Override
    public void demo(Sex sex) {

    }

    @Override
    public void hello(String name) {

    }

    @Override
    public void hello01(String name) {

    }

    @Override
    public void hello02(String name) {

    }

    public Object $echo(Object paramObject)
    {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramObject;
        Object localObject = this.handler.invoke(this, methods[1], arrayOfObject);
        return ((Object)localObject);
    }

    public proxy0()
    {
    }

    //   return (T) Proxy.getProxy(interfaces).newInstance(new InvokerInvocationHandler(invoker));
    public proxy0(InvocationHandler paramInvocationHandler)
    {
        this.handler = paramInvocationHandler;
    }
}