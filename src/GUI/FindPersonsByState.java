package GUI;

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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindPersonsByState extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_searchType;
	private JTextField textField_TotalNoOfPersons;
	private JTextField textField_State;
	private TextArea textArea_Response;
	public TextArea textArea_ListOfPersons;
	
	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];

	public FindPersonsByState() {
		setTitle("Find Persons By Name");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 548, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumberOfPersons = new JLabel("Search Type:");
		lblNumberOfPersons.setBounds(10, 11, 133, 14);
		contentPane.add(lblNumberOfPersons);
		
		JLabel lblSearchOnly = new JLabel("[0= Search Only Students] ");
		lblSearchOnly.setBounds(10, 36, 258, 20);
		contentPane.add(lblSearchOnly);
		
		textField_searchType = new JTextField();
		textField_searchType.setBounds(239, 8, 86, 20);
		contentPane.add(textField_searchType);
		textField_searchType.setColumns(10);
		
		JLabel lblSearchOnly_1 = new JLabel("[1= Search Only Instructors] ");
		lblSearchOnly_1.setBounds(10, 62, 244, 14);
		contentPane.add(lblSearchOnly_1);
		
		JLabel lblSearchBoth = new JLabel("[2= Search both Students & Instructors]");
		lblSearchBoth.setBounds(10, 81, 279, 14);
		contentPane.add(lblSearchBoth);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String searchType = textField_searchType.getText();
				String state = textField_State.getText();
				textField_TotalNoOfPersons.setText("");
				textArea_ListOfPersons.setText("");
				textArea_Response.setText("");
				if( !searchType.equals("") && (searchType.equals("0") || searchType.equals("1") || searchType.equals("2"))){
				uc= new UniversityClient();
				try {
					TMReceive=uc.Find_Person_By_State(state, searchType);
					response = TMReceive.getText();
					array= response.split("\n");
					for(int j=0;j<array.length;j++){System.out.println(array[j]);}
					if(array.length>2){
					textField_TotalNoOfPersons.setText(array[2]);
					textArea_ListOfPersons.setText(array[3]);
					for(int i=4;i<array.length;i++){
						textArea_ListOfPersons.append("\n"+array[i]);
					}
					textArea_Response.setText("RequestID: "+array[0]+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
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
		btnSearch.setBounds(408, 119, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblTotalNumberOf = new JLabel("Total Number of Persons:");
		lblTotalNumberOf.setBounds(10, 165, 179, 14);
		contentPane.add(lblTotalNumberOf);
		
		textField_TotalNoOfPersons = new JTextField();
		textField_TotalNoOfPersons.setBounds(182, 162, 86, 20);
		contentPane.add(textField_TotalNoOfPersons);
		textField_TotalNoOfPersons.setColumns(10);
		
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
		btnExit.setBounds(433, 494, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblEnterName = new JLabel("Enter State:");
		lblEnterName.setBounds(10, 123, 83, 14);
		contentPane.add(lblEnterName);
		
		textField_State = new JTextField();
		textField_State.setBounds(103, 120, 222, 20);
		contentPane.add(textField_State);
		textField_State.setColumns(10);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(10, 425, 380, 92);
		contentPane.add(textArea_Response);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(10, 388, 83, 14);
		contentPane.add(lblStatus);
	}
}
