package com.java.incrementer;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "cal")
@ViewScoped
public class Cal {

	public double calculate(double d, int qty) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		if (qty == 0) {

			sessionMap.put("cartTotal", d);
			return d;
		}

		else {

			sessionMap.put("cartTotal", d * qty);
			return d * qty;
		}
	}

}
