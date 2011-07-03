package GUI;
import java.awt.Font;
import java.awt.TextArea;

import URS.*;

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

public class FindAllPersons extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField textField_SearchType;
	private static JTextField textField_NoOfPersons;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];
	public static TextArea textArea_PersonIDList;
	public static TextArea textArea_Response;

	public FindAllPersons() {
		setTitle("Find All Persons");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 530, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumberOfPersons = new JLabel("Search Type:");
		lblNumberOfPersons.setBounds(10, 11, 133, 14);
		contentPane.add(lblNumberOfPersons);
		
		JLabel lblSearchOnly = new JLabel("[0= Search Only Students] ");
		lblSearchOnly.setBounds(10, 36, 216, 15);
		contentPane.add(lblSearchOnly);
		
		textField_SearchType = new JTextField();
		textField_SearchType.setBounds(275, 8, 89, 20);
		contentPane.add(textField_SearchType);
		textField_SearchType.setColumns(10);
		
		JLabel lblSearchOnly_1 = new JLabel("[1= Search Only Instructors] ");
		lblSearchOnly_1.setBounds(10, 62, 216, 14);
		contentPane.add(lblSearchOnly_1);
		
		JLabel lblSearchBoth = new JLabel("[2= Search both Students & Instructors]");
		lblSearchBoth.setBounds(10, 81, 255, 14);
		contentPane.add(lblSearchBoth);
		
		textArea_PersonIDList = new TextArea();
		textArea_PersonIDList.setBounds(10, 210, 380, 160);
		contentPane.add(textArea_PersonIDList);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 428, 378, 65);
		contentPane.add(textArea_Response);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String searchType=textField_SearchType.getText();
				textField_NoOfPersons.setText("");
				textArea_PersonIDList.setText("");
				textArea_Response.setText("");
				if( !searchType.equals("") && (searchType.equals("0") || searchType.equals("1") || searchType.equals("2"))){
				System.out.println("Search Type for person:"+searchType);
				uc= new UniversityClient();
				try {
					TMReceive=uc.Find_All_Persons(searchType);
					response = TMReceive.getText();
					array= response.split("\n");
					textField_NoOfPersons.setText(array[2]);
					textArea_PersonIDList.setText(array[3]);
					textArea_Response.setText("RequestID: "+array[0]+" "+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					for(int i=4;i<array.length;i++){
						textArea_PersonIDList.append("\n"+array[i]);
					}
											
					
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				}	
				else{
					textArea_Response.setText("Enter Valid Search Type");
				}
			
			}
		});
		btnSearch.setBounds(275, 77, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblTotalNumberOf = new JLabel("Total Number of Persons:");
		lblTotalNumberOf.setBounds(10, 137, 179, 14);
		contentPane.add(lblTotalNumberOf);
		
		textField_NoOfPersons = new JTextField();
		textField_NoOfPersons.setBounds(182, 134, 86, 20);
		contentPane.add(textField_NoOfPersons);
		textField_NoOfPersons.setColumns(10);
		
		JLabel lblListOfPerson = new JLabel("List Of Person ID's:");
		lblListOfPerson.setBounds(10, 172, 216, 14);
		contentPane.add(lblListOfPerson);
		
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(415, 470, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 408, 133, 14);
		contentPane.add(lblStatus);
		
		
	}
}
