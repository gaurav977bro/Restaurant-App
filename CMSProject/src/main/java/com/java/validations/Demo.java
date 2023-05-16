package com.java.validations;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.java.validations.Demo")
@ViewScoped
public class Demo implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		String uniqueColumn = (String) context.getAttributes().get("uniqueColumn");
		String input = (String) value;

		if (!check(input)) {
			FacesMessage msg = new FacesMessage("Field cannot be empty", uniqueColumn);
			context.addMessage(comp.getClientId(context), msg);
			throw new ValidatorException(msg);
		}
	}

	public Boolean check(String value) {
		if (value.length() == 0) {
			return false;
		}

		return true;
	}

}
