package com.daniel.delivery.controller;

import com.daniel.delivery.dto.OrderDto;
import com.daniel.delivery.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public List<OrderDto> getOrderAll() {
        return orderService.getAllOrder();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable ("id") Long id){
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto){
        orderService.updateOrderById(id,orderDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable Long id){
        orderService.deleteOrderById(id);
    }

}