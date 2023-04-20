package edu.iu.c322.orderservice.controller;

import java.util.List;
import java.util.Optional;

import edu.iu.c322.orderservice.model.DeleteRequest;
import edu.iu.c322.orderservice.model.ItemOrd;
import edu.iu.c322.orderservice.model.Orders;
import edu.iu.c322.orderservice.model.UpdateRequest;
import edu.iu.c322.orderservice.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{customerId}")
    public List<Orders> findByCustomerId(@PathVariable int customerId) {
        return repository.findByCustomerId(customerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public int create(@Valid @RequestBody Orders order) {

        // Need a for loop here to set the order to the item(s)

        Orders addedOrder = repository.save(order);
        return addedOrder.getOrderId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/return")
    public void update(@Valid @RequestBody UpdateRequest request) {
        Optional<Orders> optionalOrder = repository.findById(request.getOrderId());
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            boolean itemFound = false;
            for (ItemOrd item : order.getItems()) {
                if (item.getItemId() == request.getItemId()) {
                    item.setReturned(true);
                    item.setReason(request.getReason());
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                throw new IllegalStateException("Item ID is not valid.");
            }
            repository.save(order);
        }
        else {
            throw new IllegalStateException("Order ID is not valid.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{orderId}")
    public void delete(@Valid @RequestBody DeleteRequest request) {
        Optional<Orders> optionalOrder = repository.findById(request.getOrderId());
        if (optionalOrder.isPresent()) {
            Orders order = optionalOrder.get();
            order.setCancelOrder(request.getCancelOrder());
            repository.save(order);
        }
        else {
            throw new IllegalStateException("Order ID is not valid.");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable int orderId) {
        repository.deleteById(orderId);
    }
}
