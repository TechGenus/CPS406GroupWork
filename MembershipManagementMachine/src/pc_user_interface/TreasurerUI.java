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

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;

public class TreasurerUI extends JFrame{
	private int				frameWidth 	= 800;
	private int				frameHeight = 700;
	
	private String			userName;
	private Date			date;
	
	private ActionListener 	listener;
	private ItemListener	cbHandler;
	
	private JMenuBar 		accountsMenuBar;
	private JMenu			fileMenu, menuIncomeStatement, menuCustomerFile, helpMenu;
	private JMenuItem		fmbtnNewIncomeStatement, fmbtnOpenIncomeStatement, fmbtnSaveIncomeStatement, 
							fmbtnExit, fmbtnNewCustomerFile, about, documentation;
	
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
	private JPanel			accountsSortingPanel, editAccountsTab, addAndRemoveAccountsTab;
	private JList 			accountsList;
	private JScrollPane 	accountsScrollPane;
	private JTabbedPane		accountsOptionsTabbedPane;
	private JButton			btnAdd10PercentDiscount, btnAdd10PercentPenalty, btnAddNewCustomer, btnAddNewCoach,
							btnAddNewTreasurer, btnRemoveSelectedAccount;
	
	public TreasurerUI(String frameTitle, String userName) {
		setTitle(frameTitle);
		this.userName = userName;
		setVisible(true);
		this.fillFrame();
		setSize(frameWidth, frameHeight);
		setMinimumSize(new Dimension(frameWidth, frameHeight));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
	}
	
	private void fillFrame() {
		setJMenuBar(accountsMenuBar());
		getContentPane().add(headerPanel(),	BorderLayout.NORTH);
		getContentPane().add(tabbedPane(),	BorderLayout.CENTER);
	}
	
	private JTabbedPane tabbedPane() {
		tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Accounts", 				null, accountsTab(),			null);
		tabbedPane.addTab("Income and Expenses",	null, incomeAndExpensesTab(),	null);
		
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		// this line allows u to change the tab index to focus on what ever tab u want
		tabbedPane.setSelectedIndex(0);
		
		return tabbedPane;
	}
	
	private JSplitPane accountsTab() {
		accountsTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		accountsTab.setDividerLocation(5 * frameWidth / 8);
		accountsTab.setEnabled(false);
		
		accountsList = 				new JList<JLabel>();
		accountsScrollPane = 		new JScrollPane(accountsList);
		accountsSortingPanel = 		new JPanel();
		
		accountsList.add(new JLabel("Hello"));
		
		accountsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		accountsScrollPane.setColumnHeaderView(accountsSortingPanel);
		
		accountsTab.add(accountsScrollPane);
		accountsTab.add(accountsOptionsTabbedPane());
		
		return accountsTab;
	}
	
	private JTabbedPane accountsOptionsTabbedPane() {
		accountsOptionsTabbedPane = new JTabbedPane();

		
		accountsOptionsTabbedPane.addTab("Edit Accounts",		null, editAccountsTab(),			null);
		accountsOptionsTabbedPane.addTab("Add/Remove Accounts", null, addAndRemoveAccountsTab(),	null);
		
		accountsOptionsTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		accountsOptionsTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		// this line allows u to change the tab index to focus on what ever tab u want
		accountsOptionsTabbedPane.setSelectedIndex(0);
		
		return accountsOptionsTabbedPane;
	}
	
