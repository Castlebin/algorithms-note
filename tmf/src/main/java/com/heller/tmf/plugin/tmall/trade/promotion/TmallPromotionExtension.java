package com.heller.tmf.plugin.tmall.trade.promotion;

import com.heller.tmf.platform.trade.promotion.PromotionExtension;
import com.heller.tmf.platform.trade.promotion.PromotionItem;

public class TmallPromotionExtension implements PromotionExtension {

    @Override
    public long getDiscount(PromotionItem item) {
        return 20;
    }
}
