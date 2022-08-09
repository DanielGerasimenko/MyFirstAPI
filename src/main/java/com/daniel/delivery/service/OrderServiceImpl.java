package com.daniel.delivery.service;

import com.daniel.delivery.dto.CourierDto;
import com.daniel.delivery.dto.OrderDto;
import com.daniel.delivery.dto.PersonDto;
import com.daniel.delivery.entity.Courier;
import com.daniel.delivery.entity.Order;
import com.daniel.delivery.entity.Person;
import com.daniel.delivery.exception.OrderNotFoundException;
import com.daniel.delivery.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDto> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .map(this::convertToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return convertToOrderDto(order);
    }

    @Override
    public OrderDto getOrderByIdAndCourierId(Long orderId, Long courierId) {

        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = convertToOrderEntity(orderDto);
        Order orderCreated = orderRepository.save(order);
        return convertToOrderDto(orderCreated);
    }

    @Override
    public void updateOrderById(Long id, OrderDto orderDto) {
        if (!Objects.equals(id, orderDto.getId())) {
            throw new OrderNotFoundException(id);
        }

        Order order = convertToOrderEntity(orderDto);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }

    private OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        orderDto.setPersonDto(convertToPersonDto(order.getPerson()));
        orderDto.setCourierDto(convertToCourierDto(order.getCourier()));
        return orderDto;
    }

    private PersonDto convertToPersonDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    private CourierDto convertToCourierDto(Courier courier) {
        return modelMapper.map(courier, CourierDto.class);
    }

    private Order convertToOrderEntity(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        order.setPerson(convertToPersonEntity(orderDto.getPersonDto()));
        order.setCourier(convertToCourierEntity(orderDto.getCourierDto()));
        return order;
    }

    private Person convertToPersonEntity(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    private Courier convertToCourierEntity(CourierDto courierDto) {
        return modelMapper.map(courierDto, Courier.class);
    }
}