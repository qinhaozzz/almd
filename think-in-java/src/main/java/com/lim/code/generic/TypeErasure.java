package com.lim.code.generic;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型擦除
 * @author qinhao
 * <p>
 * 1.真实泛型：编译器会为每一个泛型类生成一份代码，会导致<b>代码膨胀</b>
 * 2.伪泛型：为每一个泛型只生成一份代码，该泛型类的所有实例都会使用该份代码。通过<b>type erasure</b>类型擦除实现的
 * <p>
 * Type Erasure
 * 1.将所有泛型参数用其最顶级父类类型替换
 * 2.移除所有类型参数
 */
public class TypeErasure {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 1.String Integer在编译之后被擦除
        Map<String, Integer> stringIntegerMap = new HashMap<>(16);
        stringIntegerMap.put("id", 1001);
        stringIntegerMap.put("height", 177);
        stringIntegerMap.getClass().getMethod("put", Object.class, Object.class).invoke(stringIntegerMap, 123, "abc");
        System.out.println(stringIntegerMap.size());
        System.out.println("========");
        // 2.泛型实例被擦除,只剩原始类型
        Map<String, String> stringStringMap = new HashMap<>(16);
        System.out.println(stringIntegerMap.getClass() == stringStringMap.getClass());
    }
}
