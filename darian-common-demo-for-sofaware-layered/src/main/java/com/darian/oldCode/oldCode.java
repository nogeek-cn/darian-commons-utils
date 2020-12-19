package com.darian.oldCode;

/***
 *
 *
 * @author <a href="mailto:1934849492@qq.com">Darian</a> 
 * @date 2020/12/20  1:55
 */
@Deprecated
public class oldCode {
    public void pay(String order) {
        if (order == "weixinpay") {
            // 微信支付
            weixinpay(order);
        } else if (order == "zhifubaopay") {
            // 支付包支付
            zhifubaopay();
        } else if (order == "yinlianpay") {
            // 银联支付
            yinlianpay();
        } else if (order == "gongshangyinhangpay") {
            // 工商银行支付
            gongshangyinhangpay();
        } else if (order == "jiansheyinhangpay") {
            // 建设银行支付
            jiansheyinhangpay();
        }
        // .....
    }


    public void weixinpay(String order) {
        if ("weixinpayH5" == order) {
            // 微信H5支付
            weixinpayH5();
        } else if ("weixinpaysaoma" == order) {
            // 微信二维码支付
            weixinpaysaoma();
        } else if ("weixinzhuanzhang" == order) {
            // 微信转账
            weixinzhuanzhang();
        }
    }

    public void zhifubaopay() {
    }

    public void yinlianpay() {
    }

    public void gongshangyinhangpay() {
    }

    public void jiansheyinhangpay() {

    }

    public void weixinpayH5() {
    }

    public void weixinpaysaoma() {
    }

    public void weixinzhuanzhang() {

    }
}
