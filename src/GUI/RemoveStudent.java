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

public class RemoveStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField  textField_Student_ID;
	private static JTextField textField_Force_Unenroll;
	private static TextArea textArea_Response;
	public UniversityClient uc;
	public TextMessage TMReceive;

	public RemoveStudent() {
		setTitle("Remove Student");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 470, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterStudentId = new JLabel("Enter Student ID to Remove:");
		lblEnterStudentId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterStudentId.setBounds(10, 11, 194, 14);
		contentPane.add(lblEnterStudentId);
		
		textField_Student_ID = new JTextField();
		textField_Student_ID.setBounds(205, 8, 188, 20);
		contentPane.add(textField_Student_ID);
		textField_Student_ID.setColumns(10);
		
		JLabel lblForceUnenroll = new JLabel("Force Unenroll:");
		lblForceUnenroll.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblForceUnenroll.setBounds(10, 54, 136, 14);
		contentPane.add(lblForceUnenroll);
		
		textField_Force_Unenroll = new JTextField();
		textField_Force_Unenroll.setBounds(205, 51, 188, 20);
		contentPane.add(textField_Force_Unenroll);
		textField_Force_Unenroll.setColumns(10);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 203, 380, 68);
		contentPane.add(textArea_Response);
		
		JButton btnOk = new JButton("Remove Student");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String studentID=RemoveStudent.textField_Student_ID.getText();
				String forcefulUnenroll = RemoveStudent.textField_Force_Unenroll.getText();
				if(studentID!="" && forcefulUnenroll!=""){
				 uc = new UniversityClient();
				 try {
					
					TMReceive= uc.Remove_Student(studentID,forcefulUnenroll);				
					String response = TMReceive.getText();
					String[] array = response.split("\n");
					System.out.println(TMReceive.getText());
					textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				else
				{
					textArea_Response.setText("Enter Valid Data");
				}
						
			}
		});
		btnOk.setBounds(239, 130, 154, 23);
		contentPane.add(btnOk);
		
		JLabel lblyesno = new JLabel("1=Yes, 0=No");
		lblyesno.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblyesno.setBounds(215, 82, 125, 14);
		contentPane.add(lblyesno);
		
		JLabel lblResponse = new JLabel("Response:");
		lblResponse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResponse.setBounds(10, 169, 110, 14);
		contentPane.add(lblResponse);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(268, 297, 125, 23);
		contentPane.add(btnExit);
		
		
	}

}
