package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Items implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int itemId;
  private String itemName;
  private Double itemPrice;
  private int itemQty;
  public Items() {}
public Items(int itemId, String itemName, Double itemPrice, int itemQty) {
	super();
	this.itemId = itemId;
	this.itemName = itemName;
	this.itemPrice = itemPrice;
	this.itemQty = itemQty;
}
public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public Double getItemPrice() {
	return itemPrice;
}
public void setItemPrice(Double itemPrice) {
	this.itemPrice = itemPrice;
}
public int getItemQty() {
	return itemQty;
}
public void setItemQty(int itemQty) {
	this.itemQty = itemQty;
}
  
}
