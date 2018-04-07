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
	/**
	 * Method to take a serialized User file and turn it into an object
	 * */
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
	/**
	 * Method to take a User object and turn it into a serialized file
	 * */
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
	/**
	 * Method which gathers all of the users from the User folder and puts them into an ArrayList
	 * */
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
	/**
	 * Method which sorts missed payments by greatest to least
	 * */
	public ArrayList<User> paymentsSort(ArrayList<User> holder)
	{
		ArrayList<User> results = holder;
		for(int i = 0;i < results.size();i++)
		{
			if(results.get(i).getUserFlag() != 2)
			{
				results.remove(i);
			}
		}
		for(int i = 0;i < results.size();i++)
		{
			for(int f = 0;f < results.size();f++)
			{
				if(results.get(i).getUserMissedPayments() < results.get(f).getUserMissedPayments())
				{
					User temp = results.get(i);
					results.set(i, results.get(f));
					results.set(f, temp);
				}
			}
		}
		return results;
	}
	/**
	 * Method which sorts attended classes by greatest to least
	 * */
	public ArrayList<User> attendedSort(ArrayList<User> holder)
	{
		ArrayList<User> results = holder;
		for(int i = 0;i < results.size();i++)
		{
			if(results.get(i).getUserFlag() != 2)
			{
				results.remove(i);
			}
		}
		for(int i = 0;i < results.size();i++)
		{
			for(int f = 0;f < results.size();f++)
			{
				if(results.get(i).getUserAttendedClasses() < results.get(f).getUserAttendedClasses())
				{
					User temp = results.get(i);
					results.set(i, results.get(f));
					results.set(f, temp);
				}
			}
		}
		return results;
	}
}
