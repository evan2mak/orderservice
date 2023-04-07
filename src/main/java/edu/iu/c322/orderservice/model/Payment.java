package edu.iu.c322.orderservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private int paymentId;

    @NotEmpty(message = "Payment method cannot be empty.")
    @Column(name = "method")
    private String method;

    @NotEmpty(message = "Credit card number cannot be empty.")
    @Column(name = "number")
    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    private BillingAddress billingAddress;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(method, payment.getMethod()) && Objects.equals(number, payment.getNumber()) && Objects.equals(billingAddress, payment.getBillingAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, number, billingAddress);
    }
}

