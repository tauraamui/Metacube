package co.uk.taurasystems.db.models;

public class Customer extends Model {

	private long ID = -1;
	private String firstName = "First name";
	private String surname = "Surname";
	private String phoneNumber = "";
	private String addressFirstLine = "";
	
	public Customer() {}
	public Customer(long ID, String firstName, String surname, String phoneNumber, String addressFirstLine) {
		setID(ID);
		setFirstName(firstName);
		setSurname(surname);
		setPhoneNumber(phoneNumber);
		setAddressFirstLine(addressFirstLine);
	}
	public Customer(String firstName, String surname, String phoneNumber, String addressFirstLine) {
		setFirstName(firstName);
		setSurname(surname);
		setPhoneNumber(phoneNumber);
		setAddressFirstLine(addressFirstLine);
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
	public String getAddressFirstLine() {
		return addressFirstLine;
	}
	public void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
	}
}
