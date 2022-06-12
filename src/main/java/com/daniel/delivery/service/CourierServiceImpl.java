package com.daniel.delivery.service;

import com.daniel.delivery.dto.CourierDto;
import com.daniel.delivery.entity.Courier;
import com.daniel.delivery.exception.CourierNotFoundException;
import com.daniel.delivery.repository.CourierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;
    private final ModelMapper modelMapper;

    public CourierServiceImpl(CourierRepository courierRepository, ModelMapper modelMapper) {
        this.courierRepository = courierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourierDto> getAllCourier() {
        List<Courier> courierList = courierRepository.findAll();
        return courierList.stream()
                .map(this::convertToCourierDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourierDto getCourierById(Long id) {
        Courier courier = courierRepository.findById(id).orElseThrow(() -> new CourierNotFoundException(id));
        return convertToCourierDto(courier);
    }

    @Override
    public CourierDto createCourier(CourierDto courierDto) {
        Courier courier = convertToCourierEntity(courierDto);
        Courier courierCreated = courierRepository.save(courier);
        return convertToCourierDto(courierCreated);
    }

    @Override
    public void updateCourierById(Long id, CourierDto courierDto) {
        if (!Objects.equals(id, courierDto.getId())) {
            throw new CourierNotFoundException(id);
        }
        Courier courier = convertToCourierEntity(courierDto);
        courierRepository.save(courier);
    }

    @Override
    public void deleteCourierById(Long id) {
        Courier courier = courierRepository.findById(id).orElseThrow(() -> new CourierNotFoundException(id));
        courierRepository.delete(courier);
    }

    private CourierDto convertToCourierDto(Courier courier) {
        return modelMapper.map(courier, CourierDto.class);
    }

    private Courier convertToCourierEntity(CourierDto courierDto) {
        return modelMapper.map(courierDto, Courier.class);
    }
}
