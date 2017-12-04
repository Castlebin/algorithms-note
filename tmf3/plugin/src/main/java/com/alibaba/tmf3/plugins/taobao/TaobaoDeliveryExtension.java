package com.alibaba.tmf3.plugins.taobao;

import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.delivery.DeliveryItem;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM4:55
 */

public class TaobaoDeliveryExtension implements DeliveryExtension {

    public String getTransportMethod(DeliveryItem item) {
        return "threeDayExpress";
    }

    public String getReceiveMethod(DeliveryItem item) {
        return "selfPickUp";
    }
}
