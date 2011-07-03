package GUI;
import URS.*;
//import Course.*;
//import Instructor.*;




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

public class CalculateBill extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_StudentID;
	private JTextField textField_BillAmount;
	private TextArea textArea_Response;
	public TextMessage TMReceive;
	public UniversityClient uc;
	public String response, array[];

	public CalculateBill() {
		setTitle("Calculate Bill");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 494, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterStudentId = new JLabel("Enter Student ID:");
		lblEnterStudentId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterStudentId.setBounds(10, 11, 139, 14);
		contentPane.add(lblEnterStudentId);
		
		JLabel lblEnrolledUnits = new JLabel("Status:");
		lblEnrolledUnits.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnrolledUnits.setBounds(10, 180, 89, 14);
		contentPane.add(lblEnrolledUnits);
		
		JLabel lblTotalAmountDue = new JLabel("Billing Amount:");
		lblTotalAmountDue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalAmountDue.setBounds(10, 122, 139, 14);
		contentPane.add(lblTotalAmountDue);
		
		textField_StudentID = new JTextField();
		textField_StudentID.setBounds(153, 8, 195, 20);
		contentPane.add(textField_StudentID);
		textField_StudentID.setColumns(10);
		
		textField_BillAmount = new JTextField();
		textField_BillAmount.setBounds(181, 120, 116, 20);
		contentPane.add(textField_BillAmount);
		textField_BillAmount.setColumns(10);
		
		JButton btnCalculateAmount = new JButton("Calculate Amount");
		btnCalculateAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String StudentID = textField_StudentID.getText();
				uc = new UniversityClient();
				textArea_Response.setText("");
				if((!StudentID.equals(""))){
					try {
						
						TMReceive = uc.Calculate_Bill(StudentID);
						response = TMReceive.getText();
						array= response.split("\n");
						if(array.length>2){
							textField_BillAmount.setText(array[3]);
						}	
						textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
												
						} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
				  }	
					else{
						textArea_Response.setText("Fill in all Fields !!");
					}
				
				
				
			}
		});
		btnCalculateAmount.setBounds(153, 39, 194, 23);
		contentPane.add(btnCalculateAmount);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-11, 73, 435, 0);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 73, 434, 0);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-1, 82, 435, 11);
		contentPane.add(separator_2);
		
		JLabel label = new JLabel("$");
		label.setBounds(159, 123, 12, 14);
		contentPane.add(label);
		
		JButton btnOk = new JButton("Exit");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBounds(379, 360, 89, 23);
		contentPane.add(btnOk);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 213, 380, 112);
		contentPane.add(textArea_Response);
		
		JLabel lblThanksForUsing = new JLabel("Thanks for using University Record System");
		lblThanksForUsing.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblThanksForUsing.setBounds(10, 347, 290, 14);
		contentPane.add(lblThanksForUsing);
	}

}
