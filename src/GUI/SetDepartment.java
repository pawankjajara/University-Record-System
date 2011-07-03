package GUI;

import URS.UniversityClient;

import java.awt.Font;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JTextArea;

public class SetDepartment extends JFrame {

	private JPanel contentPane;
	private static JTextField id;
	private static JTextField dept;
	private TextArea textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	String response,array[];

	
	public SetDepartment() {
		setTitle("Set Department");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 449, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Instructor ID:");
		lblInstructorId.setBounds(10, 11, 127, 14);
		contentPane.add(lblInstructorId);
		
		JButton btnSetDepartment = new JButton("Set Department");
		btnSetDepartment.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				 
				String instID=SetDepartment.id.getText();
				String Dept=SetDepartment.dept.getText();
				 uc = new UniversityClient();
				 try {
					 TMReceive=uc.Set_Department(instID, Dept);
						response = TMReceive.getText();
						array= response.split("\n");
						System.out.println("response: "+array[0] +" "+array[1]);
						textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}											
				}
			});
		btnSetDepartment.setBounds(165, 103, 156, 23);
		contentPane.add(btnSetDepartment);
		
		id = new JTextField();
		id.setBounds(165, 8, 156, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel lblDepartment = new JLabel("New Department:");
		lblDepartment.setBounds(10, 52, 127, 14);
		contentPane.add(lblDepartment);
		
		dept = new JTextField();
		dept.setBounds(165, 49, 259, 20);
		contentPane.add(dept);
		dept.setColumns(10);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(331, 103, 89, 23);
		contentPane.add(btnExit);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(50, 137, 309, 36);
		contentPane.add(textArea_Response);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(30, 107, 46, 14);
		contentPane.add(lblStatus);
	}

}
