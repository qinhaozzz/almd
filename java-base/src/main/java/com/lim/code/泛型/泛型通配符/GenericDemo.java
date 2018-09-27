package com.lim.code.泛型.泛型通配符;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符
 * @author qinhao
 * <p>
 * <? extends Animal>
 * 写：集合类型不确定，无法写入
 * 读：集合可确定元素属于Animal类型的
 * <p>
 * <? super Animal>
 * 写：Animal为集合中不确定类型的子类，可以写入
 * 读：集合中元素只能确定为Object的子类，无法读取
 */
public class GenericDemo {

    public static void main(String[] args) {
        List<? extends Animal> list = new ArrayList<>();
        Animal animal = list.get(0);
    }
}

class Animal {

}

class Dog extends Animal {

}

class Cat extends Animal {

}
