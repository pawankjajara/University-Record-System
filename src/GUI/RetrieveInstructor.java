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

public class RetrieveInstructor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField id;
	private static JTextField fname;
	private static JTextField lname;
	private static JTextField add;
	private static JTextField city;
	private static JTextField state;
	private static JTextField zip;
	private JLabel lblRequestedInformationAbout;
	private JButton btnOk;
	private static TextArea textArea_Response;
	private JLabel lblNoOfCourses;
	private static JTextField no_courses;
	private JLabel lblCourseNamesAnd;
	private static TextArea textArea;

	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];
	/**
	 * Launch the application.
	 */
	public RetrieveInstructor() {
		setTitle("Retrieve Instructor");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 100, 526, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Enter Instructor ID:");
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructorId.setBounds(10, 11, 132, 14);
		contentPane.add(lblInstructorId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(10, 68, 110, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(10, 93, 110, 14);
		contentPane.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 118, 110, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 143, 110, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblState.setBounds(10, 168, 110, 14);
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblZipCode.setBounds(10, 193, 110, 14);
		contentPane.add(lblZipCode);
		
		JLabel lblFromHours_1 = new JLabel("Response:");
		lblFromHours_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFromHours_1.setBounds(10, 427, 110, 14);
		contentPane.add(lblFromHours_1);
		
		id = new JTextField();
		id.setBounds(152, 9, 153, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		fname = new JTextField();
		fname.setBounds(130, 66, 175, 20);
		contentPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(130, 91, 175, 20);
		contentPane.add(lname);
		lname.setColumns(10);
		
		add = new JTextField();
		add.setBounds(130, 118, 283, 20);
		contentPane.add(add);
		add.setColumns(10);
		
		city = new JTextField();
		city.setBounds(130, 143, 116, 18);
		contentPane.add(city);
		city.setColumns(10);
		
		state = new JTextField();
		state.setBounds(130, 166, 116, 20);
		contentPane.add(state);
		state.setColumns(10);
		
		zip = new JTextField();
		zip.setBounds(130, 191, 116, 20);
		contentPane.add(zip);
		zip.setColumns(10);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 452, 431, 50);
		contentPane.add(textArea_Response);
		textArea_Response.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(327, 8, 86, 23);
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String instID=RetrieveInstructor.id.getText();
				 uc = new UniversityClient();
				 try {
					TMReceive = uc.Get_First_Name(instID);
					response = TMReceive.getText();
					array= response.split("\n");
					if(array.length<3)
						textArea_Response.setText("RequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);						
					else
					{
						fname.setText(array[3]);
						textArea_Response.setText("RequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_Last_Name(instID);
					response = TMReceive.getText();
					array = response.split("\n");
					
					if(array.length<3)
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);										
					else
					{						
						lname.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_Address(instID);
					response = TMReceive.getText();
					array=response.split("\n");
					if(array.length<3)
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						add.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);						
					}
					
					TMReceive = uc.Get_City(instID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);										
					else
					{
						city.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_State(instID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)					
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						state.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					TMReceive = uc.Get_Zip(instID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)				
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						zip.setText(array[3]);
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
														
					TMReceive = uc.Get_Courses(instID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)					
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						int no_param=Integer.parseInt(array[2]);
						if(no_param>0)
						{
							no_courses.setText(array[2]);
							textArea.setText(array[3]);
							for(int i=4;i<array.length;i++)
							{
								textArea.append("\n"+array[i]);								
							}
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						else
						{
							no_courses.setText(array[2]);
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
					}					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}											
			}
		});
		
		contentPane.add(btnSubmit);
		
		lblRequestedInformationAbout = new JLabel("Requested Information about Instructor:");
		lblRequestedInformationAbout.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRequestedInformationAbout.setBounds(10, 43, 295, 14);
		contentPane.add(lblRequestedInformationAbout);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateInstructor ci = new CreateInstructor();
				dispose();
			}
		});
		btnOk.setBounds(360, 518, 89, 23);
		contentPane.add(btnOk);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 512, 2);
		contentPane.add(separator);
		
		lblNoOfCourses = new JLabel("No. Of Courses:");
		lblNoOfCourses.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNoOfCourses.setBounds(10, 250, 110, 20);
		contentPane.add(lblNoOfCourses);
		
		no_courses = new JTextField();
		no_courses.setBounds(130, 253, 86, 20);
		contentPane.add(no_courses);
		no_courses.setColumns(10);
		
		lblCourseNamesAnd = new JLabel("Course Name(s) and Section(s):");
		lblCourseNamesAnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseNamesAnd.setBounds(10, 298, 261, 14);
		contentPane.add(lblCourseNamesAnd);
		
		textArea = new TextArea();
		textArea.setBounds(10, 327, 431, 83);
		contentPane.add(textArea);
	}
}
