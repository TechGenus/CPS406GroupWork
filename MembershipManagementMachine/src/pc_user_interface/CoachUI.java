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
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CoachUI extends JFrame{
	
	private static final int FHEIGHT = 600;
	private static final int FWIDTH = 800;
	private static ArrayList<User> list;
	private static Object[] list2;

	private JPanel layout, sendMessages, nameInput;
	private JSplitPane split;
	private JButton send;
	private JLabel name;
	private JTextArea messageBox; 
	private JList memberList;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new JPanel(new GridLayout(1,2));
		sendMessages = new JPanel(new BorderLayout());
		nameInput = new JPanel(new FlowLayout());
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setDividerLocation(2*FWIDTH / 3);
		split.setEnabled(false);
		
		//nameInput panel stuff
		name = new JLabel("Name");

		
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
		
				messageBox.setText(messageBox.getText() + "\n\nMessage Sent to ");
			}
		});
		
		class listListener implements ListSelectionListener
		{
			public void valueChanged(ListSelectionEvent e)
			{
				if(e.getValueIsAdjusting() == false)
				{
					User hold = (User)e.getSource();
					String name = hold.toString();
				}
			}
		}
		
		//layout panel stuff
		list2 = list.toArray();
		listListener userList = new listListener();
		memberList = new JList(list2);
		memberList.setFont(memberList.getFont().deriveFont(12f));
		memberList.setEnabled(true);
		memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		memberList.addListSelectionListener(userList);
		memberScroll = new JScrollPane(memberList);
		memberScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//adding panels
		nameInput.add(name);
		sendMessages.add(nameInput, BorderLayout.NORTH);
		sendMessages.add(scrollV, BorderLayout.CENTER);
		sendMessages.add(send, BorderLayout.SOUTH);
		split.add(sendMessages);
		split.add(memberScroll);
				
		add(split);
		setVisible(true);
	}
}