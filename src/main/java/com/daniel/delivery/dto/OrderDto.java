package com.daniel.delivery.dto;

import com.daniel.delivery.entity.Courier;
import com.daniel.delivery.entity.Person;
import lombok.Data;
import java.util.Date;

@Data
public class OrderDto {

    private Long id;

    private String product;

    private Date dateOrder;

    private Person person;

    private Courier courier;
}
