package com.example.parcelmanagment.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DeliveryOrder {
@Id @GeneratedValue private Long id;
private LocalDate deliveryDate;
private int totalOrders;
private String filePath;
private String vendorName;
@ManyToOne private Vendor vendor;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public LocalDate getDeliveryDate() {
	return deliveryDate;
}
public void setDeliveryDate(LocalDate deliveryDate) {
	this.deliveryDate = deliveryDate;
}
public int getTotalOrders() {
	return totalOrders;
}
public void setTotalOrders(int totalOrders) {
	this.totalOrders = totalOrders;
}
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}
public Vendor getVendor() {
	return vendor;
}
public void setVendor(Vendor vendor) {
	this.vendor = vendor;
}
public String getVendorName() {
    return vendorName;
}

public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
}


}
