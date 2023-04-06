package edu.iu.c322.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int billingAddressId;

    @NotEmpty(message = "State cannot be empty.")
    private String state;

    @NotEmpty(message = "City cannot be empty.")
    private String city;

    private int postalCode;

    public int getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(int billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillingAddress that)) return false;
        return getBillingAddressId() == that.getBillingAddressId() && getPostalCode() == that.getPostalCode() && Objects.equals(getState(), that.getState()) && Objects.equals(getCity(), that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBillingAddressId(), getState(), getCity(), getPostalCode());
    }
}
