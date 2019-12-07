package com.helper.store.web;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.helper.store.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.helper.store.config.AliPayConfig.*;

/**
 * Created by zj on 2019/1/4.
 */
@RestController
@RequestMapping("/pay")
public class AlipayController {

    @RequestMapping("/alipayAndroid")
    @ResponseBody
    public Map<String,Object> getAliPayOrderStr(Order orderInfo ) {
        Map<String,Object> result = new HashMap<String,Object>(16);
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", APP_ID, PRIVATE_KEY, "json", CHARSET, PUBLIC_KEY, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(orderInfo.getOrderNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(orderInfo.getPrice());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        //封装参数
        request.setNotifyUrl(NOTIFY_URL);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            result.put("result", response.getBody());
            result.put("status", "0");
            result.put("msg","订单生成成功");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            result.put("msg", "订单生成失败");
        }
        return  result;
    }

    @RequestMapping(value = "/notify")
    @ResponseBody
    public String alipaySuccessCallBack(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
        Map<String,String> params = new HashMap<String,String>(16);
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//boolean AlipaySignature.rsaCertCheckV1(Map<String, String> params, String publicKeyCertPath, String charset,String signType)
        boolean flag = AlipaySignature.rsaCertCheckV1(params, PUBLIC_KEY, "UTF-8","RSA2");
        if (flag) {

            System.out.println("支付宝支付成功:");
            return  "success";
        }else {
            System.out.println("支付宝成功回调验签失败");
            return "fail";
        }
    }


}