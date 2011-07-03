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

public class UpdateInstructor extends JFrame {

	public UniversityClient uc;
	public TextMessage TMReceive;
	public String response,array[];	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField id;
	private static JTextField fname;
	private static JTextField lname;
	private static TextArea textArea_Response;
	private static JTextField city;
	private static JTextField state;
	private static JTextField zip;
	private JLabel lblNewInstructorId;
	private static JTextField new_id;
	private static JTextField add;
	private JSeparator separator;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public UpdateInstructor() {
		setTitle("Update Instructor");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 100, 532, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Instructor ID:");
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructorId.setBounds(10, 11, 110, 14);
		contentPane.add(lblInstructorId);
		
		JLabel lblFirstName = new JLabel("New First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(10, 68, 110, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("New Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(10, 93, 110, 14);
		contentPane.add(lblLastName);
		
		JLabel lblAddress = new JLabel("New Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 124, 110, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("New City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 153, 110, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("New State:");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblState.setBounds(10, 178, 110, 14);
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("New Zip Code:");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblZipCode.setBounds(10, 203, 110, 14);
		contentPane.add(lblZipCode);
		
		JLabel lblFromHours_1 = new JLabel("Response:");
		lblFromHours_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFromHours_1.setBounds(10, 337, 110, 14);
		contentPane.add(lblFromHours_1);
		
		id = new JTextField();
		id.setBounds(140, 9, 159, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		fname = new JTextField();
		fname.setBounds(140, 66, 159, 20);
		contentPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(140, 91, 159, 20);
		contentPane.add(lname);
		lname.setColumns(10);
		
		add = new JTextField();
		add.setBounds(140, 122, 283, 20);
		contentPane.add(add);
		add.setColumns(10);		
		
		city = new JTextField();
		city.setBounds(140, 151, 86, 20);
		contentPane.add(city);
		city.setColumns(10);
		
		state = new JTextField();
		state.setBounds(140, 176, 86, 20);
		contentPane.add(state);
		state.setColumns(10);
		
		zip = new JTextField();
		zip.setBounds(140, 201, 86, 20);
		contentPane.add(zip);
		zip.setColumns(10);
		
		textArea_Response = new TextArea();
		textArea_Response.setBounds(130, 340, 283, 50);
		contentPane.add(textArea_Response);
		textArea_Response.setColumns(10);
		
		lblNewInstructorId = new JLabel("New Instructor ID:");
		lblNewInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewInstructorId.setBounds(10, 228, 132, 14);
		contentPane.add(lblNewInstructorId);
		
		new_id = new JTextField();
		new_id.setBounds(140, 226, 86, 20);
		contentPane.add(new_id);
		new_id.setColumns(10);
				
		separator = new JSeparator();
		separator.setBounds(10, 316, 509, 10);
		contentPane.add(separator);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{			
				String sid,nsid,fn,ln,ad,City,State,Zip,Dept;
				
				sid=id.getText();
				System.out.println("SID: "+sid);
				nsid=new_id.getText();
				System.out.println("NSID :"+nsid);
				fn=fname.getText();
				System.out.println("fn :"+fn);
				ln=lname.getText();
				System.out.println("ln :"+ln);
				ad=add.getText();
				System.out.println("ad :"+ad);
				City=city.getText();
				System.out.println("city :"+City);
				State=state.getText();
				System.out.println("state :"+State);
				Zip=zip.getText();
				System.out.println("zip :"+Zip);				
				uc=new UniversityClient();
				try {
					if(!fn.equals(""))
					{
						System.out.println("Inside set first name in update");
						TMReceive=uc.Set_First_Name(sid, fn);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!ln.equals(""))
					{
						TMReceive=uc.Set_Last_Name(sid, ln);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!ad.equals(""))
					{
						TMReceive=uc.Set_Address(sid,ad);
						response = TMReceive.getText();
						System.out.println("Response: "+response);
						array= response.split("\n");
						textArea_Response.setText("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}															
					
					if(!City.equals(""))
					{
						TMReceive=uc.Set_City(sid, City);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!State.equals(""))
					{
						TMReceive=uc.Set_State(sid, State);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!Zip.equals(""))
					{
						TMReceive=uc.Set_Zip(sid, Zip);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
					
					if(!nsid.equals(""))
					{
						TMReceive=uc.Set_ID(sid, nsid);
						response = TMReceive.getText();
						array= response.split("\n");
						textArea_Response.append("RequestID: "+array[0]+" "+"\n"+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					}
										
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}												
			}
		});
		btnSubmit.setBounds(327, 282, 86, 23);
		contentPane.add(btnSubmit);				
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(324, 401, 89, 23);
		contentPane.add(btnExit);
	}
	

}
