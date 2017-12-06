package com.heller.tmf.plugin.tmall.trade;

import com.heller.tmf.core.BizCode;
import com.heller.tmf.platform.trade.TradeExtensionFacade;
import com.heller.tmf.platform.trade.delivery.DeliveryExtension;
import com.heller.tmf.platform.trade.promotion.PromotionExtension;
import com.heller.tmf.plugin.tmall.trade.delivery.TmallDeliveryExtension;
import com.heller.tmf.plugin.tmall.trade.promotion.TmallPromotionExtension;

@BizCode("tmall")
public class TmallTradeExtensionFacade extends TradeExtensionFacade {

    @Override
    public DeliveryExtension getDeliveryExtension() {
        return new TmallDeliveryExtension();

    }

    @Override
    public PromotionExtension getPromotionExtension() {
        return new TmallPromotionExtension();
    }
}
