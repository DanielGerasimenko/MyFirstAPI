package com.daniel.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    @CreationTimestamp
    private LocalDateTime dateOrder;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne()
    @JoinColumn(name = "courier_id")
    private Courier courier;
}
