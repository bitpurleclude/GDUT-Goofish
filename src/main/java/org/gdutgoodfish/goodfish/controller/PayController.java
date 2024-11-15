package org.gdutgoodfish.goodfish.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.service.IOrdersService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
@Slf4j
public class PayController {

    private final IOrdersService ordersService;


    @GetMapping("/{orderId}")
    public String pay(@PathVariable Long orderId) {
        log.info("订单支付 {}", orderId);
        return ordersService.payOrder(orderId);
    }

    @PostMapping("/paySuccess")
    public void paySuccess(HttpServletRequest request) {
        log.info("支付成功回调 {}", JSONObject.toJSONString(request.getParameterMap()));
        String number = request.getParameter("out_trade_no");
        ordersService.payOrderSuccess(number);
    }


}
