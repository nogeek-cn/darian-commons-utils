package com.darian.newCode.facade;

import com.darian.newCode.service.PayService;

import javax.annotation.Resource;
import java.util.Map;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/20  1:57
 */
public class PayMonitor implements PayFacade {

    @Resource
    private Map<String, PayService> payServiceMap;

    @Override
    public void pay(String order) {

        try {
            // 获取 支付的具体实现名字
            String payServiceName = getPayServiceName(order);
            // 获取具体支付实现类
            PayService payService = payServiceMap.get(payServiceName);
            // 进行具体支付的实现
            payService.doPay(order);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 监控成功或者失败
            System.out.println("[success]/[fail]");
        }
    }

    // 设置 不同的实现
    public void generatorPayServiceMap() {

    }

    public String getPayServiceName(String order) {
        return "payServiceName";
    }

}
