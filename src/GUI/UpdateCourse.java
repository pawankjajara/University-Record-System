package GUI;
import java.awt.Font;


import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;

import URS.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCourse extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_CourseName;
	private JTextField textField_CourseSection;
	private JTextField textField_NewCourseName;
	private JTextField textField_NewCourseSection;
	private JTextField textField_NewCourseUnits;
	private JTextField textField_NewCourseLocation;
	private JSeparator separator;
	private JTextField textField_NewInstructorName;
	private JLabel lblNewProfessorName;
	private JLabel lblResponse;
	private TextArea textArea_Response;;
	public UniversityClient uc;
	String array[],response;
	public TextMessage TMReceive;

		/**
	 * Create the frame.
	 */
	public UpdateCourse() {
		setTitle("Update Course");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 577, 598);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Current Course Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseName.setBounds(20, 32, 143, 14);
		contentPane.add(lblCourseName);
		
		JLabel lblCourseSection = new JLabel("Current Course Section:");
		lblCourseSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseSection.setBounds(20, 75, 184, 14);
		contentPane.add(lblCourseSection);
		
		JLabel lblCourseLocation = new JLabel("New Course Name:");
		lblCourseLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseLocation.setBounds(20, 145, 157, 20);
		contentPane.add(lblCourseLocation);
		
		JLabel lblCourseInstructor = new JLabel("New Course Section:");
		lblCourseInstructor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseInstructor.setBounds(20, 176, 163, 20);
		contentPane.add(lblCourseInstructor);
		
		JLabel lblEndTime = new JLabel("New Course Location:");
		lblEndTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEndTime.setBounds(20, 316, 157, 14);
		contentPane.add(lblEndTime);
		
		JLabel lblCourseCreditUnits = new JLabel("New Course Units:");
		lblCourseCreditUnits.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseCreditUnits.setBounds(20, 265, 136, 14);
		contentPane.add(lblCourseCreditUnits);
		
		textField_CourseName = new JTextField();
		textField_CourseName.setBounds(224, 30, 211, 20);
		contentPane.add(textField_CourseName);
		textField_CourseName.setColumns(10);
		
		textField_CourseSection = new JTextField();
		textField_CourseSection.setBounds(224, 73, 86, 20);
		contentPane.add(textField_CourseSection);
		textField_CourseSection.setColumns(10);
		
		textField_NewCourseName = new JTextField();
		textField_NewCourseName.setBounds(202, 146, 211, 20);
		contentPane.add(textField_NewCourseName);
		textField_NewCourseName.setColumns(10);
		
		textField_NewCourseSection = new JTextField();
		textField_NewCourseSection.setBounds(202, 177, 86, 20);
		contentPane.add(textField_NewCourseSection);
		textField_NewCourseSection.setColumns(10);
		
		textField_NewCourseUnits = new JTextField();
		textField_NewCourseUnits.setBounds(202, 263, 86, 20);
		contentPane.add(textField_NewCourseUnits);
		textField_NewCourseUnits.setColumns(10);
		
		textField_NewCourseLocation = new JTextField();
		textField_NewCourseLocation.setBounds(202, 314, 211, 20);
		contentPane.add(textField_NewCourseLocation);
		textField_NewCourseLocation.setColumns(10);
		
		JButton btnSubmitCourse = new JButton("Submit Update");
		btnSubmitCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Course_Name= textField_CourseName.getText();
				String Course_Section = textField_CourseSection.getText();
				String New_Course_Name = textField_NewCourseName.getText();
				String New_Course_Section= textField_NewCourseSection.getText();
				String New_Course_Units= textField_NewCourseUnits.getText();
				String New_Course_Location = textField_NewCourseLocation.getText();
				String New_Course_Instructor_ID = textField_NewInstructorName.getText();
				
				uc= new UniversityClient();
				try{
					System.out.println("course name: "+Course_Name);
					//if( (!Course_Name.equals("")) && (!Course_Section.equals("")) && (!New_Course_Section.equals("")) && (!New_Course_Name.equals("")) && (!New_Course_Units.equals("")) && (!New_Course_Location.equals("")) && (!New_Course_Instructor.equals("")) ){
						
					if(!New_Course_Name.equals(null)){
						TMReceive=uc.Set_Course_Name(Course_Name, Course_Section, New_Course_Name);
						response = TMReceive.getText();
						System.out.println("Response: "+response);
						array= response.split("\n");
						textArea_Response.setText("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						
						if(!New_Course_Section.equals(null)){
						TMReceive=uc.Set_Course_Section(Course_Name, Course_Section, New_Course_Section);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("\n"+"RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						
						if(!New_Course_Units.equals(null)){
						TMReceive=uc.Set_Course_Units(Course_Name, Course_Section, New_Course_Units);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("\n"+"RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						
						if(!New_Course_Instructor_ID.equals(null)){
						TMReceive=uc.Set_Course_Instructor(Course_Name, Course_Section, New_Course_Instructor_ID);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("\n"+"RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						
						if(!New_Course_Location.equals(null)){
						TMReceive=uc.Set_Location(New_Course_Location,Course_Name,Course_Section);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("\n"+"RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						
		
					
				}
				
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnSubmitCourse.setBounds(262, 359, 151, 23);
		contentPane.add(btnSubmitCourse);
		
		separator = new JSeparator();
		separator.setBounds(0, 132, 559, 2);
		contentPane.add(separator);
		
		textField_NewInstructorName = new JTextField();
		textField_NewInstructorName.setBounds(202, 216, 211, 20);
		contentPane.add(textField_NewInstructorName);
		textField_NewInstructorName.setColumns(10);
		
		lblNewProfessorName = new JLabel("New Instructor Name:");
		lblNewProfessorName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewProfessorName.setBounds(20, 218, 157, 20);
		contentPane.add(lblNewProfessorName);
		
		lblResponse = new JLabel("Status:");
		lblResponse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResponse.setBounds(20, 404, 123, 14);
		contentPane.add(lblResponse);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(20, 430, 502, 104);
		contentPane.add(textArea_Response);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(425, 359, 89, 23);
		contentPane.add(btnExit);
	}
}
