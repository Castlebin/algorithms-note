package com.heller.tmf.plugin.taobao.trade;

import com.heller.tmf.core.BizCode;
import com.heller.tmf.platform.trade.TradeExtensionFacade;
import com.heller.tmf.platform.trade.delivery.DeliveryExtension;
import com.heller.tmf.platform.trade.promotion.PromotionExtension;
import com.heller.tmf.plugin.taobao.trade.delivery.TaobaoDeliveryExtension;
import com.heller.tmf.plugin.taobao.trade.promotion.TaobaoPromotionExtension;

@BizCode("taobao")
public class TaobaoTradeExtensionFacade extends TradeExtensionFacade {

    @Override
    public DeliveryExtension getDeliveryExtension() {
        return new TaobaoDeliveryExtension();
    }

    @Override
    public PromotionExtension getPromotionExtension() {
        return new TaobaoPromotionExtension();
    }
}
