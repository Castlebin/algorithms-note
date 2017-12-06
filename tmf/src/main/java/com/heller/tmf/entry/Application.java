package com.heller.tmf.entry;

import com.heller.tmf.core.ExtensionInvoker;
import com.heller.tmf.core.ExtensionMappingBuilder;
import com.heller.tmf.platform.trade.delivery.DeliveryExtension;
import com.heller.tmf.platform.trade.delivery.DeliveryItem;
import com.heller.tmf.platform.trade.promotion.PromotionExtension;
import com.heller.tmf.platform.trade.promotion.PromotionItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Application {

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

        PromotionItem item2 = new PromotionItem();
        item2.setBizCode("taobao");
        ExtensionInvoker<PromotionExtension> invoker2 = new ExtensionInvoker<>(PromotionExtension.class);
        invoker2.execute(item, p -> p.getDiscount(item2));

        Long discount = invoker2.execute(item, p -> p.getDiscount(item2));
        System.out.println(discount);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
        Application app = ctx.getBean(Application.class);
        app.run();
    }

}
