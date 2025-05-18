package com.example.dalnoboy.driver;

public class OrderItem {
    private final String orderNumber;
    private final String pickupAddress;
    private final String deliveryAddress;
    private final String eta;

    public OrderItem(String orderNumber, String pickupAddress,
                     String deliveryAddress, String eta) {
        this.orderNumber = orderNumber;
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
        this.eta = eta;
    }

    public String getOrderNumber() { return orderNumber; }
    public String getPickupAddress() { return pickupAddress; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getEta() { return eta; }
}