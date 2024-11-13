package org.gdutgoodfish.goodfish.controller;


import cn.hutool.core.lang.Snowflake;
import com.alipay.api.AlipayApiException;
import org.gdutgoodfish.goodfish.bean.AlipayBean;
import org.gdutgoodfish.goodfish.dto.OrderDTO;
import org.gdutgoodfish.goodfish.entity.Result;
import org.gdutgoodfish.goodfish.service.impl.Alipay;
import org.gdutgoodfish.goodfish.service.impl.OrderImpl;
import org.gdutgoodfish.goodfish.service.impl.OrderItemServiceImpl;
import org.gdutgoodfish.goodfish.util.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/v1/Order")
public class OrderController {
    @Autowired
    Alipay alipay;
    @Autowired
    Snowflake snowflake;
    @Autowired
    OrderImpl orderImpl;
    @Autowired
    OrderItemServiceImpl orderItemServiceImpl;

    @PostMapping(value = "newOrder")
    @ResponseBody
    public Result<OrderDTO> newOrder(OrderDTO orderDTO) {
        /*
            * 参数校验
         */
        if (orderDTO == null) {
            throw new NullPointerException("OrderDTO cannot be null");
        }
        if (ObjectValidator.validate(orderDTO)) {
            throw new NullPointerException("OrderDTO or param cannot be null");
        }

        if (orderDTO.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (orderDTO.getProductPrice() < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (orderDTO.getOrderAmount() < 0) {
            throw new IllegalArgumentException("Order amount cannot be negative");
        }
        orderDTO.setOrderId(snowflake.nextIdStr());
        orderImpl.newOrder(orderDTO);
        orderItemServiceImpl.newOrderItem(orderDTO);
        Result<OrderDTO> success = Result.success("Order created successfully");
        success.setData(orderDTO);
        return success;
    }

/**
 * 支付接口
 *
 * @param alipayBean
 * @return
 * @throws AlipayApiException
 */
@PostMapping(value = "alipay")
@ResponseBody
public Result<String> alipay(@RequestBody AlipayBean alipayBean) throws AlipayApiException {
    String form = alipay.pay(alipayBean); // 获取支付表单HTML代码
    return Result.success(form, "支付表单获取成功");
}

    /**
     * 支付成功回调接口
     *
     * @param out_trade_no
     * @return
     */
@PostMapping(value = "alipay/notify")
@ResponseBody
public Result<String> notify(@RequestBody String out_trade_no) {
    System.out.println("out_trade_no: " + out_trade_no);
    //todo 处理支付成功逻辑
    return Result.success("success");
}
}
