package com.alibaba.tmf3.plugins.tmall;

import com.alibaba.platform.promotion.PromotionExtension;
import com.alibaba.platform.promotion.PromotionItem;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM4:55
 */

public class TmallPromotionExtension implements PromotionExtension {

    public long getDiscount(PromotionItem item) {
        return 20;
    }
}
