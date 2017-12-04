package com.alibaba.platform.delivery;

import com.alibaba.tmf3.core.ExtensionPoints;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM4:55
 */


public interface DeliveryExtension extends  ExtensionPoints{

    String getTransportMethod(DeliveryItem item);

    String getReceiveMethod(DeliveryItem item);

}
