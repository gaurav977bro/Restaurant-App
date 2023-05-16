package com.java.cart;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.java.cms.Customer;
import com.java.cms.SessionHelper;
import com.java.menu.Menu;
import com.java.total.Total;

@ManagedBean(name = "cartDAO")
@RequestScoped
public class CartDAOImpl implements CartDAO {

	@Override
	public String addToCart(Cart c) {
		// TODO Auto-generated method stub
		new Total().increment();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Menu m = (Menu) sessionMap.get("menuInfo");
		Customer cust = (Customer) sessionMap.get("customerInfo");
		Criteria cr = session.createCriteria(Cart.class);
		cr.add(Restrictions.eq("menuItem", m.getMenuItem()));
		cr.setProjection(Projections.rowCount());
		Long count = (Long) cr.uniqueResult();

		int qty = (int) sessionMap.get("cartQuantity");
		double total = (double) sessionMap.get("cartTotal");
		c.setCusId(cust.getCusId());
		c.setMenuItem(m.getMenuItem());
		c.setMenuPrice(m.getMenuPrice());
		c.setMenuSpeciality(m.getMenuSpeciality());
		c.setRestaurantId(m.getRestaurantId());
		c.setQuantity(qty);
		c.setTotal(total);

		session.save(c);
		t.commit();
		return "ItemsInCart.xhtml?faces-redirect=true";

	}

	@Override
	public List<Cart> showCartItems() {
		// TODO Auto-generated method stub
		SessionFactory sf = SessionHelper.getConnection();

		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Cart.class);
		List<Cart> cartList = cr.list();
		return cartList;

	}

	public List<Cart> showCartItemsbyId(int cusId) {
		// TODO Auto-generated method stub
		SessionFactory sf = SessionHelper.getConnection();

		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Cart.class);
		cr.add(Restrictions.eq("cusId", cusId));
		List<Cart> cartList = cr.list();
		return cartList;

	}

	public String deleteCart(Cart cart) {

		SessionFactory sf = SessionHelper.getConnection();

		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Cart.class);
		Transaction t = session.beginTransaction();
		session.delete(cart);
		t.commit();

		return "ItemsInCart.xhtml?faces-redirect=true";

	}

}
