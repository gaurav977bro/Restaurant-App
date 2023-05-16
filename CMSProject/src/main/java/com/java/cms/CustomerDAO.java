package com.java.cms;

import java.util.List;

public interface CustomerDAO {

	String addCustomer(Customer customer);
	String validateCustomer(Customer customer);
	List<Customer> showCustomer();
	String updateCustomer(Customer customer);
	 String searchCustomerById(int id);
}
