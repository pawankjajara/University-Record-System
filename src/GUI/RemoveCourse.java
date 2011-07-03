package GUI;
import URS.*;

import java.awt.Font;

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
import java.awt.TextArea;



public class RemoveCourse extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField textField_CourseName;
	private JTextField textField_Course_Section;
	public TextArea textArea_Response;
	public UniversityClient uc;
	String array[],response;
	private JPanel contentPane;
	public TextMessage TMReceive;
	private JTextField textField_ForceUnenroll;

	public RemoveCourse() {
		setTitle("Remove Course");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterCourseId = new JLabel("Enter Course Name:");
		lblEnterCourseId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterCourseId.setBounds(10, 27, 131, 14);
		contentPane.add(lblEnterCourseId);
		
		JLabel lblEnterCourseSection = new JLabel("Enter Course Section:");
		lblEnterCourseSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterCourseSection.setBounds(10, 66, 144, 14);
		contentPane.add(lblEnterCourseSection);
		
		textField_CourseName = new JTextField();
		textField_CourseName.setBounds(153, 24, 282, 20);
		contentPane.add(textField_CourseName);
		textField_CourseName.setColumns(10);
		
		textField_Course_Section = new JTextField();
		textField_Course_Section.setBounds(153, 64, 86, 20);
		contentPane.add(textField_Course_Section);
		textField_Course_Section.setColumns(10);
		
		JLabel lblOperationStatus = new JLabel("Status:");
		lblOperationStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOperationStatus.setBounds(10, 231, 131, 14);
		contentPane.add(lblOperationStatus);
		
			
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 211, 494, 9);
		contentPane.add(separator);
		
		JLabel lblForceUnenroll = new JLabel("Force Unenroll:");
		lblForceUnenroll.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblForceUnenroll.setBounds(10, 116, 118, 14);
		contentPane.add(lblForceUnenroll);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 251, 455, 96);
		contentPane.add(textArea_Response);
		
		JLabel lblyesno = new JLabel("1=Yes, 0=No");
		lblyesno.setBounds(153, 144, 131, 14);
		contentPane.add(lblyesno);
		
		JButton btnDeleteCourse = new JButton("Remove Course");
		btnDeleteCourse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{	
				String course_name = textField_CourseName.getText();
				String course_section = textField_Course_Section.getText();
				String unenroll = textField_ForceUnenroll.getText();
				System.out.println("Pawan Here..."+course_name+" "+course_section+" "+unenroll);
				uc=new UniversityClient();
				try{
					System.out.println("course name: "+course_name);
					if( (!course_name.equals("")) && (!course_section.equals("")) && (!unenroll.equals(""))){
					TMReceive= uc.Remove_Course(course_name,course_section,unenroll);
					response = TMReceive.getText();
					array = response.split("\n");
					System.out.println("result"+TMReceive.getText());
					System.out.println("request id "+ array[0]);
					System.out.println("response "+ array[1]);
					textArea_Response.setText("RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
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
		btnDeleteCourse.setBounds(321, 173, 144, 23);
		contentPane.add(btnDeleteCourse);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(348, 378, 126, 23);
		contentPane.add(btnExit);
		
		textField_ForceUnenroll = new JTextField();
		textField_ForceUnenroll.setBounds(153, 114, 86, 20);
		contentPane.add(textField_ForceUnenroll);
		textField_ForceUnenroll.setColumns(10);
		
	}
}
