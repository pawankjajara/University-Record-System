package GUI;
import URS.*;

import java.awt.Font;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class UpdateStudent extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_StudentID;
	private JTextField textField_New_FName;
	private JTextField textField_NLName;
	private JTextField textField_NCity;
	private JTextField textField_NState;
	private JTextField textField_NZip;
	private JLabel lblNewInstructorId;
	private JTextField textField_NStudent_ID;
	private JTextField textField_NAddress;
	private JSeparator separator;
	private JButton btnExit;
	private TextArea textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];

	/**
	 * Launch the application.
	 */
	public UpdateStudent() {
		setTitle("Update Student");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 100, 554, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Student ID:");
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructorId.setBounds(10, 11, 110, 14);
		contentPane.add(lblInstructorId);
		
		JLabel lblFirstName = new JLabel("New First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(10, 68, 110, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("New Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(10, 93, 110, 14);
		contentPane.add(lblLastName);
		
		JLabel lblAddress = new JLabel("New Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 124, 110, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("New City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 153, 110, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("New State:");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblState.setBounds(10, 178, 110, 14);
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("New Zip Code:");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblZipCode.setBounds(10, 203, 110, 14);
		contentPane.add(lblZipCode);
		
		JLabel lblFromHours_1 = new JLabel("Response:");
		lblFromHours_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFromHours_1.setBounds(10, 317, 110, 14);
		contentPane.add(lblFromHours_1);
		
		textField_StudentID = new JTextField();
		textField_StudentID.setBounds(130, 9, 161, 20);
		contentPane.add(textField_StudentID);
		textField_StudentID.setColumns(10);
		
		textField_New_FName = new JTextField();
		textField_New_FName.setBounds(130, 66, 159, 20);
		contentPane.add(textField_New_FName);
		textField_New_FName.setColumns(10);
		
		textField_NLName = new JTextField();
		textField_NLName.setBounds(130, 91, 159, 20);
		contentPane.add(textField_NLName);
		textField_NLName.setColumns(10);
		
		textField_NCity = new JTextField();
		textField_NCity.setBounds(130, 151, 86, 20);
		contentPane.add(textField_NCity);
		textField_NCity.setColumns(10);
		
		textField_NState = new JTextField();
		textField_NState.setBounds(130, 176, 86, 20);
		contentPane.add(textField_NState);
		textField_NState.setColumns(10);
		
		textField_NZip = new JTextField();
		textField_NZip.setBounds(130, 201, 86, 20);
		contentPane.add(textField_NZip);
		textField_NZip.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//University Client Object is called from this place
				String sid,nsid,fn,ln,ad,city,state,zip;
				
				sid=textField_StudentID.getText();
				nsid=textField_NStudent_ID.getText();
				fn=textField_New_FName.getText();
				ln=textField_NLName.getText();
				ad=textField_NAddress.getText();
				city=textField_NCity.getText();
				state=textField_NCity.getText();
				zip=textField_NZip.getText();
				System.out.println(sid+nsid+fn+ln+ad+city+state+zip);
				uc=new UniversityClient();
				try {
					
					if(!ad.equals(null)){
					TMReceive=uc.Set_Address(sid,ad);
					response = TMReceive.getText();
					System.out.println("Response: "+response);
					array= response.split("\n");
					textArea_Response.setText("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!fn.equals(null)){
					TMReceive=uc.Set_First_Name(sid, fn);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!ln.equals(null)){
					TMReceive=uc.Set_Last_Name(sid, ln);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!ad.equals(null)){
					TMReceive=uc.Set_Address(sid, ad);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!city.equals(null)){
					TMReceive=uc.Set_City(sid, city);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!state.equals(null)){
					TMReceive=uc.Set_State(sid, state);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!zip.equals(null)){
					TMReceive=uc.Set_Zip(sid, zip);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!nsid.equals(null)){
					TMReceive=uc.Set_ID(sid, nsid);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnSubmit.setBounds(378, 251, 86, 23);
		contentPane.add(btnSubmit);
		
		lblNewInstructorId = new JLabel("New Student ID:");
		lblNewInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewInstructorId.setBounds(10, 228, 132, 14);
		contentPane.add(lblNewInstructorId);
		
		textField_NStudent_ID = new JTextField();
		textField_NStudent_ID.setBounds(130, 226, 159, 20);
		contentPane.add(textField_NStudent_ID);
		textField_NStudent_ID.setColumns(10);
		
		textField_NAddress = new JTextField();
		textField_NAddress.setBounds(130, 122, 334, 20);
		contentPane.add(textField_NAddress);
		textField_NAddress.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(0, 296, 509, 10);
		contentPane.add(separator);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(375, 438, 89, 23);
		contentPane.add(btnExit);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 342, 462, 90);
		contentPane.add(textArea_Response);
	}
	

}
