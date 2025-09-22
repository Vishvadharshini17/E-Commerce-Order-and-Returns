package com.kce.ecommerce.model;

import com.kce.ecommerce.service.Order;

public class ReturnRequest {
    private Order order;
    private String reason;
    private String status;

    public ReturnRequest(Order order, String reason) {
        this.order = order;
        this.reason = reason;
        this.status = "Pending";
    }

    public void approveReturn() {
        order.getItems().forEach(item -> item.getProduct().increaseStock(item.getQuantity()));
        status = "Approved";
    }

    public void rejectReturn() {
        status = "Rejected";
    }

    public String getStatus() { return status; }
}
