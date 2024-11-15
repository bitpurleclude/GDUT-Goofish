package org.gdutgoodfish.goodfish.pojo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlipayOrder {

    private Long orderId;

    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 订单名称
     */
    private String subject;
    /**
     * 付款金额
     */
    private String total_amount;

    /**
     * 产品编号
     */
    private String product_code;

}

