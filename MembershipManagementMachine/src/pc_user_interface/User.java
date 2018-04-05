package pc_user_interface;

import java.util.Iterator;

public class User implements java.io.Serializable {

	private int userNumber;           // The users Phone Number
	private String userAddress;       // The users address
	private String userFirstName;     // The users First name
	private String userLastName;      // The users Last Name
	private String username;              // user username
	private String password;              // user password
	private Boolean hasPaid;              // whether or not the user has paid this month
	private int missedPayments;           // The amount of payments the user missed
	private int successfulPayments;       // the amount of payments the user made
	private int attendedClasses;          // number of classes the user has attended
	private int salary;
	private String notifications;         // string that consists of all messages from coach
	private int userFlag;
	
	
	
	// Constructors
	public User (String firstName, String lastName, String address, int cn, String usrname, String pssword) 
	   {
	      // set instance variables
		  this.userFirstName = firstName;
	      this.userLastName = lastName;
	      this.userAddress = address;
		  this.userNumber = cn;
		  this.username = usrname;
		  this.password = pssword;
		  this.hasPaid = false;
		  this.missedPayments = 0;
		  this.successfulPayments = 0;  
		  this.attendedClasses = 0;
		  this.userFlag = 2;
		  
		  // print notifications on user GUI (inside a textbox on GUI )
	   }
	
	public User (String fname, String lname, String usrname, String pssword, int Salary)
	{
		this.userFirstName = fname;
		this.userLastName = lname;
		this.username = usrname;
		this.password = pssword;
		this.salary = Salary;
		this.userFlag = 1;
	}
	public User(String firstName, String lastName, String usrname, String pssword)
	{
		this.userFirstName = firstName;
		this.userLastName = lastName;
		this.username = usrname;
		this.password = pssword;
		this.userFlag = 0;
	}
	// user METHODS
	public void addMessage(String message)
	{
		this.notifications.concat(message + "\n");
	}
	
	// COACH METHODS
	/*
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
	
	private void sendNotification(String message)
	{
		Iterator Iterator = linkedList.iterator();
        while (Iterator.hasNext()) {
            System.out.println(Iterator.next());
            Iterator.next().addMessage(message);
        }
	}
	*/
	
	
	public int getUserNumber() {
		return userNumber;
	}
	public String getUserAddress(){
		return userAddress;
	}
	public String getUserFirstName(){
		return userFirstName;
	}
	public String getUserLastName(){
		return userLastName;
	}
	public Boolean getUserHasPaid(){
		return hasPaid;
	}
	public int getUserMissedPayments(){
		return missedPayments;
	}
	public int getUserSuccessfulPayments(){
		return successfulPayments;
	}
	public int getUserAttendedClasses(){
		return attendedClasses;
	}
	public String getUserUsername(){
		return username;
	}
	public String getUserPassword(){
		return password;
	}
	public int getSalary()
	{
		return this.salary;
	}
	
	
	public void setUserFirstName(String userFirstName){
		this.userFirstName = userFirstName;
	}
	public void setUserLastName(String userLastName){
		this.userLastName = userLastName;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
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
