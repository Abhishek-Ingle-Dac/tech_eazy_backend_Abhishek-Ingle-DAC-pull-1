package com.example.parcelmanagment.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parcelmanagment.entity.DeliveryOrder;

public interface DeliveryOrderRepo extends JpaRepository<DeliveryOrder, Long> {
    Page<DeliveryOrder> findAll(Pageable pageable);
    Page<DeliveryOrder> findByVendor_NameAndDeliveryDate(String name, LocalDate date, Pageable pageable);
    Page<DeliveryOrder> findByDeliveryDate(LocalDate date, Pageable pageable);
    Page<DeliveryOrder> findByVendor_Name(String name, Pageable pageable);
}
