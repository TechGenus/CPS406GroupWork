package pc_user_interface;
import javax.swing.*;
import java.awt.event.*;
public class LoginInterface 
{
	JTextField textField;
	JPasswordField textField2;
	LoginWork work;
	public LoginInterface()
	{
		JFrame screen = new JFrame();
		JPanel page = new JPanel();
		work = new LoginWork();
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
				String username = textField.getText();
				String password = new String(textField2.getPassword());
				c = work.decrypt("Users/" + username + ".ser");
				if(c != null)
				{
					System.out.println(c.getUserPassword());
				}
			}
		});
		page.add(login);
		screen.setVisible(true);
	}
}
