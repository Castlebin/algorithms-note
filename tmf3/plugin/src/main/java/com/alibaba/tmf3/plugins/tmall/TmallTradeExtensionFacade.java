package com.alibaba.tmf3.plugins.tmall;

import com.alibaba.platform.TradeExtensionFacade;
import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.delivery.DeliveryItem;
import com.alibaba.platform.promotion.PromotionExtension;
import com.alibaba.platform.promotion.PromotionItem;
import com.alibaba.tmf3.core.BizCode;
import com.alibaba.tmf3.core.WithProduct;

/**
 * User: kuhe
 * Date: 2017/7/12
 * Time: PM2:10
 */

@BizCode("tmall")
@WithProduct("juhuasuan")
public class TmallTradeExtensionFacade extends TradeExtensionFacade {

    public DeliveryExtension getDeliveryExtension() {
        return new DeliveryExtension() {
            @Override
            public String getTransportMethod(DeliveryItem item) {
                return "tmallMethod";
            }

            @Override
            public String getReceiveMethod(DeliveryItem item) {
                return "tmallMethod2";
            }
        };

    }

    public PromotionExtension getPromotionExtension() {
        return new PromotionExtension() {
            @Override
            public long getDiscount(PromotionItem item) {
                return 20;
            }
        };
    }
}
