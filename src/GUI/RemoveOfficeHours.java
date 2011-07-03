package GUI;
import URS.UniversityClient;

import java.awt.EventQueue;
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

public class RemoveOfficeHours extends JFrame {

	private JPanel contentPane;
	private static JTextField id;
	private static JTextField loc;
	private TextArea textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];
	
	public RemoveOfficeHours() {
		setTitle("Remove Office Hours");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 424, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Instructor ID:");
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInstructorId.setBounds(10, 14, 143, 14);
		contentPane.add(lblInstructorId);
		
		id = new JTextField();
		id.setBounds(198, 11, 170, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton btnGetOfficeHours = new JButton("Remove Office Hours");
		btnGetOfficeHours.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String ID=RemoveOfficeHours.id.getText();
				String Loc=RemoveOfficeHours.loc.getText();
				 uc = new UniversityClient();
				 try {
					TMReceive = uc.Remove_Office_Hours(ID,Loc);
					response = TMReceive.getText();
					array=response.split("\n");															
					textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);														
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}											
			}
		});
		btnGetOfficeHours.setBounds(198, 93, 170, 23);
		contentPane.add(btnGetOfficeHours);
		
		JLabel lblNumberOfOffice = new JLabel("Location of Office Hours to Remove:");
		lblNumberOfOffice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumberOfOffice.setBounds(10, 53, 240, 14);
		contentPane.add(lblNumberOfOffice);
		
		loc = new JTextField();
		loc.setBounds(225, 50, 143, 20);
		contentPane.add(loc);
		loc.setColumns(10);
		
		JLabel lblLocations = new JLabel("Response:");
		lblLocations.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocations.setBounds(10, 136, 143, 14);
		contentPane.add(lblLocations);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnExit.setBounds(279, 220, 89, 23);
		contentPane.add(btnExit);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 161, 358, 48);
		contentPane.add(textArea_Response);
		textArea_Response.setColumns(10);
	}

}
