package GUI;

import URS.*;

import java.awt.Font;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

public class UnenrollStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_StudentID;
	private JTextField textField_CourseName;
	private JTextField textField_CourseSection;
	private JButton btnExit;
	private TextArea textArea_Response;
	public TextMessage TMReceive;
	public UniversityClient uc;
	public String response, array[];

	
	public UnenrollStudent() {
		setTitle("Unenroll Student");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 416, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterStudentId = new JLabel("Enter Student ID:");
		lblEnterStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterStudentId.setBounds(10, 11, 117, 14);
		contentPane.add(lblEnterStudentId);
		
		JLabel lblEnterCourseId = new JLabel("Enter Course Name:");
		lblEnterCourseId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterCourseId.setBounds(10, 48, 151, 14);
		contentPane.add(lblEnterCourseId);
		
		JLabel lblEnterCourseSection = new JLabel("Enter Course Section:");
		lblEnterCourseSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterCourseSection.setBounds(10, 91, 133, 14);
		contentPane.add(lblEnterCourseSection);
		
		JButton btnEnroll = new JButton("Unenroll Student");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String StudentID = textField_StudentID.getText();
				String CourseName = textField_CourseName.getText();
				String CourseSection = textField_CourseSection.getText();
				
				textArea_Response.setText("");
				uc = new UniversityClient();
				if((!StudentID.equals("")) && (!CourseName.equals("")) && !(CourseSection.equals(""))){
				try {					
					TMReceive = uc.Unenroll_Student(StudentID, CourseName, CourseSection);
					response = TMReceive.getText();
					array= response.split("\n");
					textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
				} 
				catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}	
				else{
					textArea_Response.setText("Fill in all values");
				}
				
				
				
			}
		});
		btnEnroll.setBounds(171, 136, 181, 23);
		contentPane.add(btnEnroll);
		
		JLabel lblStatus = new JLabel("Response:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 205, 117, 14);
		contentPane.add(lblStatus);
		
		textField_StudentID = new JTextField();
		textField_StudentID.setBounds(171, 8, 181, 20);
		contentPane.add(textField_StudentID);
		textField_StudentID.setColumns(10);
		
		textField_CourseName = new JTextField();
		textField_CourseName.setBounds(171, 45, 181, 20);
		contentPane.add(textField_CourseName);
		textField_CourseName.setColumns(10);
		
		textField_CourseSection = new JTextField();
		textField_CourseSection.setBounds(171, 89, 181, 20);
		contentPane.add(textField_CourseSection);
		textField_CourseSection.setColumns(10);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(263, 313, 89, 23);
		contentPane.add(btnExit);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 220, 341, 87);
		contentPane.add(textArea_Response);
	}


}
