package com.java.validations;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.java.validations.PasswordValidation")
@ViewScoped
public class PasswordValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		String uniqueColumn = (String) comp.getAttributes().get("uniqueColumn");

		String pass = (String) value;
		boolean noUpper = true;
		boolean noDigit = true;
		boolean noSpecial = true;

		if (pass.length() < 6) {
			FacesMessage msg = new FacesMessage("Length must be 6 or more characters", uniqueColumn);
			context.addMessage(comp.getClientId(context), msg);
			throw new ValidatorException(msg);
		}

		else if (pass.length() >= 6) {
			
			for(int i = 0; i<pass.length(); i++)
			{
				if(Character.isUpperCase(pass.charAt(i)))
						{
							noUpper = false;
						}
				else if(Character.isDigit(pass.charAt(i)))
				{
					noDigit = false;
				}
				
				else if(!Character.isDigit(pass.charAt(i)) && !Character.isLetter(pass.charAt(i))
						&& !Character.isWhitespace(pass.charAt(i)))
				{
					noSpecial = false;
				}
			}
			
			if(noUpper)
			{
				if(noDigit == false && noSpecial == false)
				{
					FacesMessage msg = new FacesMessage("Please enter uppercase character", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noDigit == true && noSpecial == false)
				{
					FacesMessage msg = new FacesMessage("Please enter uppercase character and numbers", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noDigit == false && noSpecial == true)
				{
					FacesMessage msg = new FacesMessage("Please enter uppercase character and special characters", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noDigit == true && noSpecial == true)
				{
					FacesMessage msg = new FacesMessage("Must contian special characters, number and uppcase letter", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
			}
			
			else if(noDigit)
			{
				if(noUpper == false && noSpecial == false)
				{
					FacesMessage msg = new FacesMessage("Please enter numbers to password", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noUpper == true && noSpecial == false)
				{
					FacesMessage msg = new FacesMessage("Please enter numbers and uppercase character to password", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noUpper == false && noSpecial == true)
				{
					FacesMessage msg = new FacesMessage("Please enter numbers and special character to password", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noUpper == true && noSpecial == true)
				{
					FacesMessage msg = new FacesMessage("Must contian special characters, number and uppcase letter", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
			}
			
			else if(noSpecial)
			{
				if(noUpper == false && noDigit == false)
				{
					FacesMessage msg = new FacesMessage("Please enter special characters to password", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noUpper == true && noDigit == false)
				{
					FacesMessage msg = new FacesMessage("Please enter special characters and uppercase characters to password", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noUpper == false && noDigit == true)
				{
					FacesMessage msg = new FacesMessage("Please enter numbers and special character to password", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
				
				else if(noDigit == true && noUpper == true)
				{
					FacesMessage msg = new FacesMessage("Must contian special characters, number and uppcase letter", uniqueColumn);
					context.addMessage(comp.getClientId(context), msg);
					throw new ValidatorException(msg);
				}
			}
			
			
		}
	}

}
