package org.gdutgoodfish.goodfish.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.config.AlipayConfig;
import org.gdutgoodfish.goodfish.pojo.common.AlipayOrder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class AlipayUtil {

    private final AlipayConfig aliPayConfig;

        public String pay(AlipayOrder order) throws AlipayApiException {
            //支付网关
            String serverUrl = aliPayConfig.getGatewayUrl();
            // AppId
            String appId = aliPayConfig.getAppId();
            // 用户密钥(私钥)，即PKCS8格式RSA2私钥
            String privateKey = aliPayConfig.getPrivateKey();
            //格式化为json格式
            String format = "json";
            //编码
            String charset = aliPayConfig.getCharset();
            //支付宝公钥，即对应Appid下的支付宝公钥
            String alipayPublicKey = aliPayConfig.getAlipayPublicKey();
            //签名方式
            String signType = aliPayConfig.getSignType();
            //页面跳转同步通知页面路径
            String returnUrl = aliPayConfig.getReturnUrl();
            //服务器异步通知页面路径
            String notifyUrl = aliPayConfig.getNotifyUrl();

            //1、获取初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(
                    serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
            //2、设置请求参数
            AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
            // 页面跳转同步通知页面路径
            alipayTradePagePayRequest.setReturnUrl(returnUrl);
            alipayTradePagePayRequest.setNotifyUrl(notifyUrl);
            //封装参数（json格式）
            alipayTradePagePayRequest.setBizContent(JSONObject.toJSONString(order));
            //    3、请求支付宝进行付款，并获取支付结果
            AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayTradePagePayRequest);
            //放回信息
            return response.getBody();
        }

}
