package jacob_files;
import java.io.*;
public class LoginWork 
{
	public boolean loginCheck(String username, String password)
	{
		boolean result = false;
		return result;
	}
	public Customer decrypt(String fileName)
	{
		Customer c = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (Customer) in.readObject();
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
