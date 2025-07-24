package com.example.parcelmanagment.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parcelmanagment.entity.DeliveryOrder;

public interface DeliveryOrderRepo extends JpaRepository<DeliveryOrder, Long> {
    Page<DeliveryOrder> findByVendorNameAndDeliveryDate(String vendorName, LocalDate date, Pageable pageable);
    Page<DeliveryOrder> findByVendorName(String vendorName, Pageable pageable);
    Page<DeliveryOrder> findByDeliveryDate(LocalDate date, Pageable pageable);
}

