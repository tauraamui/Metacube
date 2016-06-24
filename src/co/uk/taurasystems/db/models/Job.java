package co.uk.taurasystems.db.models;

public class Job {

	public enum Priority {
		LOW,
		MEDIUM,
		HIGH,
		CRITICAL;
	}
	
	private long ID;
	private long customerID;
	private String date;
	private String time;
	private Priority priority;
	
	private String deviceName;
	private boolean bagOrCase;
	private String description;
	private float price;
	
	public Job() {}
	public Job(String date, String time, Priority priority) {
		this.date = date;
		this.time = time;
		this.priority = priority;
	}
	
	public long getID() {
		return ID;
	}
	
	public void setID(long ID) {
		this.ID = ID;
	}
	
	public long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public boolean isBagOrCase() {
		return bagOrCase;
	}
	public void setBagOrCase(boolean bagOrCase) {
		this.bagOrCase = bagOrCase;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}
