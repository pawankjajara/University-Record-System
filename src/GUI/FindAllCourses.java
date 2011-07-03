package GUI;
//import Person.*;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import java.awt.Font;
import java.awt.TextArea;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import URS.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindAllCourses extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_NoOfCourse;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];
	public TextArea textArea_CoursesSections;
	public TextArea textArea_Response ;
	
	
	public FindAllCourses() {
		setTitle("Find All Courses");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 552, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumberOfCourses = new JLabel("Number Of Courses:");
		lblNumberOfCourses.setBounds(10, 61, 127, 14);
		lblNumberOfCourses.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblNumberOfCourses);
		
		JLabel lblCourseName = new JLabel("Course Name(s) & Section(s):");
		lblCourseName.setBounds(10, 86, 214, 14);
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblCourseName);
		
		textField_NoOfCourse = new JTextField();
		textField_NoOfCourse.setBounds(147, 59, 127, 20);
		contentPane.add(textField_NoOfCourse);
		textField_NoOfCourse.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBounds(373, 414, 118, 23);
		contentPane.add(btnOk);
		
		textArea_CoursesSections = new TextArea();
		textArea_CoursesSections.setText("");
		textArea_CoursesSections.setBounds(10, 106, 481, 118);
		contentPane.add(textArea_CoursesSections);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 279, 81, 14);
		contentPane.add(lblStatus);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 298, 481, 99);
		contentPane.add(textArea_Response);
		
		JButton btnSearchCourses = new JButton("Search Courses");
		btnSearchCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				uc = new UniversityClient();
				
				try {
					TMReceive=uc.Find_All_Courses();
					response = TMReceive.getText();
					array= response.split("\n");
					textField_NoOfCourse.setText(array[2]);
					textArea_CoursesSections.setText(array[3]);
					textArea_Response.setText("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					
					for(int i=4;i<array.length;i++){
						textArea_CoursesSections.append("\n"+array[i]);
					}											
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 				
			}
		});
		btnSearchCourses.setBounds(10, 11, 264, 37);
		contentPane.add(btnSearchCourses);
	}
}
