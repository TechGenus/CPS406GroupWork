package pc_user_interface;
import java.io.*;
public class LoginWork 
{
	public boolean loginCheck(String username, String password)
	{
		boolean result = false;
		return result;
	}
	public User decrypt(String fileName)
	{
		User c = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (User) in.readObject();
			in.close();
			fileIn.close();
		}
		catch(IOException e)
		{
			
		}
		catch(ClassNotFoundException e)
		{
			
		}
		return c;
	}
}
