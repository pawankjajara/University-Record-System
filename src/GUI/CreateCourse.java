package GUI;
import URS.*;

import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.awt.TextArea;



public class CreateCourse extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_CourseName;
	private JTextField textField_CourseSection;
	private JTextField textField_InstructorID;
	private JTextField textField_Location;
	private JTextField textField_CourseUnits;
	private JLabel lblResponse;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String array[],response;
	private TextArea textArea_Response;

		/**
	 * Create the frame.
	 */
	public CreateCourse() {
		setTitle("Create Course");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 502, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseName.setBounds(20, 32, 123, 14);
		contentPane.add(lblCourseName);
		
		JLabel lblCourseSection = new JLabel("Course Section:");
		lblCourseSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseSection.setBounds(20, 72, 101, 14);
		contentPane.add(lblCourseSection);
		
		JLabel lblCourseInstructor = new JLabel("Instructor ID:");
		lblCourseInstructor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseInstructor.setBounds(20, 119, 123, 20);
		contentPane.add(lblCourseInstructor);
		
		JLabel lblCourseDays = new JLabel("Location:");
		lblCourseDays.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseDays.setBounds(20, 164, 113, 14);
		contentPane.add(lblCourseDays);
		
		JLabel lblStartTime = new JLabel("e.g. ENGR 489 MWF 1000-1200");
		lblStartTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStartTime.setBounds(186, 203, 211, 14);
		contentPane.add(lblStartTime);
		
		JLabel lblCourseCreditUnits = new JLabel("Course Units:");
		lblCourseCreditUnits.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseCreditUnits.setBounds(20, 246, 136, 14);
		contentPane.add(lblCourseCreditUnits);
		
		textField_CourseName = new JTextField();
		textField_CourseName.setBounds(186, 30, 272, 20);
		contentPane.add(textField_CourseName);
		textField_CourseName.setColumns(10);
		
		textField_CourseSection = new JTextField();
		textField_CourseSection.setBounds(186, 70, 86, 20);
		contentPane.add(textField_CourseSection);
		textField_CourseSection.setColumns(10);
		
		textField_InstructorID = new JTextField();
		textField_InstructorID.setBounds(186, 120, 211, 20);
		contentPane.add(textField_InstructorID);
		textField_InstructorID.setColumns(10);
		
		textField_Location = new JTextField();
		textField_Location.setBounds(186, 162, 272, 20);
		contentPane.add(textField_Location);
		textField_Location.setColumns(10);
		
		textField_CourseUnits = new JTextField();
		textField_CourseUnits.setBounds(186, 244, 86, 20);
		contentPane.add(textField_CourseUnits);
		textField_CourseUnits.setColumns(10);
		
				
		lblResponse = new JLabel("Status:");
		lblResponse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResponse.setBounds(20, 292, 123, 14);
		contentPane.add(lblResponse);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(9, 314, 442, 74);
		contentPane.add(textArea_Response);
	
	
	//JButton btnSubmit = new JButton("Create Course");
		JButton btnSubmitCourse = new JButton("Create Course");
	btnSubmitCourse.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent arg0) 
		{	
			String course_name = textField_CourseName.getText();
			String course_section = textField_CourseSection.getText();
			String ins_id = textField_InstructorID.getText();
			String location = textField_Location.getText();
			String units = textField_CourseUnits.getText();
			
			uc=new UniversityClient();
			try{
				System.out.println("course name: "+course_name);
				if( (!course_name.equals("")) && (!course_section.equals("")) && (!ins_id.equals("")) && (!location.equals("")) && (!units.equals("")) ){
				TMReceive= uc.Create_Course(course_name,course_section,ins_id,location,units);
				response = TMReceive.getText();
				System.out.println(response);
				array = response.split("\n");
				System.out.println("result"+TMReceive.getText());
				System.out.println("request id "+ array[0]);
				System.out.println("response "+ array[1]);
				textArea_Response.setText("RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
				textField_CourseName.setText("");
				textField_CourseSection.setText("");
				textField_InstructorID.setText("");
				textField_Location.setText("");
				textField_CourseUnits.setText("");
			//reply.setText(array[0]);
				}
				else{
					textArea_Response.setText("Fill in all fields with data");
				}
			}
			 catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		
	});
	
	btnSubmitCourse.setBounds(333, 246, 123, 23);
	contentPane.add(btnSubmitCourse);
	
	//btnSubmitCourse.setBounds(327, 361, 86, 23);
	//contentPane.add(btnSubmitCourse);
	
	JButton btnExit = new JButton("Exit");
	btnExit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	});
	btnExit.setBounds(333, 428, 123, 23);
	contentPane.add(btnExit);
	
	
}
		  
}


