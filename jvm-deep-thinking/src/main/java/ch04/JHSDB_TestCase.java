package ch04;

/**
 * staticObj、instanceObj、localObj存放在哪里？

 JDK 11
 vm args:

 -Xmx10m -XX:+UseSerialGC -XX:+UseCompressedOops


 Heap Parameters:
 Gen 0:   eden [0x00000007ff600000,0x00000007ff811c88,0x00000007ff8b0000) space capacity = 2818048, 77.00337254723837 used
 from [0x00000007ff8b0000,0x00000007ff8b0000,0x00000007ff900000) space capacity = 327680, 0.0 used
 to   [0x00000007ff900000,0x00000007ff900000,0x00000007ff950000) space capacity = 327680, 0.0 usedInvocations: 0

 Gen 1:   old  [0x00000007ff950000,0x00000007ff950000,0x0000000800000000) space capacity = 7012352, 0.0 usedInvocations: 0


 scanoops 0x00000007ff600000 0x00000007ff950000 ch04.JHSDB_TestCase$ObjectHolder

 output:
 0x00000007ff807558 ch04/JHSDB_TestCase$ObjectHolder
 0x00000007ff8075b0 ch04/JHSDB_TestCase$ObjectHolder
 0x00000007ff807600 ch04/JHSDB_TestCase$ObjectHolder


 revptrs 0x00000007ff807558

 */
public class JHSDB_TestCase {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder("I'm a StaticObj");
        ObjectHolder instanceObj = new ObjectHolder("I'm a InstanceObj");

        void foo() {
            ObjectHolder localObj = new ObjectHolder("I'm a LocalObj");
            System.out.println("done");    // 这里设一个断点
        }
    }

    private static class ObjectHolder {
        private String name;
        public ObjectHolder(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.foo();
    }
}
