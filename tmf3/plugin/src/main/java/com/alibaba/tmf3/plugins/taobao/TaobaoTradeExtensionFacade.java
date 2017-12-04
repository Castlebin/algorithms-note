package com.alibaba.tmf3.plugins.taobao;

import com.alibaba.platform.TradeExtensionFacade;
import com.alibaba.platform.delivery.DeliveryExtension;
import com.alibaba.platform.promotion.PromotionExtension;
import com.alibaba.tmf3.core.BizCode;
import com.alibaba.tmf3.plugins.tmall.TmallDeliveryExtension;
import com.alibaba.tmf3.plugins.tmall.TmallPromotionExtension;

/**
 * User: kuhe
 * Date: 2017/7/12
 * Time: PM2:10
 */

@BizCode("taobao")
public class TaobaoTradeExtensionFacade extends TradeExtensionFacade {


    public DeliveryExtension getDeliveryExtension() {
        return new TaobaoDeliveryExtension();

    }

    public PromotionExtension getPromotionExtension() {
        return new TaobaoPromotionExtension();
    }
}
