package com.example.parcelmanagment.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parcelmanagment.entity.Vendor;
import com.example.parcelmanagment.service.VendorService;

@RestController
@RequestMapping("/api/vendors")

public class VendorController{
  private final VendorService service;

  @GetMapping
  public Page<Vendor> list(Pageable p) {
    return service.listAll(p);
  }

public VendorController(VendorService service) {
	super();
	this.service = service;
}
  
  
}
