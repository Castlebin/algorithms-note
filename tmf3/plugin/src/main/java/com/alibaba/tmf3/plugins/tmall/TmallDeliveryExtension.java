package com.alibaba.tmf3.plugins.tmall;

import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.delivery.DeliveryItem;
import com.alibaba.tmf3.core.BizCode;
import org.springframework.stereotype.Component;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM4:55
 */

public class TmallDeliveryExtension implements DeliveryExtension {


    public String getTransportMethod(DeliveryItem item) {
        return "oneDayExpress";
    }

    public String getReceiveMethod(DeliveryItem item) {
        return "receiveAtHome";
    }
}
