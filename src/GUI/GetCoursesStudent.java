package GUI;

import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GetCoursesStudent extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	public GetCoursesStudent() {
		setTitle("Get Courses Student");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterInstructorId = new JLabel("Enter Student ID:");
		lblEnterInstructorId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterInstructorId.setBounds(10, 11, 185, 14);
		contentPane.add(lblEnterInstructorId);
		
		textField = new JTextField();
		textField.setBounds(195, 9, 218, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search Courses\r\n");
		btnSearch.setBounds(268, 40, 145, 23);
		contentPane.add(btnSearch);
		
		JLabel lblCoursesUnderAbove = new JLabel("Number Of Courses:");
		lblCoursesUnderAbove.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCoursesUnderAbove.setBounds(10, 100, 145, 14);
		contentPane.add(lblCoursesUnderAbove);
		
		textField_1 = new JTextField();
		textField_1.setBounds(195, 98, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCourseNamesAnd = new JLabel("Course Name(s) and Section(s):");
		lblCourseNamesAnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseNamesAnd.setBounds(10, 146, 269, 14);
		contentPane.add(lblCourseNamesAnd);
		
		TextArea textArea = new TextArea();
		textArea.setText("Software Systems Engineering 1\r\nEnterprise Software Overview 2\r\nEnterprise Distributed Objects 2\r\nXML For E-Business             3");
		textArea.setBounds(10, 172, 420, 183);
		contentPane.add(textArea);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 74, 484, 26);
		contentPane.add(separator);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(295, 372, 136, 23);
		contentPane.add(btnOk);
	}
//	public GetCoursesStudent() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//	}

}
