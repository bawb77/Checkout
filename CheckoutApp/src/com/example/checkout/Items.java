package com.example.checkout;

public class Items {
	private String item;
	private double price;



	public void setItem(String Item, double Price) {
		item = Item;
		price = Price;
	}
	public String getItem() {
		return item;
	}
	public double getPrice() {
		return price;
	}
}
