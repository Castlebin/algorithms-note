package com.heller.tmf;

public interface DeliveryExtension{
    String getTransportMethod(DeliveryItem item);
    String getReceiveMethod(DeliveryItem item);
}
