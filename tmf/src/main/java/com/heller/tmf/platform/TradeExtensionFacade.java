package com.heller.tmf.platform;

import com.heller.tmf.core.ExtensionFacade;
import com.heller.tmf.platform.delivery.DeliveryExtension;
import com.heller.tmf.platform.promotion.PromotionExtension;

public abstract class TradeExtensionFacade implements ExtensionFacade {

    public abstract DeliveryExtension getDeliveryExtension();

    public abstract PromotionExtension getPromotionExtension();

}
