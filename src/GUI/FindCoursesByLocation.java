package GUI;
//import Person.*;
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

import URS.UniversityClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindCoursesByLocation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Location;
	private JTextField textField_NoOfCourses;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];
	public TextArea textArea_Response;
	public TextArea textArea_CourseNamesSections;

	
	public FindCoursesByLocation() {
		setTitle("Courses By Location");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 526, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterLocation = new JLabel("Enter Location:");
		lblEnterLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterLocation.setBounds(10, 23, 123, 14);
		contentPane.add(lblEnterLocation);
		
		textField_Location = new JTextField();
		textField_Location.setBounds(163, 20, 338, 20);
		contentPane.add(textField_Location);
		textField_Location.setColumns(10);
		
		JButton btnSearchCourses = new JButton("Search Courses");
		btnSearchCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Write Code for Button Even here...
				String LocationID = textField_Location.getText();
				uc = new UniversityClient();
				try {
				TMReceive=uc.Find_Courses_By_Location(LocationID);
				response = TMReceive.getText();
				array= response.split("\n");
				
				if(array.length>2){
				textField_NoOfCourses.setText(array[1]);
				textArea_CourseNamesSections.setText(array[2]);
				textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
				for(int i=4;i<array.length;i++){
					textArea_CourseNamesSections.append("\n"+array[i]);
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
		btnSearchCourses.setBounds(343, 63, 158, 23);
		contentPane.add(btnSearchCourses);
		
		JLabel lblNumberOfCourses = new JLabel("Number Of Courses:");
		lblNumberOfCourses.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumberOfCourses.setBounds(10, 107, 143, 14);
		contentPane.add(lblNumberOfCourses);
		
		JLabel lblCourseNames = new JLabel("Course Name(s) & Section(s):");
		lblCourseNames.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseNames.setBounds(10, 146, 239, 14);
		contentPane.add(lblCourseNames);
		
		textField_NoOfCourses = new JTextField();
		textField_NoOfCourses.setBounds(163, 104, 86, 20);
		contentPane.add(textField_NoOfCourses);
		textField_NoOfCourses.setColumns(10);
		
		textArea_CourseNamesSections = new TextArea();
		textArea_CourseNamesSections.setBounds(10, 175, 491, 140);
		contentPane.add(textArea_CourseNamesSections);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBounds(412, 491, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblEgEngr = new JLabel("e.g. ENGR 489 MWF 1000-1200");
		lblEgEngr.setBounds(163, 46, 193, 14);
		contentPane.add(lblEgEngr);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 332, 64, 14);
		contentPane.add(lblStatus);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 354, 491, 97);
		contentPane.add(textArea_Response);
	}
}
