package com.example.parcelmanagment.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Vendor {
@Id @GeneratedValue private Long id;
private String name;
@Enumerated(EnumType.STRING)
private SubscriptionType type;
@OneToMany(mappedBy="vendor")
private List<DeliveryOrder> orders;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public SubscriptionType getType() {
	return type;
}
public void setType(SubscriptionType type) {
	this.type = type;
}
public List<DeliveryOrder> getOrders() {
	return orders;
}
public void setOrders(List<DeliveryOrder> orders) {
	this.orders = orders;
}



}



