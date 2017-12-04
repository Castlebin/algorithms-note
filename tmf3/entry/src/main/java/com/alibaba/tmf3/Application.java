package com.alibaba.tmf3;

import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.delivery.DeliveryItem;
import com.alibaba.platform.promotion.PromotionExtension;
import com.alibaba.platform.promotion.PromotionItem;
import com.alibaba.tmf3.core.ExtensionInvoker;
import com.alibaba.tmf3.core.ExtensionMappingBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.inject.Inject;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM3:31
 */
@ComponentScan
public class Application {

    //@Inject
    private DeliveryExtension deliveryExtension;

    private void runWithSpring() {
        DeliveryItem item = new DeliveryItem();

        // 在真实的代码中,
        // 这里会根据一些请求上下文来设定 bizCode
        item.setBizCode("tmall");


        String receiveMethod = deliveryExtension.getReceiveMethod(item);
        String transportMethod = deliveryExtension.getTransportMethod(item);

        System.out.println("receive method: " + receiveMethod);
        System.out.println("transport method: " + transportMethod);

    }

    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(Application.class);

        Application app = context.getBean(Application.class);

        app.runWithFacade();

    }


    @Inject
    private EntryService service;

    private static Application application;

    private void run() {

        //构建扩展点接口与业务实现之间的关系。
        ExtensionMappingBuilder.getInstance().build();

        DeliveryItem item = new DeliveryItem();
        item.setBizCode("tmall");

        ExtensionInvoker<DeliveryExtension> invoker =
                new ExtensionInvoker<>(DeliveryExtension.class);

        String transportMethod = invoker.execute(item,
                p -> p.getTransportMethod(item));

        String receiveMethod = invoker.execute(item,
                p -> p.getReceiveMethod(item));


        ExtensionInvoker<PromotionExtension> invoker2=
                new ExtensionInvoker<>(PromotionExtension.class);

        PromotionItem promotionItem = new PromotionItem();
        promotionItem.setBizCode("tmall");


        Long discount = invoker2.execute(item, p -> p.getDiscount(promotionItem));

        System.out.println(transportMethod);
        System.out.println(receiveMethod);

        System.out.println(discount);
    }


    private void runWithFacade() {

        //构建扩展点接口与业务实现之间的关系。
        ExtensionMappingBuilder.getInstance().build();

        DeliveryItem item = new DeliveryItem();
        item.setBizCode("tmall");

        ExtensionInvoker<DeliveryExtension> invoker =
                new ExtensionInvoker<>(DeliveryExtension.class);

        String transportMethod = invoker.execute(item,
                p -> p.getTransportMethod(item));

        String receiveMethod = invoker.execute(item,
                p -> p.getReceiveMethod(item));

        ExtensionInvoker<PromotionExtension> invoker2=
                new ExtensionInvoker<>(PromotionExtension.class);

        PromotionItem promotionItem = new PromotionItem();
        promotionItem.setBizCode("tmall");

        Long discount = invoker2.execute(item, p -> p.getDiscount(promotionItem));

        System.out.println(transportMethod);
        System.out.println(receiveMethod);

        System.out.println(discount);
    }



//    public static void main(String[] args) {
//        ApplicationContext context
//                = new AnnotationConfigApplicationContext(Application.class);
//
//        EntryService service = context.getBean(EntryService.class);
//
//
//        service.run();
//
//       // invokerExtension();
//
//    }


}