	private JPanel editAccountsTab() {
		editAccountsTab = new JPanel(new GridLayout(2,1));
		
		JPanel p1 = new JPanel(new BorderLayout()); 
		JLabel lblSelectedAccountInfo = new JLabel("Selected account information:");
		p1.add(lblSelectedAccountInfo, BorderLayout.NORTH);
		JTextArea selectedAccountInfoTextArea = new JTextArea();
		p1.add(selectedAccountInfoTextArea, BorderLayout.CENTER);
		editAccountsTab.add(p1);
		
		JPanel ButtonGrid = new JPanel(new GridLayout(2,1));
		
		JPanel p2 = new JPanel(new GridBagLayout());
		btnAdd10PercentDiscount = new JButton("Add 10% Discount to Selected Account");
		listener = new ButtonListener("btnAdd10PercentDiscount");
		btnAdd10PercentDiscount.addActionListener(listener);
		p2.add(btnAdd10PercentDiscount);
		editAccountsTab.add(p2);
		
		JPanel p3 = new JPanel(new GridBagLayout());
		btnAdd10PercentPenalty = new JButton(" Add 10% Penalty to Selected Account ");
		listener = new ButtonListener("btnAdd10PercentPenalty");
		btnAdd10PercentPenalty.addActionListener(listener);
		p3.add(btnAdd10PercentPenalty);
		editAccountsTab.add(p3);
		
		ButtonGrid.add(p2);
		ButtonGrid.add(p3);
		editAccountsTab.add(ButtonGrid);
		
		return editAccountsTab;
	}
	
