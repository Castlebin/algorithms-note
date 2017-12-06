package com.heller.tmf.platform.trade.promotion;

import com.heller.tmf.core.ExtensionPoint;

public interface PromotionExtension extends ExtensionPoint {
    long getDiscount(PromotionItem item);
}
