package GUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import URS.UniversityClient;

public class CreateInstructor extends JFrame {

	private static JPanel contentPane;
	private static JTextField id;
	private static JTextField fname;
	private static JTextField lname;
	private static JTextField add;
	private static JTextField city;
	private static JTextField state;
	private static JTextField zip;
	private static JTextField dept;
	private static JTextField response;
	public UniversityClient uc;
	public TextMessage TMReceive;
	/**
	 * Launch the application.
	 */
	public CreateInstructor() {
		setTitle("Create Instructor");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 100, 525, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInstructorId = new JLabel("Instructor ID:");
		lblInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstructorId.setBounds(10, 22, 110, 14);
		contentPane.add(lblInstructorId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(10, 47, 110, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(10, 72, 110, 14);
		contentPane.add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 101, 110, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 157, 110, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblState.setBounds(10, 182, 110, 14);
		contentPane.add(lblState);
		
		JLabel lblZipCode = new JLabel("Zip Code:");
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblZipCode.setBounds(10, 207, 110, 14);
		contentPane.add(lblZipCode);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDepartment.setBounds(10, 232, 110, 27);
		contentPane.add(lblDepartment);
		
		JLabel lblToHours = new JLabel("Status:");
		lblToHours.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblToHours.setBounds(10, 302, 100, 14);
		contentPane.add(lblToHours);
		
		id = new JTextField();
		id.setBounds(130, 20, 164, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		fname = new JTextField();
		fname.setBounds(130, 45, 164, 20);
		contentPane.add(fname);
		fname.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(130, 72, 164, 20);
		contentPane.add(lname);
		lname.setColumns(10);
		
		add = new JTextField();
		add.setBounds(130, 99, 283, 45);
		contentPane.add(add);
		add.setColumns(10);
		
		city = new JTextField();
		city.setBounds(129, 155, 164, 20);
		contentPane.add(city);
		city.setColumns(10);
		
		state = new JTextField();
		state.setBounds(130, 180, 164, 20);
		contentPane.add(state);
		state.setColumns(10);
		
		zip = new JTextField();
		zip.setBounds(130, 205, 86, 20);
		contentPane.add(zip);
		zip.setColumns(10);
		
		dept = new JTextField();
		dept.setBounds(130, 236, 164, 20);
		contentPane.add(dept);
		dept.setColumns(10);
		
		response = new JTextField();
		response.setBounds(130, 300, 283, 50);
		contentPane.add(response);
		response.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				
				String First_Name=fname.getText();
				String Last_Name=lname.getText();
				String Instructor_id=id.getText();
				String Address=add.getText();
				String City=city.getText();
				String State=state.getText();
				String Zip=zip.getText();
				String Department=dept.getText();
				uc=new UniversityClient();
				 try {
					System.out.println("Instructor: "+Instructor_id);
					System.out.println("first name: "+First_Name);
					TMReceive= uc.Create_Instructor(First_Name,Last_Name,Instructor_id,Address,City,State,Zip,Department);
					String result = TMReceive.getText();
					System.out.println(result);
					String[] array = result.split("\n");
					System.out.println("result"+TMReceive.getText());
					System.out.println("request id "+ array[0]);
					System.out.println("response "+ array[1]);
					CreateInstructor.response.setText("RequestID: "+(array[0])+"\n"+"Response: "+ExceptionList.exceptionList[Integer.parseInt(array[1])]);
					//reply.setText(array[0]);
					
				 }
				 catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
		});
		
		btnSubmit.setBounds(327, 361, 86, 23);
		contentPane.add(btnSubmit);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateInstructor ci = new CreateInstructor();
				dispose();
			}
		});
		btnExit.setBounds(217, 361, 89, 23);
		contentPane.add(btnExit);
	}
}
