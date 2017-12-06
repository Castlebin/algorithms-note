package com.heller.tmf.platform.trade;

import com.heller.tmf.core.ExtensionFacade;
import com.heller.tmf.platform.trade.delivery.DeliveryExtension;
import com.heller.tmf.platform.trade.promotion.PromotionExtension;

public abstract class TradeExtensionFacade implements ExtensionFacade {

    public abstract DeliveryExtension getDeliveryExtension();

    public abstract PromotionExtension getPromotionExtension();

}
