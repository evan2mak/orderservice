package edu.iu.c322.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
public class ItemOrd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    @NotEmpty(message = "Name cannot be empty.")
    private String name;
    private int quantity;

    private double price;
    private boolean returned;
    private String reason;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrd item = (ItemOrd) o;
        return quantity == item.getQuantity() && name.equals(item.getName()) && Objects.equals(price, item.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, price);
    }
}
