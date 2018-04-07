import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class UserTest {

	@Before
	public void setUp() throws Exception {
		String message = "next practice is tuesday";
		String username = "janak";
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddMessage() {
		boolean result = false;
		User marcky = new User("mar", "som", "toronto", "1", "mar", "78");
		marcky.addMessage("next practice is tuesday");
		if (marcky.getNotification().equals("next practice is tuesday\r\n")){
			result = true;
		}
		assertTrue(result);
	}

	@Test
	public void testPay() {
		boolean result = false;
		User marcky2 = new User("mar", "som", "toronto", "1", "mar", "78");
		marcky2.pay();
		if(marcky2.getUserHasPaid() == true){
			result = true;
		}
		assertTrue(result);
	}

	@Test
	public void testSendMessage() {
		boolean result = false;
		User marcky3 = new User("mar", "som", "toronto", "1", "mari", "78");
		ArrayList<User> list2 = new ArrayList<User>();
		list2.add(marcky3);
		User marckyCoach = new User("Marco","Puzzo","marco","janak",5);
		marckyCoach.sendMessage("next practice is tuesday", "mari", list2);
		if (marcky3.getNotification().equals("next practice is tuesday\r\n")){
			result = true;
		}
		assertTrue(result);
	}

	@Test
	public void testSendNotification() {
		boolean result = false;
		User marcky4 = new User("mar", "som", "toronto", "1", "maru", "78");
		User marcky5 = new User("mar", "som", "toronto", "1", "mary", "78");
		User marckyCoach2 = new User("Marco","Puzzo","oarco","janak",5);
		ArrayList<User> list3 = new ArrayList<User>();
		list3.add(marcky4);
		list3.add(marcky5);
		marckyCoach2.sendNotification("next practice is tuesday", list3);
		if (marcky4.getNotification().equals("next practice is tuesday\r\n") && marcky5.getNotification().equals("next practice is tuesday\r\n")){
			result = true;
		}
		assertTrue(result);
	}

}
