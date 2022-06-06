package com.daniel.delivery.controller;


import com.daniel.delivery.entity.Order;
import com.daniel.delivery.exception.OrderNotFoundException;
import com.daniel.delivery.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/order")
    List<Order> all(){
        return orderRepository.findAll();
    }

    @PostMapping("/order")
    Order newOrder(@RequestBody Order newOrder){
        return orderRepository.save(newOrder);
    }

    @GetMapping("/order/{id}")
    Order one(@PathVariable Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }


    @PutMapping("/order/{id}")
    Order replaceOrder (@RequestBody Order newOrder, @PathVariable Long id){
        return orderRepository.findById(id)
                .map(order -> {
                    order.setProduct(newOrder.getProduct());
                    order.setDateOrder(newOrder.getDateOrder());
                    order.setPerson(newOrder.getPerson());
                    order.setCourier(newOrder.getCourier());
                    return orderRepository.save(order);
                }).orElseGet(()->{
                    newOrder.setId(id);
                    return orderRepository.save(newOrder);
                });
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
    }
}
