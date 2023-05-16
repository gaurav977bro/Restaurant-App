package com.java.orders;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.java.cms.SessionHelper;
import com.java.menu.Menu;

@ManagedBean(name = "oDAO")
@ViewScoped
public class OrdersDAOImpl implements OrderDAO {

	@Override
	public List<Orders> showOrders() {
		// TODO Auto-generated method stub
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Orders.class);
		List<Orders> orderList = cr.list();
		return orderList;
	}

	@Override
	public String addOrder(int cusId) {
		// TODO Auto-generated method stub
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Menu.class);
		cr.add(Restrictions.eq("cusId", cusId));
		Orders order = (Orders) cr.uniqueResult();
		session.save(order);
		return "completed";
	}

}
