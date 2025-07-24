package com.example.parcelmanagment.dto;

import java.time.LocalDate;

//DeliveryOrderDTO.java
public class DeliveryOrderDTO {
private LocalDate date;
private String vendorName;
private int totalOrders;
private String downloadLink;
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public String getVendorName() {
	return vendorName;
}
public void setVendorName(String vendorName) {
	this.vendorName = vendorName;
}
public int getTotalOrders() {
	return totalOrders;
}
public void setTotalOrders(int totalOrders) {
	this.totalOrders = totalOrders;
}
public String getDownloadLink() {
	return downloadLink;
}
public void setDownloadLink(String downloadLink) {
	this.downloadLink = downloadLink;
}

}



