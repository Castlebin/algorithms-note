package com.heller.tmf.plugin.product;

import com.heller.tmf.platform.trade.TradeExtensionFacade;
import com.heller.tmf.platform.trade.delivery.DeliveryExtension;
import com.heller.tmf.platform.trade.promotion.PromotionExtension;
import com.heller.tmf.platform.trade.promotion.PromotionItem;
import com.heller.tmf.plugin.tmall.trade.delivery.TmallDeliveryExtension;

public class JhsProduct extends TradeExtensionFacade {

    @Override
    public DeliveryExtension getDeliveryExtension() {
        return new TmallDeliveryExtension();
    }

    @Override
    public PromotionExtension getPromotionExtension() {
        return new PromotionExtension() {
            @Override
            public long getDiscount(PromotionItem item) {
                return 50;
            }
        };
    }
}
