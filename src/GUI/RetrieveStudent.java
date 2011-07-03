package GUI;
import URS.*;



import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RetrieveStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public static JTextField textField_Student_ID;
	public static JTextField textField_First_Name;
	public static JTextField textField_Last_Name;
	public static JTextField textField_Address;
	public static JTextField textField_City;
	public static JTextField textField_State;
	public static JTextField textField_Zip;
	public static JLabel lblRequestedInformationAbout;
	private JButton btnOk;
	private JLabel lblEnrolledUnits;
	public static JTextField textField_Enrolled_Units;
	public JLabel lblEnrolledCourses;
	public static TextArea textArea_Response;
	private TextArea textArea_Enrolled_Course;
	public String response, array[];
		

	public RetrieveStudent() {
		setTitle("Retrieve Student");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 100, 495, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Enter Student ID:");
		lblInstructorId.setBounds(10, 11, 132, 14);
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblInstructorId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 68, 110, 14);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(10, 93, 110, 14);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 118, 110, 14);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(10, 143, 110, 14);
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(10, 168, 110, 14);
		lblState.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setBounds(10, 193, 110, 14);
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblZipCode);
		
		JLabel lblFromHours_1 = new JLabel("Response:");
		lblFromHours_1.setBounds(10, 392, 110, 14);
		lblFromHours_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblFromHours_1);
		
		textField_Student_ID = new JTextField();
		textField_Student_ID.setBounds(152, 9, 153, 20);
		contentPane.add(textField_Student_ID);
		textField_Student_ID.setColumns(10);
		
		textField_First_Name = new JTextField();
		textField_First_Name.setBounds(130, 66, 175, 20);
		contentPane.add(textField_First_Name);
		textField_First_Name.setColumns(10);
		
		textField_Last_Name = new JTextField();
		textField_Last_Name.setBounds(130, 91, 175, 20);
		contentPane.add(textField_Last_Name);
		textField_Last_Name.setColumns(10);
		
		textField_Address = new JTextField();
		textField_Address.setBounds(130, 118, 283, 20);
		contentPane.add(textField_Address);
		textField_Address.setColumns(10);
		
		textField_City = new JTextField();
		textField_City.setBounds(130, 143, 116, 18);
		contentPane.add(textField_City);
		textField_City.setColumns(10);
		
		textField_State = new JTextField();
		textField_State.setBounds(130, 166, 116, 20);
		contentPane.add(textField_State);
		textField_State.setColumns(10);
		
		textField_Zip = new JTextField();
		textField_Zip.setBounds(130, 191, 116, 20);
		contentPane.add(textField_Zip);
		textField_Zip.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(327, 8, 86, 23);
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String studentID=RetrieveStudent.textField_Student_ID.getText();
				
				//Reset all the JTextFields to Null
				textField_First_Name.setText("");
				textField_Last_Name.setText("");
				textField_Address.setText("");
				textField_City.setText("");
				textField_State.setText("");
				textField_Zip.setText("");
				textField_Enrolled_Units.setText("");
				textArea_Enrolled_Course.setText("");
				textArea_Response.setText("");
				/////////////////////////////////////////////
				
				 uc = new UniversityClient();
				 try {
					TMReceive = uc.Get_First_Name(studentID);
					response = TMReceive.getText();
					array= response.split("\n");
					if(array.length<3)
						textArea_Response.setText("RequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);						
					else
					{
						textField_First_Name.setText(array[3]);
						textArea_Response.setText("RequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_Last_Name(studentID);
					response = TMReceive.getText();
					array = response.split("\n");
					
					if(array.length<3)
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);										
					else
					{						
						textField_Last_Name.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_Address(studentID);
					response = TMReceive.getText();
					array=response.split("\n");
					if(array.length<3)
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						textField_Address.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);						
					}
					
					TMReceive = uc.Get_City(studentID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);										
					else
					{
						textField_City.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_State(studentID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)					
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						textField_State.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_Zip(studentID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)				
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						textField_Zip.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_Enrolled_Units(studentID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)					
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						textField_Enrolled_Units.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						
					}
									
					TMReceive = uc.Get_Courses(studentID);
					response = TMReceive.getText();
					array=response.split("\n");
										
					if(array.length<3)					
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						int no_param=Integer.parseInt(array[2]);
						if(no_param>0)
						{
							textArea_Enrolled_Course.setText(array[3]);
							if(array.length>4){
								for(int i=4;i<array.length;i++){
								textArea_Enrolled_Course.append("\n"+array[i]);
								}
							}
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						else
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}											
			}
		});
		contentPane.add(btnSubmit);
		
		lblRequestedInformationAbout = new JLabel("Requested Information about Student:");
		lblRequestedInformationAbout.setBounds(10, 43, 295, 14);
		lblRequestedInformationAbout.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblRequestedInformationAbout);
		
		btnOk = new JButton("Exit");
		btnOk.setBounds(353, 528, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnOk);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 512, 2);
		contentPane.add(separator);
		
		lblEnrolledUnits = new JLabel("Enrolled Units:");
		lblEnrolledUnits.setBounds(10, 221, 110, 14);
		lblEnrolledUnits.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblEnrolledUnits);
		
		textField_Enrolled_Units = new JTextField();
		textField_Enrolled_Units.setBounds(130, 219, 116, 20);
		contentPane.add(textField_Enrolled_Units);
		textField_Enrolled_Units.setColumns(10);
		
		lblEnrolledCourses = new JLabel("Enrolled Courses:");
		lblEnrolledCourses.setBounds(10, 257, 132, 14);
		lblEnrolledCourses.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblEnrolledCourses);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 418, 432, 89);
		contentPane.add(textArea_Response);
		
		textArea_Enrolled_Course = new TextArea();
		textArea_Enrolled_Course.setBounds(10, 283, 430, 103);
		contentPane.add(textArea_Enrolled_Course);
		
		
	}
}
