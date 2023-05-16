package com.java.wallet;

import java.util.List;

public interface WalletDAO {

	List<Wallet> showWallets();

	public List<Wallet> showWalletsForCustomer(int id);
}
