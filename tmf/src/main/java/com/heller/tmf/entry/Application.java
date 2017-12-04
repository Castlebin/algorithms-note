package com.heller.tmf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

    private DeliveryExtension deliveryExtension;

    private void run() {
        //构建扩展点接口与业务实现之间的关系。
        ExtensionMappingBuilder.getInstance().build();

        DeliveryItem item = new DeliveryItem();
        item.setBizCode("tmall");

        ExtensionInvoker<DeliveryExtension> invoker = new ExtensionInvoker<>(DeliveryExtension.class);

        String transportMethod = invoker.execute(item, p -> p.getTransportMethod(item));

        String receiveMethod = invoker.execute(item, p -> p.getReceiveMethod(item));

        System.out.println(transportMethod);
        System.out.println(receiveMethod);

    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
        Application app = ctx.getBean(Application.class);
        app.run();
    }

}
