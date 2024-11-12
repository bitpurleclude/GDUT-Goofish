package org.gdutgoodfish.goodfish.controller;


import com.alipay.api.AlipayApiException;
import org.gdutgoodfish.goodfish.bean.AlipayBean;
import org.gdutgoodfish.goodfish.service.impl.Alipay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class OrderController {
    @Autowired
    Alipay alipay;
    @PostMapping(value = "alipay")
    @ResponseBody
    public String alipay(@RequestBody AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }
}
