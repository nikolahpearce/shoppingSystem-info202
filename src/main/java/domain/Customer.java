/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author peani371
 */
public class Customer {
    
    private String personID;
    private String username;
    private String firstName;
    private String surname;
    private String password;
    private String emailAddress;
    private String shippingDetails;

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setShippingDetails(String shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	public void setCreditCardDetails(String creditCardDetails) {
		this.creditCardDetails = creditCardDetails;
	}

	public String getPersonID() {
		return personID;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getShippingDetails() {
		return shippingDetails;
	}

	public String getCreditCardDetails() {
		return creditCardDetails;
	}

	@Override
	public String toString() {
		return "Customer{" + "personID=" + personID + ", firstName=" + firstName + ", surname=" + surname + '}';
	}
    private String creditCardDetails;
}