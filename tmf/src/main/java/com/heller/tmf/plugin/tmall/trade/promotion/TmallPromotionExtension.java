package com.heller.tmf.plugin.tmall.trade.promotion;

import com.heller.tmf.core.BizCode;
import com.heller.tmf.platform.trade.promotion.PromotionExtension;
import com.heller.tmf.platform.trade.promotion.PromotionItem;

@BizCode("tmall")
public class TmallPromotionExtension implements PromotionExtension {

    @Override
    public long getDiscount(PromotionItem item) {
        return 20;
    }
}
