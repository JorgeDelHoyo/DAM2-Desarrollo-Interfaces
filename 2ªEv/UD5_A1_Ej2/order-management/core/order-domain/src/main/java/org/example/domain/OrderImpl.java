package org.example.domain;
import org.example.api.OrderInterface;

public class OrderImpl implements OrderInterface {
    public String getMessage() { return "Pedido creado"; }
}