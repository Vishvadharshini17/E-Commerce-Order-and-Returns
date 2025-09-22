package com.kce.ecommerce.service;

import com.kce.ecommerce.model.*;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private Payment payment;
    private Shipment shipment;
    private boolean returned;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.returned = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
        product.reduceStock(quantity);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void makePayment(String paymentId) {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        this.payment = new Payment(paymentId, total, true);
    }

    public void shipOrder(String shipmentId) {
        this.shipment = new Shipment(shipmentId, "Shipped");
    }

    public Payment getPayment() {
        return payment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public boolean isReturned() {
        return returned;
    }

    public void markReturned() {
        this.returned = true;
    }
}
