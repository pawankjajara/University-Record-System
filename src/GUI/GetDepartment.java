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

public class GetDepartment extends JFrame {

	private JPanel contentPane;
	private static JTextField id;
	private JTextField dept;
	private TextArea textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GetDepartment() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Enter Instructor ID");
		lblInstructorId.setBounds(27, 39, 131, 24);
		contentPane.add(lblInstructorId);
		
		id = new JTextField();
		id.setBounds(209, 41, 131, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(27, 97, 101, 14);
		contentPane.add(lblDepartment);
		
		dept = new JTextField();
		dept.setBounds(209, 94, 131, 20);
		contentPane.add(dept);
		dept.setColumns(10);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(27, 187, 313, 45);
		contentPane.add(textArea_Response);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(39, 166, 46, 14);
		contentPane.add(lblStatus);
		
		JButton btnGetDepartment = new JButton("Get Department");
		btnGetDepartment.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String instID=GetDepartment.id.getText();
				 uc = new UniversityClient();
				 try {
						TMReceive = uc.Get_Department(instID);
						response = TMReceive.getText();
						array=response.split("\n");
						
						if(array.length<3)				
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
						else
						{
							dept.setText(array[3]);
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}											
				}
			});
					 
		btnGetDepartment.setBounds(209, 141, 131, 23);
		contentPane.add(btnGetDepartment);
	}
}
