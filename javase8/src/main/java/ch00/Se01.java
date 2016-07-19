package ch00;

import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

public class Se01 {
    Logger log = Logger.getGlobal();

    @Test
    public void testSort() {
        List<Plant> l1 = new ArrayList<Plant>(){{
            Random r = new Random(1000000L);
            for (int i=0; i<100000; i++) {
                Plant p = new Plant();
                p.setAge(r.nextLong() % 1000);
                add(p);
            }
        }};
        Plant[] a1 = l1.toArray(new Plant[]{});
        Plant[] a2 = Arrays.copyOf(a1, a1.length);

    //    log.info("a1: " + Arrays.toString(a1));
    //    log.info("a2:" + Arrays.toString(a2));

        long begin = System.currentTimeMillis();
        Arrays.sort(a1, Comparator.comparing(Plant::getAge));
        System.out.println(begin - System.currentTimeMillis());

        begin = System.currentTimeMillis();
        Arrays.parallelSort(a2, Comparator.comparing(Plant::getAge));
        System.out.println(begin - System.currentTimeMillis());

    //    log.info("a1: " + Arrays.toString(a1));
    //    log.info("a2:" + Arrays.toString(a2));
    }

}

class Plant {
    private String name;
    private Long age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
}
