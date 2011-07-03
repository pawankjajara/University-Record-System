package GUI;

import java.awt.Font;
import java.awt.TextArea;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import URS.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindPersonsByCity extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_SearchType;
	private JTextField textField_NumberOfPersons;
	private JTextField textField_City;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];
	public TextArea textArea_ListOfPersons;
	public TextArea textArea_Response;

	public FindPersonsByCity() {
		setTitle("Find Persons By City");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 568, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumberOfPersons = new JLabel("Search Type:");
		lblNumberOfPersons.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumberOfPersons.setBounds(10, 11, 253, 14);
		contentPane.add(lblNumberOfPersons);
		
		JLabel lblSearchOnly = new JLabel("[0= Search Only Students] ");
		lblSearchOnly.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearchOnly.setBounds(10, 36, 300, 20);
		contentPane.add(lblSearchOnly);
		
		textField_SearchType = new JTextField();
		textField_SearchType.setBounds(239, 8, 86, 20);
		contentPane.add(textField_SearchType);
		textField_SearchType.setColumns(10);
		
		JLabel lblSearchOnly_1 = new JLabel("[1= Search Only Instructors] ");
		lblSearchOnly_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearchOnly_1.setBounds(10, 62, 300, 14);
		contentPane.add(lblSearchOnly_1);
		
		JLabel lblSearchBoth = new JLabel("[2= Search both Students & Instructors]");
		lblSearchBoth.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSearchBoth.setBounds(10, 87, 329, 14);
		contentPane.add(lblSearchBoth);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchType=textField_SearchType.getText();
				String city = textField_City.getText();
				textField_NumberOfPersons.setText("");
				textArea_ListOfPersons.setText("");
				textArea_Response.setText("");
				if( !searchType.equals("") && (searchType.equals("0") || searchType.equals("1") || searchType.equals("2"))){
				uc= new UniversityClient();
				try {
					TMReceive=uc.Find_Person_By_City(city, searchType);
					response = TMReceive.getText();
					array= response.split("\n");
					for(int j=0;j<array.length;j++){System.out.println(array[j]);}
					if(array.length>2){
					textField_NumberOfPersons.setText(array[2]);
					textArea_ListOfPersons.setText(array[3]);
					for(int i=4;i<array.length;i++){
						textArea_ListOfPersons.append("\n"+array[i]);
					}
					textArea_Response.setText("RequestID: "+array[0]+"\n"+" "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					else{
						textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
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
		btnSearch.setBounds(400, 119, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblTotalNumberOf = new JLabel("Total Number of Persons:");
		lblTotalNumberOf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalNumberOf.setBounds(10, 165, 204, 14);
		contentPane.add(lblTotalNumberOf);
		
		textField_NumberOfPersons = new JTextField();
		textField_NumberOfPersons.setBounds(182, 162, 86, 20);
		contentPane.add(textField_NumberOfPersons);
		textField_NumberOfPersons.setColumns(10);
		
		JLabel lblListOfPerson = new JLabel("List Of Person ID's:");
		lblListOfPerson.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblListOfPerson.setBounds(10, 190, 253, 14);
		contentPane.add(lblListOfPerson);
		
		textArea_ListOfPersons = new TextArea();
		textArea_ListOfPersons.setBounds(10, 210, 479, 160);
		contentPane.add(textArea_ListOfPersons);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(400, 527, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblEnterName = new JLabel("Enter City:");
		lblEnterName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterName.setBounds(10, 123, 124, 14);
		contentPane.add(lblEnterName);
		
		textField_City = new JTextField();
		textField_City.setBounds(103, 120, 222, 20);
		contentPane.add(textField_City);
		textField_City.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(10, 387, 101, 14);
		contentPane.add(lblStatus);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 404, 479, 85);
		contentPane.add(textArea_Response);
	}
}
