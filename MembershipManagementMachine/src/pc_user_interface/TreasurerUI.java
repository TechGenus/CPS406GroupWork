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
	private int				frameHeight = 600;
	
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
	private JPanel			customerSortingPanel;
	private JList 			customerList;
	private JScrollPane 	customerScrollPane;
	
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
		accountsTab.setDividerLocation(2 * frameWidth / 3);
		accountsTab.setEnabled(false);
		
		customerList = 				new JList<JLabel>();
		customerScrollPane = 		new JScrollPane(customerList);
		customerSortingPanel = 		new JPanel();
		
		customerList.add(new JLabel("Hello"));
		
		customerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		customerScrollPane.setColumnHeaderView(customerSortingPanel);
		
		accountsTab.add(customerScrollPane);
		accountsTab.add(customerOptionsPanel());
		
		return accountsTab;
	}
	
	private JPanel customerOptionsPanel() {
		JPanel customerOptionsPanel = new JPanel(new GridLayout());
		
		customerOptionsPanel.add(customerOptionsGrid(), BorderLayout.CENTER);
		
		
		// Panel 2
		JPanel p2 = new JPanel(new BorderLayout());
		JButton btnRemoveSelectedAccount = new JButton("Remove selected account");
		listener = new ButtonListener("btnRemoveSelectedAccount");
		btnRemoveSelectedAccount.addActionListener(listener);
		p2.add(btnRemoveSelectedAccount, BorderLayout.SOUTH);
		
		customerOptionsPanel.add(p2, BorderLayout.SOUTH);
		
		return customerOptionsPanel;
	}
	
	private JPanel customerOptionsGrid() {
		JPanel gridP1 = new JPanel(new GridLayout(18, 1));
		
		JPanel gp1 = new JPanel();
		// --------------------------------------------------------------finish this
		gridP1.add(gp1);
		
		JPanel gp2 = new JPanel();
		JLabel lblUserName =  new JLabel("user name");
		gp2.add(lblUserName);
		JTextField textField_userName = new JTextField(14);
		gp2.add(textField_userName);
		gridP1.add(gp2);
		
		JPanel gp3 = new JPanel();
		JLabel lblUserPassword =  new JLabel("password");
		gp3.add(lblUserPassword);
		JPasswordField passwordField_userPassword = new JPasswordField(14);
		gp3.add(passwordField_userPassword);
		gridP1.add(gp3);
		
		JPanel gp4 = new JPanel();
		JLabel lblUserFirstName =  new JLabel("first name");
		gp4.add(lblUserFirstName);
		JTextField textField_userFirstName = new JTextField(14);
		gp4.add(textField_userFirstName);
		gridP1.add(gp4);
		
		JPanel gp5 = new JPanel();
		JLabel lblUserLastName =  new JLabel("last name");
		gp5.add(lblUserLastName);
		JTextField textField_userLastName = new JTextField(14);
		gp5.add(textField_userLastName);
		gridP1.add(gp5);
		
		JPanel gp6 = new JPanel();
		JLabel lblUserAddress =  new JLabel("address");
		gp6.add(lblUserAddress);
		JTextField textField_userAddress = new JTextField(15);
		gp6.add(textField_userAddress);
		gridP1.add(gp6);
		
		JPanel gp7 = new JPanel();
		JLabel lblUserPhoneNumber =  new JLabel("phone number");
		gp7.add(lblUserPhoneNumber);
		JTextField textField_userPhoneNumber1 = new JTextField(3);
		gp7.add(textField_userPhoneNumber1);
		JTextField textField_userPhoneNumber2 = new JTextField(3);
		gp7.add(textField_userPhoneNumber2);
		JTextField textField_userPhoneNumber3 = new JTextField(4);
		gp7.add(textField_userPhoneNumber3);
		gridP1.add(gp7);
		
		return gridP1;
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