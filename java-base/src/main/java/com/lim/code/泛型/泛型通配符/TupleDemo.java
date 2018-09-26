package com.lim.code.泛型.泛型通配符;

/**
 * tuple元组
 * @author qinhao
 */
public class TupleDemo<T> {

    private Class<T> typeClass;
    private T typeT;

    public Class<T> getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public T getTypeT() {
        return typeT;
    }

    public void setTypeT(T typeT) {
        this.typeT = typeT;
    }

    public static void main(String[] args) throws Exception {
        // 1.typeClass
        TupleDemo<GenericBean> tupleDemo1 = new TupleDemo<>();
        tupleDemo1.setTypeClass(GenericBean.class);
        // Class<?>
        // Class<? extends GenericBean>
        Class<GenericBean> clazz = tupleDemo1.getTypeClass();
        GenericBean bean1 = clazz.getDeclaredConstructor().newInstance();
        System.out.println(bean1);
        // 2.typeT
        TupleDemo<GenericBean> tupleDemo2 = new TupleDemo<>();
        tupleDemo2.setTypeT(new GenericBean());
        GenericBean bean2 = tupleDemo2.getTypeT();
        System.out.println(bean2);
    }
}
