package com.java.cms;

import java.util.Scanner;
public class Test {

	public static void main(String[] args)
	
	{
		CustomerDAO dao = new CustomerDAOImpl();
		Customer cus = new Customer();
		System.out.println(dao.showCustomer());
		/*Scanner sc = new Scanner(System.in);
		System.out.println("ENTER ID:");
		cus.setCusId(sc.nextInt());
		
		System.out.println("ENTER NAME");
		cus.setCusName(sc.next());
		
		System.out.println("enter username");
		cus.setCusUsername(sc.next());
		
		System.out.println("enter email");
		cus.setCusEmail(sc.next());
		
		System.out.println("enter password");
		cus.setCusPassword(sc.next());
		
		System.out.println("enter phone");
		cus.setCusPhone(sc.next());
		
		System.out.println(dao.addCustomer(cus));*/
	}
}

