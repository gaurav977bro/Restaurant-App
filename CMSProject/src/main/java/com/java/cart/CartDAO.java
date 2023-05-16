package com.java.cart;

import java.util.List;

public interface CartDAO {

	String addToCart(Cart cart);

	List<Cart> showCartItems();
}
