import java.util.ArrayList;
import java.util.Iterator;

public class User implements java.io.Serializable {
	/**
	 * Declarations to create most of the Swing Objects
	 * */
	private String userNumber;           // The users Phone Number
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
	private Boolean discount;
	private Boolean penalty;
	
	
	/**
	 * Customer constructor below
	 * */
	public User (String firstName, String lastName, String address, String cn, String usrname, String pssword) 
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
		  this.notifications = "";
		  this.discount = false;
		  this.penalty = false;
		  
		  // print notifications on user GUI (inside a textbox on GUI )
	   }
	/**
	 * Coach constructor below
	 * */
	public User (String fname, String lname, String usrname, String pssword, int Salary)
	{
		this.userFirstName = fname;
		this.userLastName = lname;
		this.username = usrname;
		this.password = pssword;
		this.salary = Salary;
		this.userFlag = 1;
	}
	/**
	 * Treasurer constructor below
	 * */
	public User(String firstName, String lastName, String usrname, String pssword)
	{
		this.userFirstName = firstName;
		this.userLastName = lastName;
		this.username = usrname;
		this.password = pssword;
		this.userFlag = 0;
	}
	/**
	 * Method which adds a message to the user
	 * */
	public void addMessage(String message)
	{
		this.notifications += message + "\r\n";
	}
	/**
	 * Method which checks for payment
	 */
	public void pay()
	{
		if(this.hasPaid == true){
			System.out.println("You have already paid for this month");
		}	
		else{
			this.hasPaid = true;
		}
	}
	
	/**
	 * Method to send messages to all the users
	 * */
	public void sendMessage(String message, String usrname, ArrayList<User> users)
	{
        for (int counter = 0; counter < users.size(); counter++) {
            if(users.get(counter).getUserUsername() == usrname)
            {
            	users.get(counter).addMessage(message);
            	break;
            }
        }
	}
	/**
	 * Method to send a notification to all users
	 * */
	public void sendNotification(String message, ArrayList<User> users)
	{
        for (int counter = 0; counter < users.size(); counter++) { 	
        	if(users.get(counter).getUserFlag() == 2)
        	{
        		users.get(counter).addMessage(message);		
        	}
        }
	}
	/**
	 * Method to convert the User object to a string
	 * */
	public String toString()
	{
		String holder = getUserFirstName()+ " " + getUserLastName();
		return holder;
	}
	/**
	 * Methods to get all of the class variables
	 * */
	public Boolean getPenalty()
	{
		return penalty;
	}
	public Boolean getDiscount()
	{
		return discount;
	}
	public String getUserNumber() {
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
	public int getUserFlag()
	{
		return this.userFlag;
	}
	public String getNotification()
	{
		return this.notifications;
	}
	/**
	 * Methods to change all of the class variables
	 * */
	public void setPenalty(Boolean in)
	{
		this.penalty = in;
	}
	public void setDiscount(Boolean in)
	{
		this.discount = in;
	}
	public void setUserFirstName(String userFirstName){
		this.userFirstName = userFirstName;
	}
	public void setUserLastName(String userLastName){
		this.userLastName = userLastName;
	}
	public void setUserNumber(String userNumber) {
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
	public void setHasPaid(boolean state)
	{
		this.hasPaid = state;
	}
}
