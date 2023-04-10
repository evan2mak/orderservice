package edu.iu.c322.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private int customerId;
    private double total;

    @ManyToOne(cascade = CascadeType.ALL)
    @Valid
    private ShippingAddress shippingAddress;
    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    private List<ItemOrd> items;
    @ManyToOne(cascade = CascadeType.ALL)
    @Valid
    private Payment payment;
    private String cancelOrder;
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<ItemOrd> getItems() {
        return items;
    }
    public void setItems(List<ItemOrd> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getCancelOrder() {
        return cancelOrder;
    }

    public void setCancelOrder(String cancelOrder) {
        this.cancelOrder = cancelOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders order = (Orders) o;
        return customerId == order.customerId && Objects.equals(total, order.total) && Objects.equals(shippingAddress, order.shippingAddress) && Objects.equals(items, order.items) && Objects.equals(payment, order.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, total, shippingAddress, items, payment);
    }
}
