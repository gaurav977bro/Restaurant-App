package com.java.validations;

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
import com.java.cms.SessionHelper;

@FacesValidator("com.java.validations.EmailDuplicationCheck")
@ViewScoped
public class EmailDuplicationCheck implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		String uniqueColumn = (String) comp.getAttributes().get("uniqueColumn");
		String email = (String) value;

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Customer.class);
		cr.add(Restrictions.eq("cusEmail", email));
		cr.setProjection(Projections.rowCount());
		Long count = (Long) cr.uniqueResult();

		if (count == 1) {
			FacesMessage msg = new FacesMessage("Already in use", uniqueColumn);
			context.addMessage(comp.getClientId(context), msg);
			throw new ValidatorException(msg);
		}
	}

}
