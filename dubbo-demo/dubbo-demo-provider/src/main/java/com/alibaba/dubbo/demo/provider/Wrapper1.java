package com.alibaba.dubbo.demo.provider;//package com.alibaba.dubbo.demo.consumer;

import com.alibaba.dubbo.common.bytecode.ClassGenerator;
import com.alibaba.dubbo.common.bytecode.NoSuchMethodException;
import com.alibaba.dubbo.common.bytecode.NoSuchPropertyException;
import com.alibaba.dubbo.common.bytecode.Wrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Wrapper1 extends Wrapper implements ClassGenerator.DC {
    public static String[] pns;
    public static Map pts;
    public static String[] mns;
    public static String[] dmns;
    public static Class[] mts0;

    public String[] getPropertyNames() {
        return pns;
    }

    public boolean hasProperty(String string) {
        return pts.containsKey(string);
    }
    public Class getPropertyType(String string) {
        return (Class)pts.get(string);
    }

    public String[] getMethodNames() {
        return mns;
    }

    public String[] getDeclaredMethodNames() {
        return dmns;
    }

    public void setPropertyValue(Object object, String string, Object object2) {
        try {
            DemoServiceImpl demoServiceImpl = (DemoServiceImpl)object;
        }
        catch (Throwable throwable) {
            throw new IllegalArgumentException(throwable);
        }
        throw new NoSuchPropertyException(new StringBuffer().append("Not found property\"").append(string).append("\" filed or setter method in class com.alibaba.dubbo.demo.provider.DemoServiceImpl.").toString());
    }
    public Object getPropertyValue(Object object, String string) {
        try {
            DemoServiceImpl demoServiceImpl = (DemoServiceImpl)object;
        }
        catch (Throwable throwable) {
            throw new IllegalArgumentException(throwable);
        }
        throw new NoSuchPropertyException(new StringBuffer().append("Not found property\"").append(string).append("\" filed or setter method in class com.alibaba.dubbo.demo.provider.DemoServiceImpl.").toString());
    }
    // 这个就是Wrapper对象的invokerMethod方法
    public Object invokeMethod(Object object, String string, Class[] arrclass,
                               Object[] arrobject) throws InvocationTargetException {
        DemoServiceImpl demoServiceImpl;
        try {
            demoServiceImpl = (DemoServiceImpl)object;
        }
        catch (Throwable throwable) {
            throw new IllegalArgumentException(throwable);
        }
        try {
            if ("sayHello".equals(string) && arrclass.length == 1) {
                // 调用DemoServiceImpl实例对象的sayHello方法,并将结果返回
                return demoServiceImpl.sayHello((String)arrobject[0]);
            }
        }
        catch (Throwable throwable) {
            throw new InvocationTargetException(throwable);
        }
        throw new NoSuchMethodException(new StringBuffer().append("Not found method \"")
                .append(string)
                .append("\" in class com.alibaba.dubbo.demo.provider.DemoServiceImpl.").toString());
    }
}