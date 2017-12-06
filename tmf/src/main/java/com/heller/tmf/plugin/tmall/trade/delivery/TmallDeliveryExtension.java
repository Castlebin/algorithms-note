package com.heller.tmf.plugin.tmall.trade.delivery;

import com.heller.tmf.platform.trade.delivery.DeliveryExtension;
import com.heller.tmf.platform.trade.delivery.DeliveryItem;

public class TmallDeliveryExtension implements DeliveryExtension {

    public String getTransportMethod(DeliveryItem item) {
        return "oneDayExpress";
    }

    public String getReceiveMethod(DeliveryItem item) {
        return "receiveAtHome";
    }

}
