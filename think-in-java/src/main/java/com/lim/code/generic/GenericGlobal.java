package com.lim.code.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符
 * @author qinhao
 * <p>
 * <? extends Animal>
 * 写：集合元素类型不确定，无法写入
 * 读：集合元素类型可确定，属于Animal类型的，即可读
 * <p>
 * <? super Animal>
 * 写：Animal为集合中不确定类型的子类，可以写入
 * 读：集合中元素只能确定为Object的子类，无法读取
 */
public class GenericGlobal {

    /**
     * 泛型list可以指向Dog,也可以指向Cat,所有list不能确定元素具体类型,所以不能进行写入
     * 但可以确定的是获取的元素属于Animal类型或Animal的子类,所以能进行读取
     */
    @Test
    public void testExtends() {
        List<? extends Animal> list1 = null;
        /* 初始化和写入------------------------------*/
        // list1 = new ArrayList<Biology>(); error,泛型上界
        list1 = new ArrayList<Dog>();
        // list1.add(new Dog());
        list1 = new ArrayList<Cat>();
        // list1.add(new Cat());
        /* 读取-------------------------------------*/
        Biology biology = list1.get(0);
        Animal animal = list1.get(0);
        Dog dog = (Dog) list1.get(0);
        Cat cat = (Cat) list1.get(0);
    }

    /**
     * 泛型指向Animal或者Animal的父类,集合中 <b>不确定类型</b> 属于Animal类型或Animal的子类，所以可以获取到Animal类型的元素，即可读取
     * 但是不可以确定获取元素的类型,所以不能读取
     */
    @Test
    public void testSuper() {
        List<? super Animal> list2 = null;
        /* 初始化和写入------------------------------*/
        // list2 = new ArrayList<Dog>(); error,泛型下限
        list2 = new ArrayList<Biology>();
        // list2.add(new Biology());
        list2.add(new Animal());
        list2.add(new Dog());
        list2.add(new Cat());
        /* 读取-------------------------------------*/
        Object object = list2.get(0);
        Biology biology = (Biology) list2.get(0);
        Animal animal = (Animal) list2.get(0);
        Dog dog = (Dog) list2.get(0);
        Cat cat = (Cat) list2.get(0);
    }

    private class Biology {
    }

    private class Animal extends Biology {
    }

    private class Dog extends Animal {
    }

    private class Cat extends Animal {
    }
}
