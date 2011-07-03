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

public class GetOfficeHours extends JFrame {

	private JPanel contentPane;
	private static JTextField id;
	private JTextField count;
	private TextArea textArea,textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];
	
	public GetOfficeHours() {
		setTitle("Get Office Hours");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 514, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Instructor ID:");
		lblInstructorId.setBounds(10, 14, 143, 14);
		contentPane.add(lblInstructorId);
		
		id = new JTextField();
		id.setBounds(163, 11, 170, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton btnGetOfficeHours = new JButton("Get Office Hours");
		btnGetOfficeHours.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String ID=GetOfficeHours.id.getText();
				 uc = new UniversityClient();
				 try {
					TMReceive = uc.Get_Office_Hours(ID);
					response = TMReceive.getText();
					array=response.split("\n");
					
					if(array.length<3)					
						textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);					
					else
					{
						int no_param=Integer.parseInt(array[2]);
						if(no_param>0)
						{
							count.setText(array[2]);
							textArea.setText(array[3]);
							for(int i=4;i<array.length;i++)
							{
								textArea.append("\n"+array[i]);								
							}
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
						else
						{
							count.setText(array[2]);
							textArea_Response.append("\nRequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
						}
					}					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}											
			}
		});
		
		btnGetOfficeHours.setBounds(163, 65, 170, 23);
		contentPane.add(btnGetOfficeHours);
		
		JLabel lblNumberOfOffice = new JLabel("Number Of Office Hours:");
		lblNumberOfOffice.setBounds(10, 115, 143, 14);
		contentPane.add(lblNumberOfOffice);
		
		count = new JTextField();
		count.setBounds(163, 112, 86, 20);
		contentPane.add(count);
		count.setColumns(10);
		
		JLabel lblLocations = new JLabel("Locations:");
		lblLocations.setBounds(10, 158, 143, 14);
		contentPane.add(lblLocations);
		
		textArea = new TextArea();
		textArea.setBounds(10, 178, 446, 127);
		contentPane.add(textArea);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(367, 429, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 311, 46, 14);
		contentPane.add(lblStatus);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 331, 454, 73);
		contentPane.add(textArea_Response);
	}
}
