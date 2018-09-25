package com.lim;

/**
 * 基础实体类
 * @author qinhao
 */
public class BaseBean {

    private Integer length;
    private String type;

    public BaseBean() {
    }

    public BaseBean(Integer length, String type) {
        this.length = length;
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "BaseBean{" +
                "length=" + length +
                ", type='" + type + '\'' +
                '}';
    }
}
