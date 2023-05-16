package com.java.orders;

import java.util.List;

public interface OrderDAO {

	List<Orders> showOrders();

	String addOrder(int cusId);
}
