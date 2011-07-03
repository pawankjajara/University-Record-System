package GUI;

import java.awt.TextArea;

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

import URS.*;

import java.awt.Font;
public class FindPersonsByZip extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_SearchType;
	private JTextField textField_TotalNoOfPerson;
	private JTextField textField_Zip;
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];
	public TextArea textArea_ListOfPersons;
	public TextArea textArea_response;

	public FindPersonsByZip() {
		setTitle("Find Persons By Zip");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 544, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumberOfPersons = new JLabel("Search Type:");
		lblNumberOfPersons.setBounds(10, 11, 133, 14);
		contentPane.add(lblNumberOfPersons);
		
		JLabel lblSearchOnly = new JLabel("[0= Search Only Students] ");
		lblSearchOnly.setBounds(10, 36, 231, 20);
		contentPane.add(lblSearchOnly);
		
		textField_SearchType = new JTextField();
		textField_SearchType.setBounds(239, 8, 86, 20);
		contentPane.add(textField_SearchType);
		textField_SearchType.setColumns(10);
		
		JLabel lblSearchOnly_1 = new JLabel("[1= Search Only Instructors] ");
		lblSearchOnly_1.setBounds(10, 62, 231, 14);
		contentPane.add(lblSearchOnly_1);
		
		JLabel lblSearchBoth = new JLabel("[2= Search both Students & Instructors]");
		lblSearchBoth.setBounds(10, 81, 258, 14);
		contentPane.add(lblSearchBoth);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchType = textField_SearchType.getText();
				String zip = textField_Zip.getText();
				
				System.out.println("search TYpe: "+searchType+"zip: "+zip);
				textField_TotalNoOfPerson.setText("");
				textArea_ListOfPersons.setText("");
				textArea_response.setText("");
				if( (!searchType.equals("")) && (searchType.equals("0")) || (searchType.equals("1")) || (searchType.equals("2"))){
				
				uc= new UniversityClient();
				try {
					TMReceive=uc.Find_Person_By_Zip(zip,searchType);
					response = TMReceive.getText();
					array= response.split("\n");
					//for(int j=0;j<array.length;j++){System.out.println(array[j]);}
					if(array.length>2){
					textField_TotalNoOfPerson.setText(array[2]);
					textArea_ListOfPersons.setText(array[3]);
					for(int i=4;i<array.length;i++){
						textArea_ListOfPersons.append("\n"+array[i]);
					}
					textArea_response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					else{
						textArea_response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
									
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				}
				else{
					textArea_response.setText("Enter Valid Search Type");
				}
				
			}
		});
		btnSearch.setBounds(408, 119, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblTotalNumberOf = new JLabel("Total Number of Persons:");
		lblTotalNumberOf.setBounds(10, 165, 179, 14);
		contentPane.add(lblTotalNumberOf);
		
		textField_TotalNoOfPerson = new JTextField();
		textField_TotalNoOfPerson.setBounds(182, 162, 86, 20);
		contentPane.add(textField_TotalNoOfPerson);
		textField_TotalNoOfPerson.setColumns(10);
		
		JLabel lblListOfPerson = new JLabel("List Of Person ID's:");
		lblListOfPerson.setBounds(10, 190, 216, 14);
		contentPane.add(lblListOfPerson);
		
		textArea_ListOfPersons = new TextArea();
		textArea_ListOfPersons.setBounds(10, 210, 380, 160);
		contentPane.add(textArea_ListOfPersons);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(429, 474, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblEnterName = new JLabel("Enter Zip:");
		lblEnterName.setBounds(10, 123, 83, 14);
		contentPane.add(lblEnterName);
		
		textField_Zip = new JTextField();
		textField_Zip.setBounds(103, 120, 222, 20);
		contentPane.add(textField_Zip);
		textField_Zip.setColumns(10);
		
		textArea_response = new TextArea();
		textArea_response.setBounds(10, 413, 374, 68);
		contentPane.add(textArea_response);
		
		JLabel labelResponse = new JLabel("Status:");
		labelResponse.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelResponse.setBounds(10, 381, 98, 14);
		contentPane.add(labelResponse);
	}
}
