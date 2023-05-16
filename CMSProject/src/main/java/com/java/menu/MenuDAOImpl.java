package com.java.menu;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.java.cms.SessionHelper;
import com.java.restaurant.Restaurant;

@ManagedBean(name = "menuDAO")
@SessionScoped
public class MenuDAOImpl implements MenuDAO {

	@Override
	public List<Menu> showMenuByRestaurant(int rid) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Menu.class);
		cr.add(Restrictions.eq("restaurantId", rid));
		List<Menu> menuList = cr.list();
		return menuList;
	}

	@Override
	public String searchMenu(int menuId) {
		// TODO Auto-generated method stub
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Menu.class);
		cr.add(Restrictions.eq("menuId", menuId));
		Menu m = (Menu) cr.uniqueResult();

		sessionMap.put("menuInfo", m);

		return "ShowCart.xhtml?faces-redirect=true";
	}

	public String searchMenuByRestaurantId(int resId) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Menu.class);
		cr.add(Restrictions.eq("restaurantId", resId));
		List<Menu> menuList = cr.list();
		Criteria cr2 = session.createCriteria(Restaurant.class);
		cr2.add(Restrictions.eq("restaurantId", resId));
		Restaurant res = (Restaurant) cr2.uniqueResult();
		sessionMap.put("menuList", menuList);
		sessionMap.put("resInfo", res);
		return "RestaurantDetails.xhtml?faces-redirect=true";
	}

}
