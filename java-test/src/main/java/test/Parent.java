package test;

import java.util.ArrayList;
import java.util.List;

public class Parent {

    public Parent() {
        System.out.println("new Parent");
        init();
    }

    protected void init() {
        System.out.println("Parent.init");
    }

    static class Child extends Parent {
        private int i = 0;
        private List<Integer> list;

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

    }

    /**
     * 以下是输出内容，不太能理解最后一行输出
     （明白了，super()执行后，开始执行属性的赋值操作了，有private int i = 0，而list没有再赋值，所以保持原状了，
     如果改为private List<Integer> list=new ArrayList<>()，那么最后一句中size=0，
     同理如果改为private List<Integer> list=null，那么会报NullPointException）
     new Parent
     Parent.init
     new Child, i=100, list.size=1
     new Child, i=0, list.size=1
     */
    public static void main(String[] args) {
        Child child = new Child();
    }

}
