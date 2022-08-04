package com.daniel.delivery.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;
}
