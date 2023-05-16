package com.java.validations;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
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
import com.java.cms.EntryptPassword;
import com.java.cms.SessionHelper;

@FacesValidator("com.java.validations.OldPasswordValidation")
@ViewScoped
public class OldPasswordValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		String uniqueColumn = (String) context.getAttributes().get("uniqueColumn");
		String pass = (String) value;
		String encryptedPassword = EntryptPassword.getCode(pass);
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		Customer customer = (Customer) sessionMap.get("customerInfo");
		String username = customer.getCusUsername();
		cr.add(Restrictions.eq("cusUsername", username));
		cr.add(Restrictions.eq("cusPassword", encryptedPassword));
		cr.setProjection(Projections.rowCount());
		System.out.println(encryptedPassword);
		System.out.println(username);
		Long count = (Long) cr.uniqueResult();
		System.out.println(count);
		if (count == 0) {

			FacesMessage msg = new FacesMessage("Incorrect password", uniqueColumn);
			context.addMessage(comp.getClientId(context), msg);
			throw new ValidatorException(msg);

		}
	}

}
