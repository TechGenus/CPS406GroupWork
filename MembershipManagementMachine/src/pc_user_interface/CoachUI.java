package pc_user_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

public class CoachUI extends JFrame{
	
	private static final int FHEIGHT = 600;
	private static final int FWIDTH = 800;
	private static ArrayList<User> list;

	private JPanel layout, sendMessages, nameInput;
	private JSplitPane split;
	private JButton send;
	private JLabel name;
	private JTextField text;
	private JTextArea messageBox, memberList;
	private JScrollPane scrollV, memberScroll;

	public CoachUI(ArrayList<User> users)
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
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setDividerLocation(2*FWIDTH / 3);
		split.setEnabled(false);
		
		//nameInput panel stuff
		name = new JLabel("Name");
		text = new JTextField(20);
		
		//sendMessages panel stuff
		messageBox = new JTextArea();
		messageBox.setLineWrap(true);
		messageBox.setWrapStyleWord(true);
		scrollV = new JScrollPane(messageBox);
		scrollV.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		send = new JButton("send");
		send.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//obtaining name and message to send
				String member = text.getText();
				String message = messageBox.getText();
				Iterator iterator = list.iterator();
				
				/*
				for (int i = 0; i < list.size(); i++)
				{
					
					if (((User)iterator.next()).getUserUsername() == member)
					{
						member.sendNotification(message);
					}
				}*/
				messageBox.setText(messageBox.getText() + "\n\nMessage Sent to " + member);
			}
		});
		
		//layout panel stuff
		memberList = new JTextArea();
		memberList.setFont(memberList.getFont().deriveFont(25f));
		memberList.setEnabled(false);
		memberScroll = new JScrollPane(memberList);
		memberScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Iterator iterator = list.iterator();
		
		for (int i = 0; i < list.size(); i++)
		{
			User temp = (User)iterator.next();
			memberList.append(temp.getUserFirstName() + " " + temp.getUserLastName() + "\n");
		}
		
		//adding panels
		nameInput.add(name);
		nameInput.add(text);
		sendMessages.add(nameInput, BorderLayout.NORTH);
		sendMessages.add(scrollV, BorderLayout.CENTER);
		sendMessages.add(send, BorderLayout.SOUTH);
		split.add(sendMessages);
		split.add(memberScroll);
				
		add(split);
		setVisible(true);
	}
}