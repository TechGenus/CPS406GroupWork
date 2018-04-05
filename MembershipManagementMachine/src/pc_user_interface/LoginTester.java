package jacob_files;
import java.io.*;
public class LoginTester {

	public static void main(String[] args) 
	{
		LoginInterface login = new LoginInterface();
		User jill = new User("Jill","Stein","123 This St.",1,"jill","password123");
		User bill = new User("Bill","Stein","123 This St.",2,"bill","password456");
	
		try
		{
			FileOutputStream fileout2 = new FileOutputStream("Users/jill.ser");
			ObjectOutputStream out2 = new ObjectOutputStream(fileout2);
			out2.writeObject(jill);
			out2.close();
			fileout2.close();
		}
		catch(IOException e)
		{
		}
	}
}
