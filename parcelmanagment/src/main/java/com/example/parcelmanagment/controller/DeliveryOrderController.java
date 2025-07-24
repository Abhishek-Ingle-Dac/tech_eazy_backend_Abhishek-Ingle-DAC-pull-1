package com.example.parcelmanagment.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.parcelmanagment.dto.DeliveryOrderDTO;
import com.example.parcelmanagment.entity.DeliveryOrder;
import com.example.parcelmanagment.entity.Vendor;
import com.example.parcelmanagment.service.DeliveryOrderService;

@RestController
@RequestMapping("/api/orders")

public class DeliveryOrderController {
  private final DeliveryOrderService service;

  
  public DeliveryOrderController(DeliveryOrderService service) {
	super();
	this.service = service;
}

@GetMapping("/today")
  public Page<DeliveryOrderDTO> today(
    @RequestParam(value="vendor", required=false) String vendorName,
    Pageable p
  ) {
    LocalDate today = LocalDate.now();
    return service.listOrders(today, vendorName, p)
      .map(this::toDto);
  }

  @PostMapping("/upload")
  public DeliveryOrderDTO upload(
    @RequestParam("file") MultipartFile file,
    @RequestParam("vendor") String vendor,
    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
  ) throws IOException {
    return toDto(service.createFromFile(file, vendor, date));
  }

  @PostMapping
  public DeliveryOrderDTO create(@RequestBody DeliveryOrderDTO dto) {
    return toDto(service.createInMemory(dto));
  }

  private DeliveryOrderDTO toDto(DeliveryOrder o) {
    DeliveryOrderDTO dto = new DeliveryOrderDTO();
    dto.setDate(o.getDeliveryDate());
    dto.setVendorName(o.getVendorName());
    dto.setTotalOrders(o.getTotalOrders());
    dto.setDownloadLink(o.getFilePath());
    return dto;
  }
}



