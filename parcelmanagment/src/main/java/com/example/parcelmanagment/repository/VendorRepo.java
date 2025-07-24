package com.example.parcelmanagment.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parcelmanagment.entity.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Long> {
    Page<Vendor> findAll(Pageable pageable);
    Optional<Vendor> findByName(String name);
}

	

