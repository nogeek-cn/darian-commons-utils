package com.darian.newCode.service.impl.wechat;

import com.darian.newCode.service.impl.AbstractPayService;
import org.springframework.stereotype.Service;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/20  2:06
 */
public abstract class AbstractWeChatPayServiceImpl extends AbstractPayService {

    @Override
    protected final void doPayCoreService() {
        doPayCoreNodeService();
    }

    protected abstract void doPayCoreNodeService();

    @Override
    protected void checkParam() {
        // 校验公共参数
        checkNodeParam();
    }

    protected abstract void checkNodeParam();
}
