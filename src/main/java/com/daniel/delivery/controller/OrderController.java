package com.daniel.delivery.controller;


import com.daniel.delivery.entity.Order;
import com.daniel.delivery.exception.OrderNotFoundException;
import com.daniel.delivery.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/all")
    List<Order> all(){
        return orderRepository.findAll();
    }

    @PostMapping()
    Order newOrder(@RequestBody Order newOrder){
        return orderRepository.save(newOrder);
    }

    @GetMapping("/{id}")
    Order one(@PathVariable Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }


    @PutMapping("/{id}/edit")
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

    @DeleteMapping("/{id}/delete")
    public void deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id);
    }
}
