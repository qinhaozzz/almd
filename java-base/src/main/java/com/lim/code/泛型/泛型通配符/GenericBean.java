package com.lim.code.泛型.泛型通配符;

/**
 * @author qinhao
 */
public class GenericBean {

    private String name;
    private Integer age;

    public GenericBean() {
        System.out.println("constructor...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
