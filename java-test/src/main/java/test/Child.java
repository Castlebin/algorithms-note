package test;

import java.util.ArrayList;

class Child extends Parent {
    int i = 0;
    ArrayList<Integer> list;

    public Child() {
        super();
        System.out.println("new Child, i=" + i + ", list.size=" + list.size());
    }

    @Override
    protected void init() {
        super.init();
        i = 100;
        list = new ArrayList<>();
        list.add(30);
        System.out.println("new Child, i=" + i + ", list.size=" + list.size());
    }

    /**
     * 以下是输出内容，不太能理解最后一行输出
     new Parent
     Parent.init
     new Child, i=100, list.size=1
     new Child, i=0, list.size=1
     */
    public static void main(String[] args) {
        Child child = new Child();
    }

}
