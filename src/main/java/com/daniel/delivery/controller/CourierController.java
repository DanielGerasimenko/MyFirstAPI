package com.daniel.delivery.controller;

import com.daniel.delivery.entity.Courier;
import com.daniel.delivery.exception.CourierNotFoundException;
import com.daniel.delivery.repository.CourierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courier")
public class CourierController {

    private final CourierRepository courierRepository;


    public CourierController(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @GetMapping("/all")
    List<Courier> all(){
        return courierRepository.findAll();
    }

    @PostMapping()
    Courier newCourier(@RequestBody Courier newCourier){
        return courierRepository.save(newCourier);
    }

    @GetMapping("/{id}")
    Courier one(@PathVariable Long id){
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException(id));
    }

    @PutMapping("/{id}/edit")
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

    @DeleteMapping("/{id}/delete")
    public void deleteCourier(@PathVariable Long id){
        courierRepository.deleteById(id);
    }
}
