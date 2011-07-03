package GUI;

import java.awt.Font;
import java.awt.TextArea;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import URS.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RetrieveCourse extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel contentPane;
	private JTextField textField_CourseName;
	private JTextField textField_CourseSection;
	private JTextField textField_CourseLocation;
	private JTextField textField_CourseInstructor;
	private JTextField textField_CourseCreditUnits;
	public UniversityClient uc;
	String array[],response;
	public TextMessage TMReceive;
	public TextArea textArea_Response;
	public TextArea textArea_EnrolledStudents;

		/**
	 * Create the frame.
	 */
	public RetrieveCourse() {
		setTitle("Retrieve Course");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 561, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Status:");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseName.setBounds(20, 385, 136, 14);
		contentPane.add(lblCourseName);
		
		JLabel lblCourseId = new JLabel("Enter Course Name:");
		lblCourseId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseId.setBounds(20, 11, 123, 14);
		contentPane.add(lblCourseId);
		
		JLabel lblCourseSection = new JLabel("Enter Course Section:");
		lblCourseSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseSection.setBounds(20, 36, 136, 14);
		contentPane.add(lblCourseSection);
		
		JLabel lblCourseLocation = new JLabel("Course Location:");
		lblCourseLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseLocation.setBounds(20, 219, 123, 14);
		contentPane.add(lblCourseLocation);
		
		JLabel lblCourseInstructor = new JLabel("Course Instructor:");
		lblCourseInstructor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseInstructor.setBounds(20, 170, 123, 20);
		contentPane.add(lblCourseInstructor);
		
		JLabel lblCourseCreditUnits = new JLabel("Course Credit Units:");
		lblCourseCreditUnits.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseCreditUnits.setBounds(20, 133, 136, 14);
		contentPane.add(lblCourseCreditUnits);
		
		textField_CourseName = new JTextField();
		textField_CourseName.setBounds(186, 9, 86, 20);
		contentPane.add(textField_CourseName);
		textField_CourseName.setColumns(10);
		
		textField_CourseSection = new JTextField();
		textField_CourseSection.setBounds(186, 36, 86, 20);
		contentPane.add(textField_CourseSection);
		textField_CourseSection.setColumns(10);
		
		textField_CourseLocation = new JTextField();
		textField_CourseLocation.setBounds(186, 217, 211, 20);
		contentPane.add(textField_CourseLocation);
		textField_CourseLocation.setColumns(10);
		
		textField_CourseInstructor = new JTextField();
		textField_CourseInstructor.setBounds(186, 171, 211, 20);
		contentPane.add(textField_CourseInstructor);
		textField_CourseInstructor.setColumns(10);
		
		textField_CourseCreditUnits = new JTextField();
		textField_CourseCreditUnits.setBounds(186, 131, 86, 20);
		contentPane.add(textField_CourseCreditUnits);
		textField_CourseCreditUnits.setColumns(10);
		
		JButton btnSubmitCourse = new JButton("OK");
		btnSubmitCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSubmitCourse.setBounds(422, 527, 96, 23);
		contentPane.add(btnSubmitCourse);
		
		JButton btnSubmitQuery = new JButton("Submit Query");
		btnSubmitQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Course_Name = textField_CourseName.getText();
				String Course_Section= textField_CourseSection.getText();
				uc= new UniversityClient();
				try{
					System.out.println("course name: "+Course_Name);
					if( (!Course_Name.equals("")) && (!Course_Section.equals(""))){
					
					TMReceive= uc.Get_Course_Units(Course_Name, Course_Section);
					response = TMReceive.getText();
					array = response.split("\n");
					textField_CourseCreditUnits.setText(array[3]);
					textArea_Response.setText("RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					
					TMReceive = uc.Get_Course_Instructor(Course_Name, Course_Section);
					response = TMReceive.getText();
					array = response.split("\n");
					textField_CourseInstructor.setText(array[3]);
					textArea_Response.append("\n"+"RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					
					TMReceive = uc.Get_Location(Course_Name, Course_Section);
					response = TMReceive.getText();
					array = response.split("\n");
					textField_CourseLocation.setText(array[3]);
					textArea_Response.append("\n"+"RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					
					TMReceive = uc.Get_Students(Course_Name, Course_Section);
					response = TMReceive.getText();
					array = response.split("\n");
					textArea_EnrolledStudents.setText(array[2]);
					textArea_Response.append("\n"+"RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
										
					
					}
					else{
						textArea_Response.setText("Enter Values in all Fields !!");
					}
				}
				catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
				
				
				
			}
		});
		btnSubmitQuery.setBounds(390, 33, 123, 23);
		contentPane.add(btnSubmitQuery);
		
		JLabel lblResultsForYour = new JLabel("RESULTS FOR YOUR QUERY:");
		lblResultsForYour.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResultsForYour.setBounds(109, 89, 252, 14);
		contentPane.add(lblResultsForYour);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 534, 2);
		contentPane.add(separator);
		
		JLabel lblEnrolledStudents = new JLabel("Enrolled Students:");
		lblEnrolledStudents.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnrolledStudents.setBounds(20, 259, 281, 14);
		contentPane.add(lblEnrolledStudents);
		
		textArea_EnrolledStudents = new TextArea();
		textArea_EnrolledStudents.setBounds(20, 279, 377, 100);
		contentPane.add(textArea_EnrolledStudents);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(17, 402, 380, 118);
		contentPane.add(textArea_Response);
	}


}
