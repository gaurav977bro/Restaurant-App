package com.java.wallet;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		WalletDAO dao = new WalletDAOImpl();
		List<Wallet> listW = dao.showWalletsForCustomer(1);
		for (Wallet wallet : listW) {
			System.out.println(wallet);
		}

	}
}
