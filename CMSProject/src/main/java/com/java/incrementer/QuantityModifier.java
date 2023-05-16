package com.java.incrementer;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class QuantityModifier implements Serializable {

	private int number = 1;

	public int getnumber() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		sessionMap.put("cartQuantity", number);
		return number;
	}

	public void setnumber(int number) {
		this.number = number;
	}

	public void increment() {

		if (number < 10) {
			number++;

		}

	}

	public void decrement() {
		if (number > 0) {
			number--;

		}
	}

}
