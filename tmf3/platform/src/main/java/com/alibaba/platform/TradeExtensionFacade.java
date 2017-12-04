package com.alibaba.platform;

import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.promotion.PromotionExtension;
import com.alibaba.tmf3.core.ExtensionFacade;

/**
 * User: kuhe
 * Date: 2017/7/12
 * Time: PM2:08
 */
public abstract class TradeExtensionFacade implements ExtensionFacade {

    public abstract DeliveryExtension getDeliveryExtension();

    public abstract PromotionExtension getPromotionExtension();

}
