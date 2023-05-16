package com.java.cms;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

@ManagedBean(name = "customerDAO")
@ViewScoped
public class CustomerDAOImpl implements CustomerDAO {

	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String addCustomer(Customer customer) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		String pwd = EntryptPassword.getCode(customer.getCusPassword());
		customer.setCusPassword(pwd);
		Transaction t = session.beginTransaction();
		session.save(customer);
		t.commit();
		return "Thanks.xhtml?faces-redirect=true";
	}

	public String updateCustomer1(Customer customer) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		String pwd = EntryptPassword.getCode(customer.getCusPassword());
		customer.setCusPassword(pwd);
		Transaction t = session.beginTransaction();
		session.update(customer);
		t.commit();
		return "Thanks.xhtml?faces-redirect=true";
	}

	@Override
	public List<Customer> showCustomer() {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		List<Customer> cuList = cr.list();
		return cuList;
	}

	@Override
	public String validateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String encPwd = EntryptPassword.getCode(customer.getCusPassword());
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("cusUsername", customer.getCusUsername()));
		cr.add(Restrictions.eq("cusPassword", encPwd.trim()));
		cr.setProjection(Projections.rowCount());
		Long count = (Long) cr.uniqueResult();

		if (count == 1) {
			Customer c = searchCustomer(customer.getCusUsername());

			sessionMap.put("customerInfo", c);

			return "CustomerHomepage.xhtml?faces-redirect=true";
		}

		else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Incorrect password");
			facesContext.addMessage("form:password", facesMessage);
			return "error";
		}

	}

	public Customer searchCustomer(String username) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("cusUsername", username));
		Customer customer = (Customer) cr.uniqueResult();
		return customer;

	}

	@Override
	public String updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();

		session.update(customer);
		t.commit();
		return "CustomerDetails.xhtml?faces-redirect=true";
	}

	@Override
	public String searchCustomerById(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("cusId", id));
		Customer c = (Customer) cr.uniqueResult();
		sessionMap.put("info", c);
		return "updateCustomer.xhtml?faces-redirect=true";
	}

	public String changePassword(Customer pass, String password) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		String enc = EntryptPassword.getCode(password);
		Transaction t = session.beginTransaction();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("cusUsername", pass.getCusUsername()));
		Customer customer = (Customer) cr.uniqueResult();
		System.out.println(customer.getCusUsername());
		customer.setCusPassword(enc);
		session.update(customer);
		t.commit();

		return "CustomerDetails.xhtml?faces-redirect=true";
	}
}
