package com.heller.tmf.plugin.taobao.trade.promotion;

import com.heller.tmf.platform.trade.promotion.PromotionExtension;
import com.heller.tmf.platform.trade.promotion.PromotionItem;

public class TaobaoPromotionExtension implements PromotionExtension {

    @Override
    public long getDiscount(PromotionItem item) {
        return 10;
    }
}
