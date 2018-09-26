package com.lim.code.泛型.泛型通配符;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符
 * @author qinhao
 */
public class GenericDemo {

    public static void main(String[] args) {
        List<? extends Number> list = new ArrayList<>();
        Number i = Integer.valueOf(1);

    }
}
