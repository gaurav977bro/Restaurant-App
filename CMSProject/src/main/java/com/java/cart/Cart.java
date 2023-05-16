package com.java.cart;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartinfoorders")
@ManagedBean(name = "cart")
@ViewScoped
public class Cart {

	@Id
	@Column(name = "cartId")
	private int cartId;

	@Column(name = "CUS_ID")
	private int cusId;

	@Column(name = "MEN_ITEM")
	private String menuItem;

	@Column(name = "MEN_SPECIALITY")
	private String menuSpeciality;

	@Column(name = "MEN_PRICE")
	private double menuPrice;

	@Column(name = "Restaurant_ID")
	private double restaurantId;

	@Column(name = "Quantity")
	private int quantity;

	@Column(name = "total")
	private double total;

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public void increment() {
		this.quantity++;
	}

	public void decrement() {
		this.quantity--;
	}

	public void cal() {
		if (quantity == 0) {
			this.total = menuPrice;

		}

		else {
			this.total = quantity * menuPrice;
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}

	public String getMenuSpeciality() {
		return menuSpeciality;
	}

	public void setMenuSpeciality(String menuSpeciality) {
		this.menuSpeciality = menuSpeciality;
	}

	public double getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}

	public double getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(double restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", menuItem=" + menuItem + ", menuSpeciality=" + menuSpeciality
				+ ", menuPrice=" + menuPrice + ", restaurantId=" + restaurantId + "]";
	}

}
