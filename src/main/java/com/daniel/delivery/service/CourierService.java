package com.daniel.delivery.service;

import com.daniel.delivery.dto.CourierDto;

import java.util.List;

public interface CourierService {

    List<CourierDto> getAllCourier();

    CourierDto getCourierById(Long id);

    CourierDto createCourier(CourierDto courierDto);

    void updateCourierById(Long id, CourierDto courierDto);

    void deleteCourierById(Long id);
}
