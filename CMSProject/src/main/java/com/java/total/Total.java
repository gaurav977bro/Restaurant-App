package com.java.total;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Total {

	private double newTotal;

	public double getNewTotal() {
		return newTotal;
	}

	public void setNewTotal(double newTotal) {
		this.newTotal = newTotal;
	}

	public double increment() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		double currentTotal = (double) sessionMap.get("cartTotal");
		return newTotal + currentTotal;
	}

	public double decrement(int previousTotal) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		return newTotal - previousTotal;
	}

}
