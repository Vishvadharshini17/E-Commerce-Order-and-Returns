package com.kce.ecommerce.model;

public class Shipment {
    private String shipmentId;
    private String status;

    public Shipment(String shipmentId, String status) {
        this.shipmentId = shipmentId;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
