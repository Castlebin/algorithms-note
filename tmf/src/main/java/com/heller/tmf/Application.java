package com.heller.tmf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

    @Autowired
    private DeliveryExtension deliveryExtension;

    private void run(){
        DeliveryItem item=new DeliveryItem();

        String receiveMethod=deliveryExtension.getReceiveMethod(item);
        String transportMethod=deliveryExtension.getTransportMethod(item);

        System.out.println("receive method: "+receiveMethod);
        System.out.println("transport method: "+transportMethod);

    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
        Application app = ctx.getBean(Application.class);
        app.run();
    }

}
