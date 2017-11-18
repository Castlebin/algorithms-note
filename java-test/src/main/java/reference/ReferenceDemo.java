package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceDemo {
    public static void main(String[] arge) {
        //强引用
        Object object = new Object();
        Object[] objects = new Object[100];

        //软引用
        SoftReference<String> stringSoftReference = new SoftReference<>(new String("SoftReference"));
        System.out.println(stringSoftReference.get());
        System.gc();
        System.out.println(stringSoftReference.get()); //手动GC,这时内存充足,对象没有被回收

        System.out.println();

        //弱引用
        WeakReference<String> stringWeakReference = new WeakReference<>(new String("WeakReference"));
        System.out.println(stringWeakReference.get());
        System.gc();
        System.out.println(stringWeakReference.get()); //手动gc,这时,返回null,对象已经被回收

        System.out.println();

        //虚引用
        //虚引用主要用来跟踪对象被垃圾回收器回收的活动。
        //虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。
        //当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之 关联的引用队列中
        ReferenceQueue<String> stringReferenceQueue = new ReferenceQueue<>();
        PhantomReference<String> stringPhantomReference = new PhantomReference<>(new String("PhantomReference"), stringReferenceQueue);
        System.out.println(stringPhantomReference.get());
    }
}
