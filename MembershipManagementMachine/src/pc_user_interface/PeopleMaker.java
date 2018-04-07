package pc_user_interface;
import java.util.Random;

public class PeopleMaker 
{
	/**
	 * Main method which allows for new user files to be created
	 * */
	public static void main(String args[])
	{
		Random r = new Random();
		User jill = new User("Jill","Stein","29 Lane","4169999999","jill","password123");
		jill.setMissedPayments(r.nextInt(5) + 1);
        jill.setAttendedClasses(r.nextInt(5) + 1);
		User will = new User("will","Stein","29 Lane","4169999999","will","password123");
		will.setMissedPayments(r.nextInt(5) + 1);
        will.setAttendedClasses(r.nextInt(5) + 1);
		User bill = new User("bill","Stein","29 Lane","4169999999","bill","password123");
		bill.setMissedPayments(r.nextInt(5) + 1);
        bill.setAttendedClasses(r.nextInt(5) + 1);
		User nill = new User("nill","Stein","29 Lane","4169999999","nill","password123");
		nill.setMissedPayments(r.nextInt(5) + 1);
        nill.setAttendedClasses(r.nextInt(5) + 1);
		User janak = new User("Janak","Bhanushali","janak","password456");
		User marco = new User("Marco","Puzzo","marco","janak",5);
		IOWork work = new IOWork();
		work.serialize(jill);
		work.serialize(janak);
		work.serialize(marco);
		work.serialize(will);
		work.serialize(bill);
		work.serialize(nill);
	}
}
