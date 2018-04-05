package pc_user_interface;

import java.util.*;
import javax.swing.*;
import java.io.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TreasurerUI extends JFrame{
	private int				frameWidth 	= 800;
	private int				frameHeight = 600;
	
	private String			userName;
	
	private ActionListener 	listener;
	
	private JMenuBar 		accountsMenuBar;
	private JMenu			fileMenu;
	private JMenuItem		newIncomeStatement, open, saveAs, save, exit;
	private JMenu			helpMenu;
	private JMenuItem		about, documentation;
	
	private JTabbedPane		tabbedPane;
	
	private JSplitPane		incomeAndExpensesTab;
	private JPanel			incomeAndExpensesOptionsPanel;
	private JScrollPane		incomeStatementScrollPane;
	private JTextArea		incomeStatementTextArea;
	private JPanel 			incomeStatementInputPanel, incomeStatementOptionsPanel;
	private JLabel 			lblIncomeStatement, lblRevenue, lblExpenses, lblAccountsPayable, lblTax;
	private JTextField 		textField_revenue, textField_expenses, textField_accountsPayable, textField_tax;
	private JButton 		btnOutputIncomeStatement, btnSave;
	
	private JSplitPane		accountsTab;
	private JPanel			accountsChatSplitPanePanel;
	private JSplitPane		accountsChatSplitPane;
	private JPanel			customerSortingPanel;
	private JList 			customerList;
	private JScrollPane 	customerScrollPane;
	private JPanel			customerScrollPanePanel;
	private JPanel 			inputChatTextAreaPanel;
	private JTextArea 		inputChatTextArea;
	private JScrollPane 	inputChatScrollPane;
	private JPanel 			btnSendPanel;
	private JButton 		btnSend;
	private JSplitPane 		inputChatSplitPane;
	private JPanel 			previousChatPanel;
	private JTextArea 		previousTextArea;
	private JLabel 			lblInChatWith;
	
	public TreasurerUI(String frameTitle, String userName) {
		setTitle(frameTitle);
		this.userName = userName;
		setVisible(true);
		this.fillFrame();
		setSize(frameWidth, frameHeight);
		setMinimumSize(new Dimension(frameWidth, frameHeight));
		//frame.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void fillFrame() {
		setJMenuBar(accountsMenuBar());
		getContentPane().add(introPanel(),	BorderLayout.NORTH);
		getContentPane().add(tabbedPane(),	BorderLayout.CENTER);
	}
	
	private JTabbedPane tabbedPane() {
		tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Accounts", null, accountsTab(),	null);
		
		tabbedPane.addTab("Income and Expenses",	null, incomeAndExpensesTab(),	null);
		
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		// this line allows u to change the tab index to focus on what ever tab u want
		tabbedPane.setSelectedIndex(0);
		
		return tabbedPane;
	}
	
	private JSplitPane accountsTab() {
		accountsTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		accountsTab.setDividerLocation(frameWidth / 3);
		accountsTab.setEnabled(false);
		
		customerList = 				new JList<JLabel>();
		customerScrollPane = 		new JScrollPane(customerList);
		customerSortingPanel = 		new JPanel();
		customerScrollPanePanel =	new JPanel(new BorderLayout());
		
		customerList.add(new JLabel("Hello"));
		
		customerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		customerScrollPane.setColumnHeaderView(customerSortingPanel);
		
		customerScrollPanePanel.add(customerScrollPane, BorderLayout.CENTER);
		
		accountsTab.add(customerScrollPanePanel);
		accountsTab.add(accountsChatSplitPanePanel());
		
		return accountsTab;
	}
	
	private JSplitPane accountsChatSplitPanePanel() {
		accountsChatSplitPane = 		new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		accountsChatSplitPane.setDividerLocation(2 * frameHeight / 3);
		accountsChatSplitPane.setEnabled(false);
		
		accountsChatSplitPane.add(previousChatPanel());
		accountsChatSplitPane.add(inputChatSplitPane(2 * frameWidth / 3));
		
		return accountsChatSplitPane;
	}
	
	private JPanel previousChatPanel() {
		previousChatPanel = new JPanel(new BorderLayout());
		
		lblInChatWith = 	new JLabel(" In Chat With: ");
		previousTextArea = 	new JTextArea();
		previousTextArea.setEnabled(false);
		
		previousChatPanel.add(lblInChatWith, BorderLayout.NORTH);
		previousChatPanel.add(previousTextArea, BorderLayout.CENTER);
		
		return previousChatPanel;
	}
	
	private JSplitPane inputChatSplitPane(int parentWidth) {
		inputChatSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		inputChatSplitPane.setDividerLocation(2 * parentWidth / 3);
		inputChatSplitPane.setEnabled(false);
		
		inputChatTextAreaPanel = 	new JPanel(new BorderLayout());
		inputChatTextArea = 		new JTextArea();
		inputChatScrollPane = 		new JScrollPane(inputChatTextArea);
		btnSendPanel =				new JPanel(new BorderLayout());
		btnSend =					new JButton("send");
		listener = 				new ButtonListener("btnSend");
		btnSend.addActionListener(listener);
		
		inputChatScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		inputChatTextAreaPanel.add(inputChatScrollPane);
		btnSendPanel.add(btnSend, BorderLayout.CENTER);		
		
		inputChatSplitPane.add(inputChatTextAreaPanel);
		inputChatSplitPane.add(btnSendPanel);
		
		return inputChatSplitPane;
	}
	
	private JSplitPane incomeAndExpensesTab() {
		incomeAndExpensesTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		incomeAndExpensesTab.setDividerLocation(frameWidth / 3);
		incomeAndExpensesTab.setEnabled(false);
		
		incomeStatementTextArea =			new JTextArea();
		incomeStatementTextArea.setFont(new Font("Consolas", Font.PLAIN, 12));
		incomeStatementScrollPane =			new JScrollPane(incomeStatementTextArea);
		lblIncomeStatement = 				new JLabel(" Income Statement:");
		
		incomeStatementScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		incomeStatementScrollPane.setColumnHeaderView(lblIncomeStatement);
		
		incomeAndExpensesTab.add(incomeAndExpensesOptionsPanel());
		incomeAndExpensesTab.add(incomeStatementScrollPane);
		
		return incomeAndExpensesTab;
	}
	
	private JPanel incomeAndExpensesOptionsPanel() {
		incomeAndExpensesOptionsPanel = new JPanel(new GridLayout(2,1));
		
		incomeAndExpensesOptionsPanel.add(incomeStatementInputPanel());
		incomeAndExpensesOptionsPanel.add(incomeStatementOptionsPanel());
		
		return incomeAndExpensesOptionsPanel;
	}
	
	private JPanel incomeStatementOptionsPanel() {
		incomeStatementOptionsPanel = new JPanel();
		incomeStatementOptionsPanel.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnOutputIncomeStatement = 	new JButton("output income statement");
		listener = 				new ButtonListener("btnOutputIncomeStatement");
		btnOutputIncomeStatement.addActionListener(listener);
		btnSave =					new JButton("save");
		listener = 				new ButtonListener("btnSave");
		btnSave.addActionListener(listener);
		incomeStatementOptionsPanel.add(btnOutputIncomeStatement);
		incomeStatementOptionsPanel.add(btnSave);
		
		return incomeStatementOptionsPanel;
	}
	
	private JPanel incomeStatementInputPanel() {
		incomeStatementInputPanel = new JPanel((new GridLayout(4, 1)));
		incomeStatementInputPanel.setBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 =			new JPanel();
		lblRevenue = 				new JLabel("revenue $");
		textField_revenue = 		new JTextField();
		textField_revenue.setColumns(14);
		panel_1.add(lblRevenue);
		panel_1.add(textField_revenue);
		
		JPanel panel_2 = 			new JPanel();
		lblExpenses =				new JLabel("expenses $");
		textField_expenses = 		new JTextField();
		textField_expenses.setColumns(13);
		panel_2.add(lblExpenses);
		panel_2.add(textField_expenses);
		
		JPanel panel_3 = 			new JPanel();
		lblAccountsPayable =		new JLabel("accounts payable $");
		textField_accountsPayable = new JTextField();
		textField_accountsPayable.setColumns(9);
		panel_3.add(lblAccountsPayable);
		panel_3.add(textField_accountsPayable);
		
		JPanel panel_4 = 			new JPanel();
		lblTax = 					new JLabel("tax $");
		textField_tax = 			new JTextField();
		textField_tax.setColumns(16);
		panel_4.add(lblTax);
		panel_4.add(textField_tax);
		
		incomeStatementInputPanel.add(panel_1);
		incomeStatementInputPanel.add(panel_2);
		incomeStatementInputPanel.add(panel_3);
		incomeStatementInputPanel.add(panel_4);
		
		return incomeStatementInputPanel;
	}
	
	private JPanel introPanel() {
		JPanel introPanel = new JPanel(new GridBagLayout());
		introPanel.setBackground(SystemColor.textHighlightText);
		
		introPanel.add(new JLabel(userName + " signed in as Treasurer"));
		
		return introPanel;
	}
	
	private JMenuBar accountsMenuBar() {
		accountsMenuBar = new JMenuBar();
		
		accountsMenuBar.add(fileMenu());
		accountsMenuBar.add(helpMenu());
		
		return accountsMenuBar;
	}
	
	private JMenu fileMenu() {
		fileMenu = new JMenu("File");
		
		newIncomeStatement = 	new JMenuItem("new income statement");
		open = 					new JMenuItem("open");
		saveAs =				new JMenuItem("save as");
		save = 					new JMenuItem("save");
		exit = 					new JMenuItem("exit");
		
		fileMenu.add(newIncomeStatement);
		fileMenu.add(open);
		fileMenu.add(saveAs);
		fileMenu.add(save);
		fileMenu.add(exit);
		
		return fileMenu;
	}
	
	private JMenu helpMenu() {
		helpMenu = new JMenu("Help");
		
		about = 			new JMenuItem("about");
		documentation = 	new JMenuItem("open documentation");
		
		helpMenu.add(about);
		helpMenu.add(documentation);
		
		return helpMenu;
	}
	
	class ButtonListener implements ActionListener {
		private String btnDescription;
		
		public ButtonListener(String description) {
			this.btnDescription = description;
		}
		
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			try {
				if (btnDescription.equals("btnOutputIncomeStatement")) {
					if (textField_revenue.getText().equals("") || textField_expenses.getText().equals("")
					|| textField_accountsPayable.getText().equals("")) {
						throw new NullPointerException("Missing value in input field");
					}
				}
				buttonPressed(btnDescription);
			}
			catch (Exception e) { JOptionPane.showMessageDialog(null, e); }
		}
	}

	private void buttonPressed(String btnDescription) {
		if (btnDescription.equals("btnOutputIncomeStatement")) {
			outputIncomeStatement();
		}
		else if (btnDescription.equals("btnSave")) {
			fileOutputTest();
		}
		else if (btnDescription.equals("btnSend")) {
			System.out.println("btnSend");
		}
	}
	
	private void outputIncomeStatement() {
		int	expenses =  Integer.parseInt(textField_expenses.getText());
		int revenue = Integer.parseInt(textField_revenue.getText());
		int accountsPayable = Integer.parseInt(textField_accountsPayable.getText());
		int tax = Integer.parseInt(textField_tax.getText());
		int liabilities = expenses + accountsPayable + tax;
		int netIncome = revenue - liabilities;
		incomeStatementTextArea.setText("LAFitness\r\nBalance Sheet\r\nDate\r\n\nAssets:\r\n"
				+"	revenue			$ " + textField_revenue.getText() + "\r\n"
				+"				------\r\n"
				+"	total assets		$ " + textField_revenue.getText() + "\r\n\r\n"
				+"Liabilities:\r\n"
				+"	expenses		$ " + textField_expenses.getText() + "\r\n"
				+"	accounts payable	$ " + textField_accountsPayable.getText() + "\r\n"
				+"	tax			$ " + textField_tax.getText() + "\r\n"
				+"				------\r\n"
				+"	total liabilities	$ " + liabilities + "\r\n\r\n"
				+"Net Income:			$ " + netIncome + "\r\n");
	}
	
	private void saveIncomeStatement() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try{
			if (incomeStatementTextArea.getText().equals("")) throw new IOException("Cannot save an empty file");
		    File incomeStatementFile = new File("LAFitnessIncomeStatement" + dateFormat.format(date).toString() + ".txt");
		    incomeStatementFile.createNewFile();
		    BufferedWriter output = new BufferedWriter(new FileWriter(incomeStatementFile));
		    output.write(incomeStatementTextArea.getText());
		    output.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	private void fileOutputTest() {
		
		File dir = new File("IncomeStatements");
		File file = new File("IncomeStatements/LAFitnessIncomeStatement.txt");
		try {
			dir.mkdir();
			PrintWriter output = new PrintWriter(file);
			output.printf(incomeStatementTextArea.getText());
			output.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}