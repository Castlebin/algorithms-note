package com.alibaba.tmf3.plugins.products;

import com.alibaba.platform.TradeExtensionFacade;
import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.promotion.PromotionExtension;
import com.alibaba.platform.promotion.PromotionItem;
import com.alibaba.tmf3.core.ProductCode;
import com.alibaba.tmf3.plugins.tmall.TmallDeliveryExtension;

/**
 * User: kuhe
 * Date: 2017/7/14
 * Time: PM3:36
 */
@ProductCode("juhuasuan")
public class JhsProduct extends TradeExtensionFacade{

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
