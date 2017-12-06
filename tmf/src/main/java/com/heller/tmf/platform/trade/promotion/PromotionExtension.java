package com.heller.tmf.platform.trade.promotion;

import com.heller.tmf.core.ExtensionPoint;

@ExtensionPoint
public interface PromotionExtension {
    long getDiscount(PromotionItem item);
}
