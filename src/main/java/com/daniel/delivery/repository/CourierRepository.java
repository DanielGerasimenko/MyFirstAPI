package com.daniel.delivery.repository;

import com.daniel.delivery.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier , Long> {
}
