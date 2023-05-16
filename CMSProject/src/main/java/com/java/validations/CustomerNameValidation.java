package com.java.validations;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.java.validations.CustomerNameValidation")
@ViewScoped
public class CustomerNameValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		String uniqueColumn = (String) component.getAttributes().get("uniqueColumn");
		String name = (String) value;

		if (name.length() == 1) {
			FacesMessage msg = new FacesMessage("Please enter full name", uniqueColumn);
			context.addMessage(component.getClientId(context), msg);
			throw new ValidatorException(msg);
		}

		else if (name.length() > 1) {
			if (!check(name)) {
				FacesMessage msg = new FacesMessage("Name cannot contain special characters or nubmerical values.",
						uniqueColumn);
				context.addMessage(component.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
		}

	}

	public boolean check(String name) {
		boolean isValid = true;
		;

		for (int i = 0; i < name.length(); i++) {
			if (!(Character.isDigit(name.charAt(i)) || Character.isWhitespace(name.charAt(i))
					|| Character.isAlphabetic(name.charAt(i))

			) || Character.isDigit(name.charAt(i)))

			{
				isValid = false;
			}

		}

		return isValid;

	}

}
