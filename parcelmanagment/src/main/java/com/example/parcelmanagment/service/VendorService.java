package com.example.parcelmanagment.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.parcelmanagment.entity.Vendor;
import com.example.parcelmanagment.repository.VendorRepo;

//VendorService.java
@Service
public class VendorService {
 private final VendorRepo repo;

 public VendorService(VendorRepo repo) {
     this.repo = repo;
 }

 public Page<Vendor> listAll(Pageable pageable) {
     return repo.findAll(pageable);
 }

 public Optional<Vendor> findByName(String name) {
     return repo.findByName(name);
 }

 public Vendor save(Vendor vendor) {
     return repo.save(vendor);
 }
}
