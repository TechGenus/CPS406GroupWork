package pc_user_interface;

import java.io.File;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		LinkedList Users = new LinkedList();
		IOWork decryptor = new IOWork();
		File folder = new File("users/");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        Users.add(decryptor.deserialize(file.getName()));
		    }
		}
		
		TreasurerUI treasurerUI = new TreasurerUI("Membership Management Machine", "Jedi Master Luigibird");
		CustomerUI customerUI = new CustomerUI();
		CoachUI coachUI = new CoachUI(Users);
	}
}
