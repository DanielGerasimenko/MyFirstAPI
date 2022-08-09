package com.daniel.delivery.service;

import com.daniel.delivery.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrder();

    OrderDto getOrderById(Long id);

    OrderDto getOrderByIdAndCourierId(Long orderId, Long courierId);

    OrderDto createOrder(OrderDto orderDto);

    void updateOrderById(Long id, OrderDto orderDto);

    void deleteOrderById(Long id);
}
