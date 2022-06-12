package com.daniel.delivery.controller;

import com.daniel.delivery.dto.CourierDto;
import com.daniel.delivery.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courier")
public class CourierController {

    private final CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }


    @GetMapping
    public List<CourierDto> getOrderAll() {
        return courierService.getAllCourier();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourierDto createCourier(@RequestBody CourierDto courierDto) {
        return courierService.createCourier(courierDto);
    }

    @GetMapping("/{id}")
    public CourierDto getCourier(@PathVariable ("id") Long id){
        return courierService.getCourierById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCourier(@PathVariable Long id, @RequestBody CourierDto courierDto){
        courierService.updateCourierById(id,courierDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourier(@PathVariable Long id){
        courierService.deleteCourierById(id);
    }

}
