package ch18.se06;

import javax.swing.*;
import java.util.Date;

public class CrazyitObjectFactory2 {
    public static <T> T getInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Date date = CrazyitObjectFactory2.getInstance(Date.class);
        JFrame f = CrazyitObjectFactory2.getInstance(JFrame.class);
    }
}
