package com.java.cms;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
@ManagedBean
@ViewScoped
public class Customer {

	/*CUS_ID int(10) NOT NULL AUTO_INCREMENT,
	  CUS_NAME varchar(50) NOT NULL,
	  CUS_PHN_NO varchar(50) NOT NULL,
	  CUS_USERNAME varchar(50) NOT NULL,
	  CUS_PASSWORD varchar(50) NOT NULL,
	  CUS_EMAIL varchar(50) NOT NULL,
	  PRIMARY KEY (CUS_ID),
	  UNIQUE KEY CUS_PHN_NO (CUS_PHN_NO),
	  UNIQUE KEY CUS_USERNAME (CUS_USERNAME)*/
	
	@Id
	@Column(name="CUS_ID")
	private int cusId;
	@Column(name="CUS_NAME")
	private String cusName;
	@Column(name="CUS_PHN_NO")
	private String cusPhone;
	@Column(name="CUS_USERNAME")
	private String cusUsername;
	@Column(name="CUS_PASSWORD")
	private String cusPassword;
	@Column(name="CUS_EMAIL")
	private String cusEmail;
	
	
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public String getCusUsername() {
		return cusUsername;
	}
	public void setCusUsername(String cusUsername) {
		this.cusUsername = cusUsername;
	}
	public String getCusPassword() {
		return cusPassword;
	}
	public void setCusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", cusName=" + cusName + ", cusPhone=" + cusPhone + ", cusUsername="
				+ cusUsername + ", cusPassword=" + cusPassword + ", cusEmail=" + cusEmail + "]";
	}
	
	
	
	
	
	
	
}
