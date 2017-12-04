package com.alibaba.platform.promotion;

import com.alibaba.tmf3.core.ExtensionPoint;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM4:55
 */



public interface PromotionExtension extends ExtensionPoint {

    long getDiscount(PromotionItem item);
}
