package com.heller.tmf.plugin.taobao.trade.delivery;

import com.heller.tmf.platform.trade.delivery.DeliveryExtension;
import com.heller.tmf.platform.trade.delivery.DeliveryItem;

public class TaobaoDeliveryExtension implements DeliveryExtension {

    public String getTransportMethod(DeliveryItem item) {
        return "threeDayExpress";
    }

    public String getReceiveMethod(DeliveryItem item) {
        return "selfPickUp";
    }

}
