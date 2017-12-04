package com.alibaba.platform.promotion;

import com.alibaba.tmf3.core.ExtensionInvoker;

/**
 * User: kuhe
 * Date: 2017/7/7
 * Time: PM4:55
 */

public class PromotionExtensionInvoker extends ExtensionInvoker<PromotionExtension>
    implements PromotionExtension {

    public PromotionExtensionInvoker() {
        super(PromotionExtension.class);
    }

    @Override
    public long getDiscount(PromotionItem item) {

        return this.execute(item, p -> p.getDiscount(item));
    }
}
