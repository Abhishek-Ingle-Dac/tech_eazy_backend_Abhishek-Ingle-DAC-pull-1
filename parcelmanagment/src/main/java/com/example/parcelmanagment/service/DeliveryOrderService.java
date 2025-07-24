package com.example.parcelmanagment.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.example.parcelmanagment.repository.VendorRepo;

@Service
public class DeliveryOrderService {

    @Value("${file.upload.dir:uploads}")
    private String uploadDir;

    private final DeliveryOrderRepo orderRepo;
    private final VendorRepo vendorRepo;

    public DeliveryOrderService(DeliveryOrderRepo orderRepo, VendorRepo vendorRepo) {
        this.orderRepo = orderRepo;
        this.vendorRepo = vendorRepo;
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
            return orderRepo.findByVendor_NameAndDeliveryDate(vendorName, date, pageable);
        } else if (vendorName != null) {
            return orderRepo.findByVendor_Name(vendorName, pageable);
        } else if (date != null) {
            return orderRepo.findByDeliveryDate(date, pageable);
        } else {
            return orderRepo.findAll(pageable);
        }
    }

    public Page<DeliveryOrder> listOrders(LocalDate date, String vendorName, Pageable pageable) {
        if (date != null && vendorName != null) {
            return orderRepo.findByVendor_NameAndDeliveryDate(vendorName, date, pageable);
        } else if (vendorName != null) {
            return orderRepo.findByVendor_Name(vendorName, pageable);
        } else if (date != null) {
            return orderRepo.findByDeliveryDate(date, pageable);
        } else {
            return orderRepo.findAll(pageable);
        }
    }

//	public DeliveryOrder processUpload(MultipartFile file, String vendor, LocalDate date) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
