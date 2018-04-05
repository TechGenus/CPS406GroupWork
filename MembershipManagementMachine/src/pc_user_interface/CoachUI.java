package pc_user_interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class CoachUI extends JFrame{
	
	private static final int FHEIGHT = 600;
	private static final int FWIDTH = 800;
	private static ArrayList<User> list;

	private JPanel layout, sendMessages, nameInput;
	private JButton send;
	private JLabel name;
	private JTextField text;
	private JTextArea messageBox, memberList;

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
				Iterator iterator = list.iterator();
				
				/*
				for (int i = 0; i < list.size(); i++)
				{
					
					if (((User)iterator.next()).getUserUsername() == member)
					{
						member.sendNotification(message);
					}
				}*/
				
			}
		});
		
		//layout panel stuff
		memberList = new JTextArea();
		memberList.setEnabled(false);
		Iterator iterator = list.iterator();
		
		for (int i = 0; i < list.size(); i++)
		{
			User temp = (User)iterator.next();
			String name = temp.getUserFirstName();
			System.out.println(name);
			memberList.append(name + "\n");
		}
		
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