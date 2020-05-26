package com.project.pages;

public class DataTestDTO {
	String email;
	String Pass;
	String First_Name;
	String Last_name;
	String DAY_OF_BIRTH;
	String MONTH_OF_BIRTH;
	String YEAR_OF_BIRTH;
	String ADDRESS;
	String City;
	String State;
	String ZIPCODE;
	String MOBILEPHONE;
	String ALIAS;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_name() {
		return Last_name;
	}
	public void setLast_name(String last_name) {
		Last_name = last_name;
	}
	public String getDAY_OF_BIRTH() {
		return DAY_OF_BIRTH;
	}
	public void setDAY_OF_BIRTH(String dAY_OF_BIRTH) {
		DAY_OF_BIRTH = dAY_OF_BIRTH;
	}
	public String getMONTH_OF_BIRTH() {
		return MONTH_OF_BIRTH;
	}
	public void setMONTH_OF_BIRTH(String mONTH_OF_BIRTH) {
		MONTH_OF_BIRTH = mONTH_OF_BIRTH;
	}
	public String getYEAR_OF_BIRTH() {
		return YEAR_OF_BIRTH;
	}
	public void setYEAR_OF_BIRTH(String yEAR_OF_BIRTH) {
		YEAR_OF_BIRTH = yEAR_OF_BIRTH;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZIPCODE() {
		return ZIPCODE;
	}
	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}
	public String getMOBILEPHONE() {
		return MOBILEPHONE;
	}
	public void setMOBILEPHONE(String mOBILEPHONE) {
		MOBILEPHONE = mOBILEPHONE;
	}
	public String getALIAS() {
		return ALIAS;
	}
	public void setALIAS(String aLIAS) {
		ALIAS = aLIAS;
	}
	
	@Override
	public String toString() {
		return "DataTestDTO [email=" + email + ", Pass=" + Pass + ", First_Name=" + First_Name + ", Last_name="
				+ Last_name + ", DAY_OF_BIRTH=" + DAY_OF_BIRTH + ", MONTH_OF_BIRTH=" + MONTH_OF_BIRTH
				+ ", YEAR_OF_BIRTH=" + YEAR_OF_BIRTH + ", ADDRESS=" + ADDRESS + ", City=" + City + ", State=" + State
				+ ", ZIPCODE=" + ZIPCODE + ", MOBILEPHONE=" + MOBILEPHONE + ", ALIAS=" + ALIAS + "]";
	}

}
