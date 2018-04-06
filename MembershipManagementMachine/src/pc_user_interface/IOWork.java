package pc_user_interface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IOWork 
{
	public Object deserialize(String fileName)
	{
		Object c = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (User) in.readObject();
			in.close();
			fileIn.close();
		}
		catch(IOException e) {}
		catch(ClassNotFoundException e) {}
		return c;
	}
	public void serialize(User o)
	{
		try
		{
			FileOutputStream fileout = new FileOutputStream("Users/"+ o.getUserUsername() + ".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(o);
			out.close();
			fileout.close();
		}
		catch(IOException i)
		{
		
		}
	}
	public ArrayList<User> userGather()
	{
		ArrayList<User> results = new ArrayList<User>();
		File folder = new File("Users");
		File[] temp = folder.listFiles();
		for(int i = 0;i < temp.length;i++)
		{
			results.add((User)deserialize(temp[i].getAbsolutePath()));
		}
		return results;
	}
}
