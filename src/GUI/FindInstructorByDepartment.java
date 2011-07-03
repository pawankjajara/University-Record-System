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


import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import URS.UniversityClient;

public class FindInstructorByDepartment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField dept;
	private JTextField no_prof;
	private TextArea textArea_Response, textArea;
	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];
	
	public FindInstructorByDepartment() {
		setTitle("Find Instructor By Department");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 510, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterDepartmentName = new JLabel("Enter Department Name:");
		lblEnterDepartmentName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterDepartmentName.setBounds(10, 11, 178, 14);
		contentPane.add(lblEnterDepartmentName);
		
		dept = new JTextField();
		dept.setBounds(229, 9, 203, 20);
		contentPane.add(dept);
		dept.setColumns(10);
		
		textArea = new TextArea();
		textArea.setBounds(-6, 141, 438, 160);
		contentPane.add(textArea);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String Dept=FindInstructorByDepartment.dept.getText();
				 uc = new UniversityClient();
				 try {
					TMReceive = uc.Find_Instructors_By_Department(Dept);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)					
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						int no_param=Integer.parseInt(array[2]);
						if(no_param>0)
						{
							no_prof.setText(array[2]);
							textArea.setText(array[3]);
							for(int i=4;i<array.length;i++)
							{
								textArea.append("\n"+array[i]);								
							}
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						else
						{
							no_prof.setText(array[2]);
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
					}					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}											
			}
		});				
		btnSearch.setBounds(326, 40, 106, 23);
		contentPane.add(btnSearch);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 84, 480, 17);
		contentPane.add(separator);
		
		JLabel lblProfessorsInThis = new JLabel("Number of Professors in this Department:");
		lblProfessorsInThis.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProfessorsInThis.setBounds(10, 100, 284, 14);
		contentPane.add(lblProfessorsInThis);
		
		JButton btnOk = new JButton("Exit");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateInstructor ci = new CreateInstructor();
				dispose();
			}
		});
		btnOk.setBounds(343, 447, 89, 23);
		contentPane.add(btnOk);
		
		no_prof = new JTextField();
		no_prof.setBounds(302, 98, 130, 20);
		contentPane.add(no_prof);
		no_prof.setColumns(10);
		
		JLabel lblInstructorIds = new JLabel("Instructor ID's:");
		lblInstructorIds.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructorIds.setBounds(10, 138, 178, 14);
		contentPane.add(lblInstructorIds);				
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 321, 46, 14);
		contentPane.add(lblStatus);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 346, 422, 63);
		contentPane.add(textArea_Response);
		textArea_Response.setColumns(10);
	}

}
