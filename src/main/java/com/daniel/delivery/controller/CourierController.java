package com.daniel.delivery.controller;

import com.daniel.delivery.entity.Courier;
import com.daniel.delivery.exception.CourierNotFoundException;
import com.daniel.delivery.repository.CourierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourierController {

    private final CourierRepository courierRepository;


    public CourierController(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @GetMapping("/courier")
    List<Courier> all(){
        return courierRepository.findAll();
    }

    @PostMapping("/courier")
    Courier newCourier(@RequestBody Courier newCourier){
        return courierRepository.save(newCourier);
    }

    @GetMapping("/courier/{id}")
    Courier one(@PathVariable Long id){
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException(id));
    }

    @PutMapping("/courier/{id}")
    Courier replaceCourier (@RequestBody Courier newCourier, @PathVariable Long id){
        return courierRepository.findById(id)
                .map(courier -> {
                    courier.setFirstName(newCourier.getFirstName());
                    courier.setLastName(newCourier.getLastName());
                    return courierRepository.save(courier);
                }).orElseGet(()->{
                    newCourier.setId(id);
                    return courierRepository.save(newCourier);
                });
    }

    @DeleteMapping("/courier/{id}")
    public void deleteCourier(@PathVariable Long id){
        courierRepository.deleteById(id);
    }
}
