package GUI;
import URS.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
public class CreateStudent extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public UniversityClient uc;	
	public TextMessage TMReceive;
	
	private static JTextField textField_Student_ID;
	private static JTextField textField_FirstName;
	private static JTextField textField_LastName;
	private static JTextField textField_Address;
	private static JTextField textField_City;
	private static JTextField textField_State;
	private static JTextField textField_Zip;
	private static TextArea textArea_response;

	
	public CreateStudent() {
		setTitle("Create Student");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 100, 471, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Student ID:");
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructorId.setBounds(10, 77, 110, 14);
		contentPane.add(lblInstructorId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(10, 21, 110, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(10, 46, 110, 14);
		contentPane.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 111, 110, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 157, 110, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblState.setBounds(10, 182, 110, 14);
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblZipCode.setBounds(10, 207, 110, 14);
		contentPane.add(lblZipCode);
		
		textField_Student_ID = new JTextField();
		textField_Student_ID.setBounds(130, 75, 283, 20);
		contentPane.add(textField_Student_ID);
		textField_Student_ID.setColumns(10);
		
		textField_FirstName = new JTextField();
		textField_FirstName.setBounds(130, 19, 283, 20);
		contentPane.add(textField_FirstName);
		textField_FirstName.setColumns(10);
		
		textField_LastName = new JTextField();
		textField_LastName.setBounds(130, 44, 283, 20);
		contentPane.add(textField_LastName);
		textField_LastName.setColumns(10);
		
		textField_Address = new JTextField();
		textField_Address.setBounds(130, 108, 283, 23);
		contentPane.add(textField_Address);
		textField_Address.setColumns(10);
		
		textField_City = new JTextField();
		textField_City.setBounds(129, 155, 131, 20);
		contentPane.add(textField_City);
		textField_City.setColumns(10);
		
		textField_State = new JTextField();
		textField_State.setBounds(130, 180, 86, 20);
		contentPane.add(textField_State);
		textField_State.setColumns(10);
		
		textField_Zip = new JTextField();
		textField_Zip.setBounds(130, 205, 86, 20);
		contentPane.add(textField_Zip);
		textField_Zip.setColumns(10);
		
		textArea_response = new TextArea();
		textArea_response.setBounds(10, 284, 411, 76);
		contentPane.add(textArea_response);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String First_Name=textField_FirstName.getText();
				String Last_Name=textField_LastName.getText();
				String Student_id=textField_Student_ID.getText();
				String Address=textField_Address.getText();
				String city=textField_City.getText();
				String state=textField_State.getText();
				String Zip=textField_Zip.getText();
				
				uc=new UniversityClient();
				 try {
					 
					 System.out.println("Student: "+Student_id);
						System.out.println("first name: "+First_Name);
						TMReceive= uc.Create_Student(First_Name,Last_Name,Student_id,Address,city,state,Zip);
						String result = TMReceive.getText();
						System.out.println(result);
						String[] array = result.split("\n");
						System.out.println("result"+TMReceive.getText());
						System.out.println("request id "+ array[0]);
						System.out.println("response "+ array[1]);
						CreateStudent.textArea_response.setText("RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);

					
				 }
				 catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 	textField_FirstName.setText("");
					textField_LastName.setText("");
					textField_Student_ID.setText("");
					textField_Address.setText("");
					textField_City.setText("");
					textField_State.setText("");
					textField_Zip.setText("");
			
			}
		});
		btnSubmit.setBounds(322, 246, 99, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		btnExit.setBounds(322, 366, 99, 23);
		contentPane.add(btnExit);
		
		JLabel lblResponse = new JLabel("Status:");
		lblResponse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResponse.setBounds(10, 249, 86, 14);
		contentPane.add(lblResponse);
		
		
	}
}
