package com.lim.code.generic;

/**
 * @author qinhao
 */
public class GenericTuple<A, B, C> {

    public final A first;
    public final B second;
    public final C third;

    public GenericTuple(A a, B b, C c) {
        this.first = a;
        this.second = b;
        this.third = c;
    }

    @Override
    public String toString() {
        return "(" + first.getClass().getSimpleName() + ":" + first + "," +
                second.getClass().getSimpleName() + ":" + second + "," +
                third.getClass().getSimpleName() + ":" + third + ")";
    }

    public static void main(String[] args) {
        GenericTuple<String, Integer, Long> tuple = new GenericTuple<>("string", 5, 10L);
        System.out.println(tuple.toString());
        System.out.println(tuple.first);
        System.out.println(tuple.second);
        System.out.println(tuple.third);
    }
}
