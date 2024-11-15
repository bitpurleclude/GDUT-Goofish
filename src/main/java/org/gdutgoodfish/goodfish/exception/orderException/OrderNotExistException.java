package org.gdutgoodfish.goodfish.exception.orderException;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.gdutgoodfish.goodfish.exception.BaseException;

public class OrderNotExistException extends BaseException {
    public OrderNotExistException(String msg) {
        super(msg);
    }
}
