package pc_user_interface;

public class PeopleMaker 
{
	public static void main(String args[])
	{
		User jill = new User("Jill","Stein","29 Lane",1,"jill","password123");
		User janak = new User("Janak","Bhanushali","janak","password456");
		User marco = new User("Marco","Puzzo","marco","janak",5);
		IOWork work = new IOWork();
		work.serialize(jill);
		work.serialize(janak);
		work.serialize(marco);
	}
}
