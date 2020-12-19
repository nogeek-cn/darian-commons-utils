package com.darian.newCode.service.impl;

import com.darian.newCode.service.PayService;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/20  2:09
 */
public abstract class AbstractPayService implements PayService {

    @Override
    public final void doPay(String order) {
        checkParam();
        doPayCoreService();
    }

    protected abstract void doPayCoreService();

    protected abstract void checkParam();
}
