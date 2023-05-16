package com.java.validations;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@RequestScoped
@FacesValidator("com.java.validations.PhoneNumberValidation")
public class PhoneNumberValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		String uniqueColumn = (String) comp.getAttributes().get("uniqueColumn");
		String number = (String) value;
		
		boolean hasLetter = false;
		boolean hasSpaces = false;
		boolean hasSpecial = false;
		
		for(int i = 0;i<number.length(); i++)
		{
			
			if(Character.isWhitespace(number.charAt(i)))
			{
				hasSpaces = true;
			}
			
			else if(Character.isLetter(number.charAt(i)))
			{
				hasLetter = true;
			}
			
			else if(!Character.isWhitespace(number.charAt(i)) && 
					!Character.isLetter(number.charAt(i)) &&
					!Character.isDigit(number.charAt(i)))
			{
				hasSpecial = true;
			}
		}
		
		if(hasLetter == false && hasSpaces == false && hasSpecial == false)
		{
			if(number.length()<10)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot be less than 10 digits", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(number.length() > 10)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot be greater than 10 digits", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
		}
		
		else if(hasLetter)
		{
			if(hasSpaces == false && hasSpecial == false)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain letters", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasSpaces == true && hasSpecial == false)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain letters and white spaces", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasSpaces == false && hasSpecial == true)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain letters and special characters", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasSpaces == true && hasSpecial == true)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain letters, special characters and white spaces", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
		}
		
		else if(hasSpaces)
		{
			if(hasLetter == false && hasSpecial == false)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain white spaces", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasLetter == true && hasSpecial == false)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain white spaces and letters", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasLetter == false && hasSpecial == true)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain special characters and white spaces", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasSpaces == true && hasSpecial == true)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain letters, special characters and white spaces", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
		}
		
		else if(hasSpecial)
		{
			if(hasSpaces == false && hasLetter == false)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain special characters?", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasSpaces == true && hasLetter == false)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain special characters and white spaces", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasSpaces == false && hasLetter == true)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain special characters and letters", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
			
			else if(hasSpaces == true && hasLetter == true)
			{
				FacesMessage msg = new FacesMessage("Phone number cannot contain special characters, white spaces and letters", uniqueColumn);
				context.addMessage(comp.getClientId(context), msg);
				throw new ValidatorException(msg);
			}
		}
		
	}

}
