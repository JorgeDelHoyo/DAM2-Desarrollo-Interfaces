package org.example.service;
import org.example.domain.OrderImpl;
import org.example.utils.OrderUtils;

public class OrderService {
    public void process() {
        OrderImpl order = new OrderImpl();
        System.out.println(OrderUtils.formatText(order.getMessage()));
    }
}