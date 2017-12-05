package com.heller.tmf.platform.promotion;

import com.heller.tmf.core.ExtensionPoint;

@ExtensionPoint
public interface PromotionExtension {
    long getDiscount(PromotionItem item);
}
