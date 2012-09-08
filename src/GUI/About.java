package GUI;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public About() {
		setTitle("About Unviersity Record System");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 432, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblThisProjectIs = new JLabel("This Project is Developed By:");
		lblThisProjectIs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThisProjectIs.setBounds(10, 11, 268, 14);
		contentPane.add(lblThisProjectIs);
		
		JLabel lblMadhaviFale = new JLabel("M Fale");
		lblMadhaviFale.setBounds(10, 36, 189, 14);
		contentPane.add(lblMadhaviFale);
		
		JLabel lblRuchitaBora = new JLabel("R Bora");
		lblRuchitaBora.setBounds(10, 60, 138, 14);
		contentPane.add(lblRuchitaBora);
		
		JLabel lblSagarDhedia = new JLabel("S Dhedia");
		lblSagarDhedia.setBounds(10, 85, 138, 14);
		contentPane.add(lblSagarDhedia);
		
		JLabel lblPawankumarJajara = new JLabel("Pawankumarass Jajara");
		lblPawankumarJajara.setBounds(10, 110, 154, 14);
		contentPane.add(lblPawankumarJajara);
		
		JLabel lblUnderDirectGuidance = new JLabel("Under Direct Guidance Of:");
		lblUnderDirectGuidance.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUnderDirectGuidance.setBounds(10, 174, 234, 14);
		contentPane.add(lblUnderDirectGuidance);
		
		JLabel lblProfessorMikeLarkin = new JLabel("Professor Mike Larkin");
		lblProfessorMikeLarkin.setBounds(10, 199, 205, 14);
		contentPane.add(lblProfessorMikeLarkin);
		
		JLabel lblAllCopyrightsReserved = new JLabel("All Copyrights Reserved By:");
		lblAllCopyrightsReserved.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAllCopyrightsReserved.setBounds(10, 224, 268, 14);
		contentPane.add(lblAllCopyrightsReserved);
		
		JLabel lblSanJoseState = new JLabel("San Jose State University, SJ, CA-95112 Web: http://www.sjsu.edu/");
		lblSanJoseState.setBounds(10, 249, 414, 14);
		contentPane.add(lblSanJoseState);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOk.setBounds(267, 286, 89, 23);
		contentPane.add(btnOk);
		
		JLabel lblnamesInAlphabetical = new JLabel("[Names in Alphabetical Order]");
		lblnamesInAlphabetical.setBounds(10, 137, 268, 14);
		contentPane.add(lblnamesInAlphabetical);
	}

}
