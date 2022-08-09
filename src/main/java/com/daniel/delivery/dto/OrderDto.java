package com.daniel.delivery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Long id;

    private String product;

    private LocalDateTime dateOrder;

    @JsonProperty("person")
    private PersonDto personDto;

    @JsonProperty("courier")
    private CourierDto courierDto;

//    String address;
//    String contactName;
//    String contactPhone;
//
}
