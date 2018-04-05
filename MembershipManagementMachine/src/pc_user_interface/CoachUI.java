package pc_user_interface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CoachUI extends JFrame{
	
	private static final int FHEIGHT = 800;
	private static final int FWIDTH = 600;

	JPanel layout, sendMessages, nameInput;
	JButton send;
	JLabel name;
	JTextField text;
	JTextArea area, memberList;
	
	ActionListener listener;
	
	public CoachUI()
	{
		constructUI();
	} 
	
	private void constructUI()
	{
		setSize(FWIDTH, FHEIGHT);
		
		layout = new JPanel(new BorderLayout());
		sendMessages = new JPanel(new BorderLayout());
		nameInput = new JPanel(new FlowLayout());
		
		send = new JButton("send");
		send.addActionListener(listener);
		
		name = new JLabel("Names");
		text = new JTextField();
		
		area = new JTextArea();
		memberList = new JTextArea();
		
		
		nameInput.add(name);
		nameInput.add(text);
		
		sendMessages.add(nameInput, BorderLayout.NORTH);
		sendMessages.add(area, BorderLayout.CENTER);
		sendMessages.add(send, BorderLayout.SOUTH);
		
		layout.add(sendMessages, BorderLayout.WEST);
		layout.add(memberList, BorderLayout.EAST);
		
		add(layout);
		
		setVisible(true);
	}
	
	class listener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
		}
	}
}