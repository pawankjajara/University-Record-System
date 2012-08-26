package GUI;

//import universitySystem.*;

import java.awt.EventQueue;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.TextArea;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import URS.UniversityClient;
import org.omg.CORBA.ExceptionList;

public class AddOfficeHours extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField id;
	private static JTextField loc;
	private TextArea textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];
    String sample="sample";
	
	public AddOfficeHours() {
		setTitle("Add Office Hours");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 425, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Instructor ID:");
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructorId.setBounds(10, 14, 143, 14);
		contentPane.add(lblInstructorId);
		
		id = new JTextField();
		id.setBounds(229, 12, 170, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton btnGetOfficeHours = new JButton("Add Office Hours");
		btnGetOfficeHours.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String ID=AddOfficeHours.id.getText();
				String Loc=AddOfficeHours.loc.getText();
				 uc = new UniversityClient();
				 try {
					TMReceive = uc.Add_Office_Hours(ID,Loc);
					response = TMReceive.getText();
					array=response.split("\n");
										
					textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}											
			}
		});
		btnGetOfficeHours.setBounds(229, 96, 170, 23);
		contentPane.add(btnGetOfficeHours);
		
		JLabel lblNumberOfOffice = new JLabel("Location of new Office Hours:");
		lblNumberOfOffice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumberOfOffice.setBounds(10, 53, 209, 14);
		contentPane.add(lblNumberOfOffice);
		
		loc = new JTextField();
		loc.setBounds(229, 51, 170, 20);
		contentPane.add(loc);
		loc.setColumns(10);
		
		JLabel lblLocations = new JLabel("Status:");
		lblLocations.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLocations.setBounds(10, 148, 143, 14);
		contentPane.add(lblLocations);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 175, 389, 64);
		contentPane.add(textArea_Response);
		textArea_Response.setColumns(10);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(310, 262, 89, 23);
		contentPane.add(btnExit);
	}
}
