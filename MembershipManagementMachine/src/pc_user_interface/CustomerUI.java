package pc_user_interface;
/**
 *Last Name: Barrios-Ruiz
 *First Name: Nicolas 
 *Student ID: 500773454
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

import java.util.Random;

public class CustomerUI extends JFrame{

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    private Random r = new Random();
    
    private IOWork wrk = new IOWork();
    private JPanel pCredit;
    private JPanel pDebit;

    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblPhoneNumber;
    private JLabel lblAddress;
    private JLabel lblCreditNumber;
    private JLabel lblSecurityCode;
    private JLabel lblDebitNumber;
    private JLabel lblVerificationCode;
    
    private JLabel stateBox;

    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfPhoneNumber;
    private JTextField tfAddress;
    private JTextField tfCreditNumber;
    private JTextField tfSecurityCode;
    private JTextField tfDebitNumber;
    private JTextField tfVerificationCode;

    private JTextArea notification;

    private ActionListener paymentListener;
    private ActionListener confirmListener;

    private JButton btnCredit;
    private JButton btnDebit;
    private JButton btnConfirm1;
    private JButton btnConfirm2;

    private JPanel p;
    
    private User user;
    
    private String lastNameGotten;
    private String firstNameGotten;
    private String addressGotten;
    private String phoneNumberGotten;
    private String ccNumberGotten;
    private String securityCodeGotten;
    private String dcNumberGotten;
    private String verificationCodeGotten;
    
    private JLabel payingWithDebit;
    private JLabel payingWithCredit;
    private JLabel methods;
    private JLabel curUser;

    class PaymentListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
        	p.removeAll();
            if(event.getSource() == btnCredit)
            {
                validate();
                repaint();
                pCredit();
                validate();
                repaint();  
            }
            if(event.getSource() == btnDebit)
            {
                validate();
                repaint();
                pDebit();
                validate();
                repaint();
            }
        }
    }
    class ConfirmListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) 
        {
            if(event.getSource() == btnConfirm1)
            {
                getInfoPayC();
            }
            if(event.getSource() == btnConfirm2)
            {
                getInfoPayD();
            }
        }
    }

    public CustomerUI(User u)
    {
    	p = new JPanel();
    	user = u;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
    	PaymentListener payment = new PaymentListener();
    	
    	JPanel topBar = new JPanel();
    	curUser = new JLabel("Current user is " + u.getUserFirstName());
    	topBar.add(curUser);
    	topBar.setBackground(Color.WHITE);
    	p.add(topBar);
    	
    	methods = new JLabel("Subscription Payment Options");
    	p.add(methods);
    	
    	JPanel holder2 = new JPanel();
    	btnCredit = new JButton("Credit");
    	btnCredit.addActionListener(payment);
    	holder2.add(btnCredit);
    	btnDebit = new JButton("Debit");
    	btnDebit.addActionListener(payment);
    	holder2.add(btnDebit);
    	p.add(holder2);
    	
    	notification = new JTextArea();
        notification.setEditable(false);
        notification.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Notifications:"), notification.getBorder()));
        notification.setText(u.getNotification());
        p.add(notification);
        
        this.add(p);
        
        setTitle("Welcome");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setMinimumSize(new Dimension(400, 400));
        setResizable(false);
		setVisible(true);
    }

    public void pCredit()
    {
        p.setLayout(new BorderLayout());
        JPanel pCreditConfrim = new JPanel();
        pCreditConfrim.setLayout(new BoxLayout(pCreditConfrim, BoxLayout.Y_AXIS));
        pCredit = new JPanel(new GridLayout(6,2));
        
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();

        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();
        JPanel p9 = new JPanel();
        JPanel p10 = new JPanel();
        JPanel p11 = new JPanel();
        JPanel p12 = new JPanel();

        lblFirstName = new JLabel("First Name:");
        p7.add(lblFirstName);
        lblLastName = new JLabel("Last Name:");
        p8.add(lblLastName);
        lblPhoneNumber = new JLabel("Phone Number:");
        p9.add(lblPhoneNumber);
        lblAddress = new JLabel("Address:");
        p10.add(lblAddress);
        lblCreditNumber = new JLabel("Credit Card Number:");
        p11.add(lblCreditNumber);
        lblSecurityCode = new JLabel("Security Code:");
        p12.add(lblSecurityCode);

        tfFirstName = new JTextField(10);
        p1.add(tfFirstName);
        tfLastName = new JTextField(10);
        p2.add(tfLastName);
        tfPhoneNumber = new JTextField(10);
        p3.add(tfPhoneNumber);
        tfAddress = new JTextField(10);
        p4.add(tfAddress);
        tfCreditNumber = new JTextField(10);
        p5.add(tfCreditNumber);
        tfSecurityCode = new JTextField(10);
        p6.add(tfSecurityCode);

        pCredit.add(p7);
        pCredit.add(p1);
        pCredit.add(p8);
        pCredit.add(p2);
        pCredit.add(p9);
        pCredit.add(p3);
        pCredit.add(p10);
        pCredit.add(p4);
        pCredit.add(p11);
        pCredit.add(p5);
        pCredit.add(p12);
        pCredit.add(p6);

        JPanel pButton = new JPanel();
        btnConfirm1 = new JButton("Confirm");
        pButton.add(btnConfirm1);
        confirmListener = new ConfirmListener();
        btnConfirm1.addActionListener(confirmListener);
        
        JPanel holder = new JPanel();
        payingWithCredit = new JLabel("Paying with Credit");
        payingWithCredit.setVisible(true);
        holder.add(payingWithCredit);
        
        pCreditConfrim.add(holder);
        pCreditConfrim.add(pCredit);
        pCreditConfrim.add(pButton);
        
        JPanel panel = new JPanel();
        stateBox = new JLabel("");
        stateBox.setVisible(true);
        panel.add(stateBox);
        pCreditConfrim.add(panel);

        p.add(pCreditConfrim, BorderLayout.NORTH);

    }
    public void pDebit()
    {
           p.setLayout(new BorderLayout());
            JPanel pDebitConfrim = new JPanel();
            pDebitConfrim.setLayout(new BoxLayout(pDebitConfrim, BoxLayout.Y_AXIS));
                pDebit = new JPanel(new GridLayout(6,2));
                    JPanel p1 = new JPanel();
                    JPanel p2 = new JPanel();
                    JPanel p3 = new JPanel();
                    JPanel p4 = new JPanel();
                    JPanel p5 = new JPanel();
                    JPanel p6 = new JPanel();

                    JPanel p7 = new JPanel();
                    JPanel p8 = new JPanel();
                    JPanel p9 = new JPanel();
                    JPanel p10 = new JPanel();
                    JPanel p11 = new JPanel();
                    JPanel p12 = new JPanel();

                    lblFirstName = new JLabel("First Name:");
                        p7.add(lblFirstName);
                    lblLastName = new JLabel("Last Name:");
                        p8.add(lblLastName);
                    lblPhoneNumber = new JLabel("Phone Number:");
                        p9.add(lblPhoneNumber);
                    lblAddress = new JLabel("Address:");
                        p10.add(lblAddress);
                    lblDebitNumber = new JLabel("Debit Card Number:");
                        p11.add(lblDebitNumber);
                    lblVerificationCode = new JLabel("Verification Code:");
                        p12.add(lblVerificationCode);

                    tfFirstName = new JTextField(10);
                        p1.add(tfFirstName);
                    tfLastName = new JTextField(10);
                        p2.add(tfLastName);
                    tfPhoneNumber = new JTextField(10);
                        p3.add(tfPhoneNumber);
                    tfAddress = new JTextField(10);
                        p4.add(tfAddress);
                    tfDebitNumber = new JTextField(10);
                        p5.add(tfDebitNumber);
                    tfVerificationCode = new JTextField(10);
                        p6.add(tfVerificationCode);

                    pDebit.add(p7);
                    pDebit.add(p1);
                    pDebit.add(p8);
                    pDebit.add(p2);
                    pDebit.add(p9);
                    pDebit.add(p3);
                    pDebit.add(p10);
                    pDebit.add(p4);
                    pDebit.add(p11);
                    pDebit.add(p5);
                    pDebit.add(p12);
                    pDebit.add(p6);

                JPanel pButton = new JPanel();
                    btnConfirm2 = new JButton("Confirm");
                    pButton.add(btnConfirm2);
                    confirmListener = new ConfirmListener();
                    btnConfirm2.addActionListener(confirmListener);

                    JPanel holder = new JPanel();
                    payingWithDebit = new JLabel("Paying with Debit");
                    payingWithDebit.setVisible(true);
                    holder.add(payingWithDebit);
                    
            pDebitConfrim.add(holder);
            pDebitConfrim.add(pDebit);
            pDebitConfrim.add(pButton);
            
            JPanel panel = new JPanel();
            stateBox = new JLabel("");
            stateBox.setVisible(true);
            panel.add(stateBox);
            pDebitConfrim.add(panel);
            
        p.add(pDebitConfrim, BorderLayout.NORTH);

    }

    public void getInfoPayD(){
        firstNameGotten = tfFirstName.getText();
        lastNameGotten = tfLastName.getText();
        phoneNumberGotten = tfPhoneNumber.getText();
        addressGotten = tfAddress.getText();
        dcNumberGotten = tfDebitNumber.getText();
        verificationCodeGotten = tfVerificationCode.getText();

        if(lastNameGotten.isEmpty() || firstNameGotten.isEmpty() || dcNumberGotten.isEmpty() || phoneNumberGotten.isEmpty() || addressGotten.isEmpty() || verificationCodeGotten.isEmpty()){
            stateBox.setText("Payment information is still missing");
        }
        else if(isLetter(firstNameGotten) != true || isLetter(lastNameGotten) != true || isNum(phoneNumberGotten) != true || isNum(dcNumberGotten) != true || isNum(verificationCodeGotten) != true)
        {
        	stateBox.setText("Payment information is the wrong format in one of the fields. ");
        }
        else
        {
            user.setMissedPayments(r.nextInt(5) + 1);
            user.setAttendedClasses(r.nextInt(5) + 1);
            user.setHasPaid(true);
            stateBox.setText("Payment was Successful");
            wrk.serialize(user);
        }
    }

    public void getInfoPayC(){
        firstNameGotten = tfFirstName.getText();
        lastNameGotten = tfLastName.getText();
        phoneNumberGotten = tfPhoneNumber.getText();
        addressGotten = tfAddress.getText();
        ccNumberGotten = tfCreditNumber.getText();
        securityCodeGotten = tfSecurityCode.getText();

        if(lastNameGotten.isEmpty() || firstNameGotten.isEmpty() || ccNumberGotten.isEmpty() || phoneNumberGotten.isEmpty() || addressGotten.isEmpty() || securityCodeGotten.isEmpty()){
        	stateBox.setText("Payment information is still missing");
        }
        else if(isLetter(firstNameGotten) != true || isLetter(lastNameGotten) != true || isNum(phoneNumberGotten) != true || isNum(ccNumberGotten) != true || isNum(securityCodeGotten) != true)
        {
        	stateBox.setText("Payment information is the wrong format in one of the fields. ");
        }
        else
        {
            user.setMissedPayments(r.nextInt(5) + 1);
            user.setAttendedClasses(r.nextInt(5) + 1);
            user.setHasPaid(true);
            stateBox.setText("Payment was Successful");
            wrk.serialize(user);
        }
    }
    public boolean isLetter(String input)
    {
    	return input.matches("[a-zA-Z]+");
    }
    public boolean isNum(String input)
    {
    	try
    	{
    		Integer.parseInt(input);
    	}
    	catch(NumberFormatException e)
    	{
    		return false;
    	}
    	catch(NullPointerException e)
    	{
    		return false;
    	}
    	return true;
    }
}
