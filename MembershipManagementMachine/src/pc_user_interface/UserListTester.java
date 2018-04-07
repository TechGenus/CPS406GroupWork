package pc_user_interface;

import java.util.ArrayList;


public class UserListTester {

	public static void main(String[] args) 
	{
		IOWork work = new IOWork();
		ArrayList<User> holder = work.userGather();
		for(int i = 0;i < holder.size();i++)
		{
			User temp = holder.get(i);
			System.out.println(temp.toString());
		}
	}

}
