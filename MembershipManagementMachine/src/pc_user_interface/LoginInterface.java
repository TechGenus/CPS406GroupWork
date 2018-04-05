package pc_user_interface;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
public class LoginInterface 
{
	JTextField textField;
	JPasswordField textField2;
	IOWork decryptor;
	public LoginInterface()
	{
		JFrame screen = new JFrame();
		JPanel page = new JPanel();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setTitle("Login");
		screen.setSize(260, 120);
		screen.setResizable(false);
		screen.setLocationRelativeTo(null);
		page.setVisible(true);
		screen.add(page);
		partsAdding(page,screen);
	}
	public void partsAdding(JPanel page, JFrame screen)
	{
		String[] labels = {"Username:","Password:"};
		
		JLabel l = new JLabel(labels[0],JLabel.TRAILING);
		page.add(l);
		textField = new JTextField(14);
		l.setLabelFor(textField);
		page.add(textField);
		
		JLabel l2 = new JLabel(labels[1],JLabel.TRAILING);
		page.add(l2);
		textField2 = new JPasswordField(14);
		l2.setLabelFor(textField2);
		page.add(textField2);
		
		JButton login = new JButton("Log-in");
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				User c = null;
				ArrayList<User> userList = new ArrayList<User>();
				decryptor = new IOWork();
				File folder = new File("Users");
				File[] temp = folder.listFiles();
				for(int i = 0;i < temp.length;i++)
				{
					userList.add((User)decryptor.deserialize(temp[i].getAbsolutePath()));
				}
				String username = textField.getText();
				String password = new String(textField2.getPassword());
				c = (User)decryptor.deserialize("Users/" + username + ".ser");
				if(c != null)
				{
					if(c.getUserFlag() == 0)
					{
						TreasurerUI treasurerUI = new TreasurerUI("Membership Management Machine", "Jedi Master Luigibird");
					}
					if(c.getUserFlag() == 1)
					{
						System.out.println(userList.size());
						System.out.println(userList.get(1));
						CoachUI coachUI = new CoachUI(userList);
					}
					if(c.getUserFlag() == 2)
					{
						CustomerUI customerUI = new CustomerUI();
					}
				}
			}
		});
		page.add(login);
		screen.setVisible(true);
	}
}
