package com.java.validations;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.java.cms.Customer;
import com.java.cms.SessionHelper;

@FacesValidator("com.java.validations.UsernameValidation")
@RequestScoped
public class UsernameValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		String username = (String) value;

		String uniqueColumn = (String) context.getAttributes().get("uniqueColumn");
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("cusUsername", username));
		Customer customer = (Customer) cr.uniqueResult();
		cr.setProjection(Projections.rowCount());
		Long count = (Long) cr.uniqueResult();

		if (count == 1) {
			FacesMessage msg = new FacesMessage("Username already exits, Try new", uniqueColumn);
			context.addMessage(comp.getClientId(context), msg);
			throw new ValidatorException(msg);

		}

		else if (username.length() < 6) {
			FacesMessage msg = new FacesMessage("Length too short for username", uniqueColumn);
			context.addMessage(comp.getClientId(context), msg);
			throw new ValidatorException(msg);
		}

		else if (!check(username)) {
			FacesMessage msg = new FacesMessage("Username must contain letters", uniqueColumn);
			context.addMessage(comp.getClientId(context), msg);
			throw new ValidatorException(msg);
		}

	}

	public boolean check(String username) {
		int letterCount = 0;

		if (username.length() >= 6) {
			for (int i = 0; i < username.length(); i++) {
				if (Character.isAlphabetic(username.charAt(i))) {
					letterCount++;
				}
			}
		}

		if (letterCount > 0) {
			return true;
		}

		else {
			return false;
		}

	}

}
