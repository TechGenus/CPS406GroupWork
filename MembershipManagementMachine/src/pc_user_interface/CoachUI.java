package pc_user_interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.*;

public class CoachUI extends JFrame{
	
	private static final int FHEIGHT = 600;
	private static final int FWIDTH = 800;
	private static LinkedList list;

	private JPanel layout, sendMessages, nameInput;
	private JButton send;
	private JLabel name;
	private JTextField text;
	private JTextArea messageBox, memberList;

	public CoachUI(LinkedList users)
	{
		list = users;
		constructUI();
	} 
	
	private void constructUI()
	{
		setSize(FWIDTH, FHEIGHT);
		
		//panels
		layout = new JPanel(new GridLayout(1,2));
		sendMessages = new JPanel(new BorderLayout());
		nameInput = new JPanel(new FlowLayout());
		
		//nameInput panel stuff
		name = new JLabel("Name");
		text = new JTextField(30);
		
		//sendMessages panel stuff
		messageBox = new JTextArea();
		send = new JButton("send");
		send.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//obtaining name and message to send
				String member = text.getText();
				String message = messageBox.getText();
			}
		});
		
		//layout panel stuff
		memberList = new JTextArea();
		memberList.setEnabled(false);
		Iterator iterator = list.iterator();
		/*while (iterator.hasNext())
		{
			memberList.add();
			 
		}*/
		
		//adding panels
		nameInput.add(name);
		nameInput.add(text);
		sendMessages.add(nameInput, BorderLayout.NORTH);
		sendMessages.add(messageBox, BorderLayout.CENTER);
		sendMessages.add(send, BorderLayout.SOUTH);
		layout.add(sendMessages);
		layout.add(memberList);
				
		add(layout);
		setVisible(true);
	}
}