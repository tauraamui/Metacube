package co.uk.taurasystems.db.models;

public class Customer {

	private long ID = -1;
	private String firstName = "Unnamed";
	private String surname = "Surname";
	private String phoneNumber = "";
	
	public Customer() {}
	public Customer(long ID, String firstName, String surname, String phoneNumber) {
		setID(ID);
		setFirstName(firstName);
		setSurname(surname);
		setPhoneNumber(phoneNumber);
	}
	public Customer(String firstName, String surname, String phoneNumber) {
		setFirstName(firstName);
		setSurname(surname);
		setPhoneNumber(phoneNumber);
	}
	
	public long getID() {
		return ID;
	}
	
	public void setID(long ID) {
		this.ID = ID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
