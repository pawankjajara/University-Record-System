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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import URS.UniversityClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindCoursesByInstructor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_NoOfCourses;
	private JTextField textField_InstructorID;
	private JTable table;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];
	public TextArea textArea_Response;
	public TextArea textArea_CourseNamesSection;

	public FindCoursesByInstructor() {
		setTitle("Find Courses By Instructor");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 554, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterInstructorId = new JLabel("Enter Instructor ID:");
		lblEnterInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterInstructorId.setBounds(10, 23, 137, 14);
		contentPane.add(lblEnterInstructorId);
		
		textField_NoOfCourses = new JTextField();
		textField_NoOfCourses.setBounds(163, 133, 192, 20);
		contentPane.add(textField_NoOfCourses);
		textField_NoOfCourses.setColumns(10);
		
		JButton btnSubmit = new JButton("Search Courses");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//Write your code for this button click
				String InstructorID = textField_InstructorID.getText();
				uc = new UniversityClient();
				try {
				TMReceive=uc.Find_Courses_By_Instructor(InstructorID);
				response = TMReceive.getText();
				array= response.split("\n");
				
				if(array.length>2){
				textField_NoOfCourses.setText(array[2]);
				textArea_CourseNamesSection.setText(array[3]);
				textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
				for(int i=4;i<array.length;i++){
					textArea_CourseNamesSection.append("\n"+array[i]);
				}
				}
				else{
					textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
				}
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnSubmit.setBounds(200, 63, 155, 23);
		contentPane.add(btnSubmit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 101, 525, 5);
		contentPane.add(separator);
		
		JLabel lblNumberOfCourses = new JLabel("Number Of Courses:");
		lblNumberOfCourses.setBounds(10, 135, 127, 14);
		lblNumberOfCourses.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNumberOfCourses);
		
		JLabel lblCourseName = new JLabel("Course Name(s) & Section(s):");
		lblCourseName.setBounds(10, 169, 214, 14);
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblCourseName);
		
		textField_InstructorID = new JTextField();
		textField_InstructorID.setBounds(161, 21, 194, 20);
		contentPane.add(textField_InstructorID);
		textField_InstructorID.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBounds(389, 428, 118, 23);
		contentPane.add(btnOk);
		
		textArea_CourseNamesSection = new TextArea();
		textArea_CourseNamesSection.setBounds(10, 189, 497, 116);
		contentPane.add(textArea_CourseNamesSection);
		
		table = new JTable();
		table.setBounds(84, 278, 145, -57);
		contentPane.add(table);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 327, 77, 14);
		contentPane.add(lblStatus);
		
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 348, 497, 74);
		contentPane.add(textArea_Response);

	}
}
