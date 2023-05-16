package com.java.menu;

import java.util.List;

public interface MenuDAO {

	List<Menu> showMenuByRestaurant(int rid);

	String searchMenu(int menuId);
}
