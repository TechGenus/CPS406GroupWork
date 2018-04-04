import javax.swing.*;
import java.awt.event.*;
public class LoginInterface 
{
	JTextField textField;
	JPasswordField textField2;
	public LoginInterface()
	{
		JFrame screen = new JFrame();
		JPanel page = new JPanel();
		LoginWork work = new LoginWork();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setTitle("Login");
		screen.setSize(260, 130);
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
				String username = textField.getText();
				String password = new String(textField2.getPassword());
				
			}
		});
		page.add(login);
		screen.setVisible(true);
	}
}
