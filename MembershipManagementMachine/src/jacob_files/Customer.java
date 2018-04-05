package users;

import java.util.Iterator;

public class Customer implements java.io.Serializable {

	private int customerNumber;           // The Customers Phone Number
	private String customerAddress;       // The customers address
	private String customerFirstName;     // The customers First name
	private String customerLastName;      // The customers Last Name
	private String username;              // customer username
	private String password;              // customer password
	private Boolean hasPaid;              // whether or not the customer has paid this month
	private int missedPayments;           // The amount of payments the customer missed
	private int successfulPayments;       // the amount of payments the customer made
	private int attendedClasses;          // number of classes the customer has attended
	private int    salary;
	
	private String notifications;         // string that consists of all messages from coach
	
	
	
	
	// Constructors
	public Customer (String firstName, String lastName, String address, int cn, String usrname, String pssword) 
	   {
	      // set instance variables
		  this.customerFirstName = firstName;
	      this.customerLastName = lastName;
	      this.customerAddress = address;
		  this.customerNumber = cn;
		  this.username = usrname;
		  this.password = pssword;
		  this.hasPaid = false;
		  this.missedPayments = 0;
		  this.successfulPayments = 0;  
		  this.attendedClasses = 0;
		  
		  // print notifications on customer GUI (inside a textbox on GUI )
	   }
	
	public Customer(String fname, String lname, String usrname, String pssword, int Salary)
	{
		this.customerFirstName = fname;
		this.customerLastName = lname;
		this.username = usrname;
		this.password = pssword;
		this.salary = Salary;
	}
	
	
	// CUSTOMER METHODS
	public void addMessage(String message)
	{
		this.notifications.concat(message + "\n");
	}
	
	private void sendNotification(String message)
	{
		Iterator Iterator = linkedList.iterator();
        while (Iterator.hasNext()) {
            System.out.println(Iterator.next());
            Iterator.next().addMessage(message);
        }
	}
	
	// COACH METHODS
	private void sendMessage(String message, String usrname)
	{
		Iterator Iterator2 = linkedList.iterator();
        while (Iterator2.hasNext()) {
            System.out.println(Iterator2.next());
            if(Iterator2.next().username == usrname)
            {
            	Iterator2.next().addMessage(message);
            	break;
            }
        }
	}
	
	
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	public String getCustomerAddress(){
		return customerAddress;
	}
	public String getCustommerFirstName(){
		return customerFirstName;
	}
	public String getCustomerLastName(){
		return customerLastName;
	}
	public Boolean getCustomerHasPaid(){
		return hasPaid;
	}
	public int getCustomerMissedPayments(){
		return missedPayments;
	}
	public int getCustomerSuccessfulPayments(){
		return successfulPayments;
	}
	public int getCustomerAttendedClasses(){
		return attendedClasses;
	}
	public String getCustomerUSername(){
		return username;
	}
	public String getCustomerPassword(){
		return password;
	}
	public int getSalary()
	{
		return this.salary;
	}
	
	
	public void setCustomerFirstName(String CustomerFirstName){
		this.customerFirstName = CustomerFirstName;
	}
	public void setCustomerLastName(String CustomerLastName){
		this.customerLastName = CustomerLastName;
	}
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	public void setCustomerAddress(String CustomerAddress){
		this.customerAddress = CustomerAddress;
	}
	public void setMissedPayments(int missedPayments){
		this.missedPayments = missedPayments;
	}
	public void setSuccessfulPayments(int successfulPayments){
		this.successfulPayments = successfulPayments;
	}
	public void setAttendedClasses(int classesAttended){
		this.attendedClasses = classesAttended;
	}
	public void setSalary(int Salary)
	{
		this.salary = Salary;
	}
}