	private JPanel addAndRemoveAccountsTab() {
		addAndRemoveAccountsTab = new JPanel(new GridLayout(22, 1));
		
		btnAddNewCustomer = new JButton("Add new customer");
		listener = new ButtonListener("btnAddNewCustomer");
		btnAddNewCustomer.addActionListener(listener);
		addAndRemoveAccountsTab.add(btnAddNewCustomer);
		
		JPanel p2 = new JPanel();
		JLabel lblCustomerUserName =  new JLabel("user name");
		p2.add(lblCustomerUserName);
		JTextField textField_customerUserName = new JTextField(14);
		p2.add(textField_customerUserName);
		addAndRemoveAccountsTab.add(p2);
		
		JPanel p3 = new JPanel();
		JLabel lblCustomerPassword =  new JLabel("password");
		p3.add(lblCustomerPassword);
		JPasswordField passwordField_customerPassword = new JPasswordField(14);
		p3.add(passwordField_customerPassword);
		addAndRemoveAccountsTab.add(p3);
		
		JPanel p4 = new JPanel();
		JLabel lblCustomerFirstName =  new JLabel("first name");
		p4.add(lblCustomerFirstName);
		JTextField textField_customerFirstName = new JTextField(14);
		p4.add(textField_customerFirstName);
		addAndRemoveAccountsTab.add(p4);
		
		JPanel p5 = new JPanel();
		JLabel lblCustomerLastName =  new JLabel("last name");
		p5.add(lblCustomerLastName);
		JTextField textField_customerLastName = new JTextField(14);
		p5.add(textField_customerLastName);
		addAndRemoveAccountsTab.add(p5);
		
		JPanel p6 = new JPanel();
		JLabel lblCustomerAddress =  new JLabel("address");
		p6.add(lblCustomerAddress);
		JTextField textField_customerAddress = new JTextField(15);
		p6.add(textField_customerAddress);
		addAndRemoveAccountsTab.add(p6);
		
		JPanel p7 = new JPanel();
		JLabel lblCustomerPhoneNumber =  new JLabel("phone number");
		p7.add(lblCustomerPhoneNumber);
		JTextField textField_customerPhoneNumber1 = new JTextField(3);
		p7.add(textField_customerPhoneNumber1);
		JTextField textField_customerPhoneNumber2 = new JTextField(3);
		p7.add(textField_customerPhoneNumber2);
		JTextField textField_customerPhoneNumber3 = new JTextField(4);
		p7.add(textField_customerPhoneNumber3);
		addAndRemoveAccountsTab.add(p7);
		
		JPanel p8 = new JPanel();
		addAndRemoveAccountsTab.add(p8);
		
		btnAddNewCoach = new JButton("Add new coach");
		listener = new ButtonListener("btnAddNewCoach");
		btnAddNewCoach.addActionListener(listener);
		addAndRemoveAccountsTab.add(btnAddNewCoach);
		
		JPanel p10 = new JPanel();
		JLabel lblCoachUserName = new JLabel("user name");
		p10.add(lblCoachUserName);
		JTextField textField_coachUserName = new JTextField(14);
		p10.add(textField_coachUserName);
		addAndRemoveAccountsTab.add(p10);
		
		JPanel p11 = new JPanel();
		JLabel lblCoachPassword = new JLabel("password");
		p11.add(lblCoachPassword);
		JPasswordField passwordField_coachPassword = new JPasswordField(14);
		p11.add(passwordField_coachPassword);
		addAndRemoveAccountsTab.add(p11);
		
		JPanel p12 = new JPanel();
		JLabel lblCoachFirstName = new JLabel("first name");
		p12.add(lblCoachFirstName);
		JTextField textField_coachFirstName = new JTextField(14);
		p12.add(textField_coachFirstName);
		addAndRemoveAccountsTab.add(p12);
		
		JPanel p13 = new JPanel();
		JLabel lblCoachLastName = new JLabel("last name");
		p13.add(lblCoachLastName);
		JTextField textField_coachLastName = new JTextField(14);
		p13.add(textField_coachLastName);
		addAndRemoveAccountsTab.add(p13);
		
		JPanel p14 = new JPanel();
		JLabel lblCoachSalary = new JLabel("salary");
		p14.add(lblCoachSalary);
		JTextField textField_coachSalary = new JTextField(16);
		p14.add(textField_coachSalary);
		addAndRemoveAccountsTab.add(p14);
		
		JPanel p15 = new JPanel();
		addAndRemoveAccountsTab.add(p15);
		
		btnAddNewTreasurer = new JButton("Add new treasurer");
		listener = new ButtonListener("btnAddNewTreasurer");
		btnAddNewTreasurer.addActionListener(listener);
		addAndRemoveAccountsTab.add(btnAddNewTreasurer);
		
		JPanel p17 = new JPanel();
		JLabel lblTreasurerUserName = new JLabel("user name");
		p17.add(lblTreasurerUserName);
		JTextField textField_treasurerUserName = new JTextField(14);
		p17.add(textField_treasurerUserName);
		addAndRemoveAccountsTab.add(p17);
		
		JPanel p18 = new JPanel();
		JLabel lblTreasurerPassword = new JLabel("password");
		p18.add(lblTreasurerPassword);
		JPasswordField passwordField_treasurerPassword = new JPasswordField(14);
		p18.add(passwordField_treasurerPassword);
		addAndRemoveAccountsTab.add(p18);
		
		JPanel p19 = new JPanel();
		JLabel lblTreasurerFirstName = new JLabel("first name");
		p19.add(lblTreasurerFirstName);
		JTextField textField_treasurerFirstName = new JTextField(14);
		p19.add(textField_treasurerFirstName);
		addAndRemoveAccountsTab.add(p19);
		
		JPanel p20 = new JPanel();
		JLabel lblTreasurerLastName = new JLabel("last name");
		p20.add(lblTreasurerLastName);
		JTextField textField_treasurerLastName = new JTextField(14);
		p20.add(textField_treasurerLastName);
		addAndRemoveAccountsTab.add(p20);
		
		JPanel p21 = new JPanel();
		addAndRemoveAccountsTab.add(p21);
		
		btnRemoveSelectedAccount = new JButton("Remove selected account");
		listener = new ButtonListener("btnRemoveSelectedAccount");
		btnRemoveSelectedAccount.addActionListener(listener);
		addAndRemoveAccountsTab.add(btnRemoveSelectedAccount);
		
		return addAndRemoveAccountsTab;
	}
	
