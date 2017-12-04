package com.heller.tmf;

import org.springframework.stereotype.Component;

@Component
public class TmallDeliveryExtension implements DeliveryExtension {
    public String getTransportMethod(DeliveryItem item) {
        return "oneDayExpress";
    }
    public String getReceiveMethod(DeliveryItem item) {
        return "receiveAtHome";
    }
}
