package GUI;



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

import URS.UniversityClient;

public class EnrollStudent extends JFrame {

	private JPanel contentPane;
	private static JTextField id;
	private static JTextField name;
	private static JTextField section;
	private TextArea textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];

	
	public EnrollStudent() {
		setTitle("Enroll Student");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		lblEnterCourseId.setBounds(10, 47, 133, 14);
		contentPane.add(lblEnterCourseId);
		
		JLabel lblEnterCourseSection = new JLabel("Enter Course Section:");
		lblEnterCourseSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterCourseSection.setBounds(10, 91, 133, 14);
		contentPane.add(lblEnterCourseSection);
		
		JButton btnEnroll = new JButton("Enroll Student");
		btnEnroll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				
				String studID=EnrollStudent.id.getText();
				String Name=EnrollStudent.name.getText();
				String Section=EnrollStudent.section.getText();
				 uc = new UniversityClient();
				 try {
					TMReceive = uc.Enroll_Student(studID,Name,Section);
					response = TMReceive.getText();
					array=response.split("\n");
										
					textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);										
				 } catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}																							
			}
		});
		
		btnEnroll.setBounds(171, 136, 153, 23);
		contentPane.add(btnEnroll);
		
		JLabel lblStatus = new JLabel("Response:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 185, 117, 14);
		contentPane.add(lblStatus);
		
		id = new JTextField();
		id.setBounds(171, 8, 153, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(171, 45, 153, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		section = new JTextField();
		section.setBounds(171, 88, 153, 20);
		contentPane.add(section);
		section.setColumns(10);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 213, 393, 38);
		contentPane.add(textArea_Response);
		textArea_Response.setColumns(10);
	}

}