	private JSplitPane incomeAndExpensesTab() {
		incomeAndExpensesTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		incomeAndExpensesTab.setDividerLocation(frameWidth / 3);
		incomeAndExpensesTab.setEnabled(false);
		
		incomeStatementTextArea =			new JTextArea();
		incomeStatementTextArea.setFont(new Font("Consolas", Font.PLAIN, 12));
		incomeStatementTextArea.setEditable(false);
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
		listener = 					new ButtonListener("btnOutputIncomeStatement");
		btnOutputIncomeStatement.addActionListener(listener);
		btnSave =					new JButton("save");
		listener = 					new ButtonListener("btnSave");
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
		textField_revenue = 		new JTextField(14);
		panel_1.add(lblRevenue);
		panel_1.add(textField_revenue);
		
		JPanel panel_2 = 			new JPanel();
		lblExpenses =				new JLabel("expenses $");
		textField_expenses = 		new JTextField(13);
		panel_2.add(lblExpenses);
		panel_2.add(textField_expenses);
		
		JPanel panel_3 = 			new JPanel();
		lblAccountsPayable =		new JLabel("accounts payable $");
		textField_accountsPayable = new JTextField(9);
		panel_3.add(lblAccountsPayable);
		panel_3.add(textField_accountsPayable);
		
		JPanel panel_4 = 			new JPanel();
		lblTax = 					new JLabel("tax $");
		textField_tax = 			new JTextField(16);
		panel_4.add(lblTax);
		panel_4.add(textField_tax);
		
		incomeStatementInputPanel.add(panel_1);
		incomeStatementInputPanel.add(panel_2);
		incomeStatementInputPanel.add(panel_3);
		incomeStatementInputPanel.add(panel_4);
		
		return incomeStatementInputPanel;
	}
	
	private JPanel headerPanel() {
		JPanel headerPanel = new JPanel(new GridBagLayout());
		headerPanel.setBackground(SystemColor.textHighlightText);
		
		headerPanel.add(new JLabel(userName + " signed in as Treasurer"));
		
		return headerPanel;
	}
	
	private JMenuBar accountsMenuBar() {
		accountsMenuBar = new JMenuBar();
		
		accountsMenuBar.add(fileMenu());
		accountsMenuBar.add(helpMenu());
		
		return accountsMenuBar;
	}
	
	private JMenu fileMenu() {
		fileMenu = new JMenu("File");
		
		menuCustomerFile = new JMenu("Customer");
		fmbtnNewCustomerFile = 	new JMenuItem("new");
		listener =	new ButtonListener("fmbtnNewCustomerFile");
		fmbtnNewCustomerFile.addActionListener(listener);		
		menuCustomerFile.add(fmbtnNewCustomerFile);

		fmbtnExit = new JMenuItem("Exit");
		
		fileMenu.add(menuIncomeStatement());
		fileMenu.add(menuCustomerFile);
		fileMenu.add(fmbtnExit);
		
		return fileMenu;
	}
	
	private JMenu menuIncomeStatement() {
		menuIncomeStatement = new JMenu("Income Statement");
		
		fmbtnNewIncomeStatement = 		new JMenuItem("new");
		listener =	new ButtonListener("fmbtnNewIncomeStatement");
		fmbtnNewIncomeStatement.addActionListener(listener);
		menuIncomeStatement.add(fmbtnNewIncomeStatement);
		
		fmbtnSaveIncomeStatement = 		new JMenuItem("save");
		listener =	new ButtonListener("fmbtnSaveIncomeStatement");
		fmbtnSaveIncomeStatement.addActionListener(listener);
		menuIncomeStatement.add(fmbtnSaveIncomeStatement);
		
		fmbtnOpenIncomeStatement =		new JMenuItem("open");
		listener =	new ButtonListener("fmbtnOpenIncomeStatement");
		fmbtnOpenIncomeStatement.addActionListener(listener);
		menuIncomeStatement.add(fmbtnOpenIncomeStatement);
		
		return menuIncomeStatement;
	}
	
	private JMenu helpMenu() {
		helpMenu = new JMenu("Help");
		
		about = 			new JMenuItem("about");
		documentation = 	new JMenuItem("open documentation");
		
		helpMenu.add(about);
		helpMenu.add(documentation);
		
		return helpMenu;
	}
	
	public class ButtonListener implements ActionListener {
		private String btnDescription;
		
		public ButtonListener(String description) {
			this.btnDescription = description;
		}
		
