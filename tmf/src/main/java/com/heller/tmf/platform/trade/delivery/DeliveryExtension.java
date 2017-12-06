package com.heller.tmf.platform.trade.delivery;

import com.heller.tmf.core.ExtensionPoint;

public interface DeliveryExtension extends ExtensionPoint {

    String getTransportMethod(DeliveryItem item);

    String getReceiveMethod(DeliveryItem item);

}
