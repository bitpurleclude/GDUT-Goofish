package org.gdutgoodfish.goodfish.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdutgoodfish.goodfish.pojo.common.Result;
import org.gdutgoodfish.goodfish.pojo.dto.OrderAddDTO;
import org.gdutgoodfish.goodfish.pojo.dto.OrderIdDTO;
import org.gdutgoodfish.goodfish.pojo.vo.OrderVO;
import org.gdutgoodfish.goodfish.service.IOrdersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author J
 * @since 2024-11-15
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final IOrdersService ordersService;

    @PostMapping
    public Result<String> addOrder(@RequestBody OrderAddDTO orderAddDTO) {
        log.info("用户下单 : {}", orderAddDTO);
        ordersService.addOrder(orderAddDTO);
        return Result.success("下单成功，请及时完成支付");
    }

    @GetMapping
    public Result<List<OrderVO>> getAllOrders(Integer status) {
        log.info("用户查询订单");
        List<OrderVO> vos = ordersService.getUserOrder(status);
        return Result.success(vos);
    }

    @PutMapping("/finish")
    public Result<String> finishOrder(@RequestBody OrderIdDTO orderIdDTO) {
        log.info("用户完成订单 : {}", orderIdDTO);
        ordersService.finishOrder(orderIdDTO);
        return Result.success("订单已完成");
    }

    @PutMapping("/cancel")
    public Result<String> cancelOrder(@RequestBody OrderIdDTO orderIdDTO) {
        log.info("用户取消订单 : {}", orderIdDTO);
        ordersService.cancelOrder(orderIdDTO);
        return Result.success("订单取消成功");
    }



}
