package edu.iu.c322.orderservice.model;

public class DeleteRequest {
    private int orderId;
    private String cancelOrder;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCancelOrder() {
        return cancelOrder;
    }

    public void setCancelOrder(String cancelOrder) {
        this.cancelOrder = cancelOrder;
    }
}
