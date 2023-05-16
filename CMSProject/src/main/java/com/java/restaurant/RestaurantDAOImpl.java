package com.java.restaurant;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.java.cms.SessionHelper;

@ManagedBean(name = "rDAO")
@ViewScoped
public class RestaurantDAOImpl implements RestaurantDAO {

	private Integer restaurantId;
	private String localCode;

	

	

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getLocalCode() {
		return localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	@Override
	public List<Restaurant> showRestaurants() {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Restaurant.class);
		List<Restaurant> rList = cr.list();
		return rList;
	}

	public Integer searchRestaurantByName(String restaurantName) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Restaurant.class);
		cr.add(Restrictions.eq("restaurantName", restaurantName));

		Projection projection = Projections.property("restaurantId"); 
		cr.setProjection(projection); 
		Integer rid = (Integer)cr.uniqueResult();
		System.out.println("method " +rid);
		return rid;

	}

	public void restaurantLocaleCodeChanged(ValueChangeEvent e){
		Map<String,Object> sessionMap = 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String rname = e.getNewValue().toString();
		Integer rid = searchRestaurantByName(rname);
		this.restaurantId = rid;
		this.localCode=rname;
		sessionMap.put("restaurantId", restaurantId);
	}
	
	public List<String> showRestaurantNames()
	{
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Restaurant.class);
		Projection projection = Projections.distinct(Projections.property("restaurantName")); 
		cr.setProjection(projection); 
		List<String> list = cr.list();
		return list;
	}

}
