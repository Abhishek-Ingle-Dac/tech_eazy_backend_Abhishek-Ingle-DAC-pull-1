package com.example.parcelmanagment.service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.parcelmanagment.dto.DeliveryOrderDTO;
import com.example.parcelmanagment.entity.DeliveryOrder;
import com.example.parcelmanagment.repository.DeliveryOrderRepo;

@Service
public class DeliveryOrderService {

    @Value("${file.upload.dir:uploads}")
    private String uploadDir;

    private final DeliveryOrderRepo orderRepo;

    public DeliveryOrderService(DeliveryOrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public DeliveryOrder createFromFile(MultipartFile file, String vendorName, LocalDate date) throws IOException {
        Path dirPath = Paths.get(uploadDir, vendorName);
        Files.createDirectories(dirPath);

        Path filePath = dirPath.resolve(UUID.randomUUID() + "_" + file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath);

        List<String> lines = Files.readAllLines(filePath);
        int totalOrders = lines.size();

        DeliveryOrder order = new DeliveryOrder();
        order.setVendorName(vendorName);
        order.setDeliveryDate(date);
        order.setFilePath(filePath.toString());
        order.setTotalOrders(totalOrders);

        return orderRepo.save(order);
    }

    public DeliveryOrder createInMemory(DeliveryOrderDTO dto) {
        DeliveryOrder order = new DeliveryOrder();
        order.setVendorName(dto.getVendorName());
        order.setDeliveryDate(dto.getDate());
        order.setTotalOrders(dto.getTotalOrders());
        order.setFilePath("in-memory");
        return orderRepo.save(order);
    }

    public Page<DeliveryOrder> getOrders(String vendorName, LocalDate date, Pageable pageable) {
        if (vendorName != null && date != null) {
            return orderRepo.findByVendorNameAndDeliveryDate(vendorName, date, pageable);
        } else if (vendorName != null) {
            return orderRepo.findByVendorName(vendorName, pageable);
        } else if (date != null) {
            return orderRepo.findByDeliveryDate(date, pageable);
        } else {
            return orderRepo.findAll(pageable);
        }
    }

    public Page<DeliveryOrder> listOrders(LocalDate date, String vendorName, Pageable pageable) {
        return getOrders(vendorName, date, pageable);
    }
}
