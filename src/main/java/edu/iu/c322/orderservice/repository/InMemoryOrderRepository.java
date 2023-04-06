package edu.iu.c322.orderservice.repository;

import edu.iu.c322.orderservice.model.ItemOrd;
import edu.iu.c322.orderservice.model.Orders;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryOrderRepository {
    private List<Orders> orders = new ArrayList<>();

    public List<Orders> findByCustomerId(int customerId) {
        List<Orders> customerOrders = orders.stream().filter(order -> order.getCustomerId() == customerId).collect(Collectors.toList());
        return customerOrders.isEmpty() ? Collections.emptyList() : customerOrders;
    }

    public int create(Orders order) {
        int orderId = orders.size() + 1;
        order.setOrderId(orderId);

        int itemId = 1;
        for (ItemOrd item : order.getItems()) {
            item.setItemId(itemId);
            itemId++;
        }

        orders.add(order);
        return orderId;
    }

    public void update(int orderId, int itemId, String reason) {
        Orders order = getOrderById(orderId);
        if (order != null) {
            ItemOrd item = order.getItems().stream().filter(i -> i.getItemId() == itemId).findFirst().orElse(null);
            if (item != null) {
                item.setReturned(true);
                item.setReason(reason);
            }
            else {
                throw new IllegalStateException("Item ID is not valid.");
            }
        }
        else {
            throw new IllegalStateException("Order ID is not valid.");
        }
    }

    public void delete(int orderId) {
        Orders x = getOrderById(orderId);
        if (x != null) {
            orders.remove(orderId - 1);
        }
        else {
            throw new IllegalStateException("Order ID is not valid.");
        }
    }

    private Orders getOrderById(int orderId) {
        return orders.stream().filter(x -> x.getOrderId() == orderId).findAny().orElse(null);
    }
}