		public void actionPerformed(ActionEvent event) {
			try {
				if (btnDescription.equals("btnOutputIncomeStatement") 
				|| btnDescription.equals("btnSave")
				|| btnDescription.equals("fmbtnSaveIncomeStatement")
				|| btnDescription.equals("fmbtnSaveAsIncomeStatement")) {
					if (textField_revenue.getText().equals("") 
					|| textField_expenses.getText().equals("")
					|| textField_accountsPayable.getText().equals("") 
					|| textField_tax.getText().equals("")) {
						tabbedPane.setSelectedIndex(1);
						throw new NullPointerException("Missing value in input field");
					}
					else if (Integer.parseInt(textField_revenue.getText()) < 0 
					|| Integer.parseInt(textField_expenses.getText()) < 0
					|| Integer.parseInt(textField_accountsPayable.getText()) < 0
					|| Integer.parseInt(textField_tax.getText()) < 0) {
						throw new ArithmeticException("Caution: negative value in input field");
					}
				}
				buttonPressed(btnDescription);
			}
			catch (Exception e) { JOptionPane.showMessageDialog(null, e); }
		}
	}

	private void buttonPressed(String btnDescription) throws Exception {
		if (btnDescription.equals("btnOutputIncomeStatement")) {
			outputIncomeStatement();
		}
		else if (btnDescription.equals("btnSave") || btnDescription.equals("fmbtnSaveIncomeStatement")) {
			saveIncomeStatement();
		}
		else if (btnDescription.equals("btnSend")) {
			System.out.println("btnSend");
		}
		else if (btnDescription.equals("fmbtnNewIncomeStatement")) {
			tabbedPane.setSelectedIndex(1);
		}
		else if (btnDescription.equals("fmbtnOpenIncomeStatement")) {
			openIncomeStatement();
		}
	}
	
	private void outputIncomeStatement() {
		int revenue = Integer.parseInt(textField_revenue.getText());
		
		int	expenses =  Integer.parseInt(textField_expenses.getText());
		int accountsPayable = Integer.parseInt(textField_accountsPayable.getText());
		int tax = Integer.parseInt(textField_tax.getText());
		int liabilities = expenses + accountsPayable + tax;
		
		int netIncome = revenue - liabilities;
		
		date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		incomeStatementTextArea.setText("LAFitness\r\n"
				+ "Balance Sheet\r\n"
				+ "Date: " + dateFormat.format(date) + "\r\n\n"
				+ "Assets:\r\n"
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
		DateFormat dateFormat = new SimpleDateFormat(" yyyy_MM");
		
		File dir = new File("IncomeStatements");
		File file = new File("IncomeStatements/LAFitnessIncomeStatement" + dateFormat.format(date) + ".txt");
		
		try {			
			dir.mkdir();
			
			PrintWriter output = new PrintWriter(file);
			output.printf(incomeStatementTextArea.getText());
			output.close();
			
			JOptionPane.showMessageDialog(null, "saved: " + file);
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	@SuppressWarnings("resource")
	private void openIncomeStatement() throws Exception {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("text file", "txt"));
		fileChooser.setCurrentDirectory(new File ("IncomeStatements"));
		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			//get the file
			java.io.File file = fileChooser.getSelectedFile();
			//TODO----------------------------------------------------------------------------------------------------: check if file is actually income statement
			//if (!file.toString().substring(0, 16).equals("IncomeStatement")) { throw new IOException("File is not an income statement file"); }
			
			// scanner for the file
			Scanner scan = new Scanner(file);
			if (!scan.next().equals("LAFitness")) { throw new IOException("File is not an income statement file"); }
			
			int counter = 0;
			while(scan.hasNext()) {
				if (scan.hasNextInt() && counter == 0) {
					textField_revenue.setText(scan.next());
					counter++;
				}
				else if (scan.hasNextInt() && counter == 2) {
					textField_expenses.setText(scan.next());
					counter++;
				}
				else if (scan.hasNextInt() && counter == 3) {
					textField_accountsPayable.setText(scan.next());
					counter++;
				}
				else if (scan.hasNextInt() && counter == 4) {
					textField_tax.setText(scan.next());
					counter++;
				}
				else if (scan.hasNextInt()) {
					scan.next();
					counter++;
				}
				else scan.next();
			}
			scan.close(); 
			if (counter > 7) { throw new IOException("Error: Not an income statement"); }
			else { 
				tabbedPane.setSelectedIndex(1);
				outputIncomeStatement();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "No file was selected");
		}
	}
}