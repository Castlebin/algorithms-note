package com.alibaba.tmf3;

import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.delivery.DeliveryItem;

import org.springframework.stereotype.Service;

/**
 * User: kuhe
 * Date: 2017/7/8
 * Time: PM3:45
 */

@Service
public class EntryService {

    //@Inject
    private DeliveryExtension deliveryExtension;

    public void run() {
        DeliveryItem item = new DeliveryItem();

        String transportMethod = deliveryExtension.getTransportMethod(item);
        String receiveMethod = deliveryExtension.getReceiveMethod(item);

        System.out.println(transportMethod);
        System.out.println(receiveMethod);
    }

}
