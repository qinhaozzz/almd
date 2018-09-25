package com.lim.lambda;

import com.lim.BaseBean;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda 匿名函数
 * @author qinhao
 */
public class LimLambdaTest {

    private List<BaseBean> list;

    @Before
    public void init() {
        list = new ArrayList<>();
        list.add(new BaseBean(1100, "A"));
        list.add(new BaseBean(1002, "B"));
        list.add(new BaseBean(2008, "C"));
        list.add(new BaseBean(2000, "D"));
    }

    @Test
    public void test() {
        System.out.println(list);
        Collections.sort(list, new Comparator<BaseBean>() {
            @Override
            public int compare(BaseBean o1, BaseBean o2) {
                return o1.getLength() < o2.getLength() ? -1 : (o1.getLength() > o2.getLength() ? 1 : 0);
            }
        });
        list.forEach(System.out::print);
    }

    @Test
    public void testLambda() {
        list.sort(Comparator.comparing(BaseBean::getLength));
        list.forEach(System.out::println);
        System.out.println(Thread.currentThread().getId());
    }

}
