package pc_user_interface;

import java.io.File;
import java.util.*;

public class Main {
	
	public static void main(String[] args) 
	{
		User jill = new User("Jill","Stein","29 Lane",1,"jill","password123");
		User janak = new User("Janak","Janakkumar","janak","password456");
		User marco = new User("Marco","Puzzo","marco","janak",5);
		IOWork work = new IOWork();
		work.serialize(jill);
		work.serialize(janak);
		work.serialize(marco);
		LoginInterface login = new LoginInterface();
	}
}
