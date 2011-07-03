package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 598);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnInstructor = new JMenu("Instructor");
		menuBar.add(mnInstructor);
		
		JMenuItem mntmCreateStudent = new JMenuItem("Create Instructor\r\n\r\n");
		mntmCreateStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Insert the code for frame to be called when this menu item is clicked !!
				CreateInstructor ci = new CreateInstructor();
				ci.setVisible(true);
			}
		});
		mnInstructor.add(mntmCreateStudent);
		
		JMenuItem mntmUpdateinstructor = new JMenuItem("Update Instructor");
		mntmUpdateinstructor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateInstructor ui = new UpdateInstructor();
				ui.setVisible(true);
			}
		});
		mnInstructor.add(mntmUpdateinstructor);
		
		JMenuItem mntmRetrieveInstructor = new JMenuItem("Retrieve Instructor");
		mntmRetrieveInstructor.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				RetrieveInstructor ri = new RetrieveInstructor();
				ri.setVisible(true);
			}
		});
		mnInstructor.add(mntmRetrieveInstructor);
		
		JMenuItem mntmGetOfficeHours = new JMenuItem("Get Office Hours");
		mntmGetOfficeHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GetOfficeHours goh = new GetOfficeHours();
				goh.setVisible(true);
			}
		});
		mnInstructor.add(mntmGetOfficeHours);
		
		JMenuItem mntmAddOfficeHours = new JMenuItem("Add Office Hours");
		mntmAddOfficeHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddOfficeHours aoh = new AddOfficeHours();
				aoh.setVisible(true);
			}
		});
		mnInstructor.add(mntmAddOfficeHours);
		
		JMenuItem mntmRemoveOfficeHours = new JMenuItem("Remove Office Hours");
		mntmRemoveOfficeHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveOfficeHours roh = new RemoveOfficeHours();
				roh.setVisible(true);
			}
		});
		mnInstructor.add(mntmRemoveOfficeHours);
		
		JMenuItem mntmInstructorByCourse = new JMenuItem("Find Instructors By Department");
		mntmInstructorByCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FindInstructorByDepartment ibd = new FindInstructorByDepartment();
				ibd.setVisible(true);
			}
		});
		mnInstructor.add(mntmInstructorByCourse);
		
		JMenu mnStudent = new JMenu("Student");
		menuBar.add(mnStudent);
		
		JMenuItem mntmCreateStudent_1 = new JMenuItem("Create Student");
		mntmCreateStudent_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateStudent cs = new CreateStudent();
				cs.setVisible(true);
			}
		});
		mnStudent.add(mntmCreateStudent_1);
		
		JMenuItem mntmGetCourses = new JMenuItem("Retrieve Student");
		mntmGetCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RetrieveStudent rs = new RetrieveStudent();
				rs.setVisible(true);
			}
		});
		mnStudent.add(mntmGetCourses);
		
		JMenuItem mntmDeleteStudent = new JMenuItem("Remove Student");
		mntmDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveStudent rst = new RemoveStudent();
				rst.setVisible(true);
			}
		});
		mnStudent.add(mntmDeleteStudent);
		
		JMenuItem mntmUpdatestudent = new JMenuItem("Update Student");
		mntmUpdatestudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateStudent ust = new UpdateStudent();
				ust.setVisible(true);
			}
		});
		mnStudent.add(mntmUpdatestudent);
		
		JMenu mnCourse = new JMenu("Course");
		menuBar.add(mnCourse);
		
		JMenuItem mntmCreateCourse = new JMenuItem("Create Course");
		mntmCreateCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CreateCourse cc = new CreateCourse();
				cc.setVisible(true);
			}
		});
		mnCourse.add(mntmCreateCourse);
		
		JMenuItem mntmRetrieveCourse = new JMenuItem("Retrieve Course");
		mntmRetrieveCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RetrieveCourse rc = new RetrieveCourse();
				rc.setVisible(true);
			}
		});
		
		JMenuItem mntmUpdateCourse = new JMenuItem("Update Course");
		mntmUpdateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateCourse uc = new UpdateCourse();
				uc.setVisible(true);
			}
		});
		mnCourse.add(mntmUpdateCourse);
		mnCourse.add(mntmRetrieveCourse);
		
		JMenuItem mntmDeleteCourse = new JMenuItem("Remove Course");
		mntmDeleteCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RemoveCourse rc = new RemoveCourse();
				rc.setVisible(true);
			}
		});
		mnCourse.add(mntmDeleteCourse);
		
		JMenuItem mntmFindAllCourses = new JMenuItem("Find All Courses");
		mntmFindAllCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindAllCourses fac = new FindAllCourses();
				fac.setVisible(true);
			}
		});
		mnCourse.add(mntmFindAllCourses);
		
		JMenuItem mntmFindcoursesbyinstructor = new JMenuItem("Find Courses By Instructor");
		mntmFindcoursesbyinstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindCoursesByInstructor fcbi = new FindCoursesByInstructor();
				fcbi.setVisible(true);
			}
		});
		mnCourse.add(mntmFindcoursesbyinstructor);
		
		JMenuItem mntmFindcoursesbylocation = new JMenuItem("Find Courses By Location");
		mntmFindcoursesbylocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindCoursesByLocation fcbl = new FindCoursesByLocation();
				fcbl.setVisible(true);
			}
		});
		mnCourse.add(mntmFindcoursesbylocation);
		
		JMenuItem mntmFindCoursesBy = new JMenuItem("Find Courses By Course Name");
		mntmFindCoursesBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindCoursesByCourseName fcbcn = new FindCoursesByCourseName();
				fcbcn.setVisible(true);
			}
		});
		mnCourse.add(mntmFindCoursesBy);
		
		JMenu mnEnrollments = new JMenu("Enrollments\r\n");
		menuBar.add(mnEnrollments);
		
		JMenuItem mntmEnrollStudent = new JMenuItem("Enroll Student");
		mntmEnrollStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EnrollStudent es = new EnrollStudent();
				es.setVisible(true);
			}
		});
		mnEnrollments.add(mntmEnrollStudent);
		
		JMenuItem mntmUnenrollStudent = new JMenuItem("Unenroll Student");
		mntmUnenrollStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UnenrollStudent us = new UnenrollStudent();
				us.setVisible(true);
			}
		});
		mnEnrollments.add(mntmUnenrollStudent);
		
		JMenuItem mntmCalculateBill = new JMenuItem("Calculate Bill");
		mntmCalculateBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CalculateBill cb = new CalculateBill();
				cb.setVisible(true);
			}
		});
		mnEnrollments.add(mntmCalculateBill);
		
		JMenu mnPerson = new JMenu("Person");
		menuBar.add(mnPerson);
		
		JMenuItem mntmFindAllPersons = new JMenuItem("Find All Persons");
		mntmFindAllPersons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindAllPersons fap = new FindAllPersons();
				fap.setVisible(true);
			}
		});
		mnPerson.add(mntmFindAllPersons);
		
		JMenuItem mntmPersonByFirstname = new JMenuItem("Person By Name");
		mntmPersonByFirstname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindPersonsByName fpbn = new FindPersonsByName();
				fpbn.setVisible(true);
			}
		});
		mnPerson.add(mntmPersonByFirstname);
		
		JMenuItem mntmPersonByLastname = new JMenuItem("Person By State");
		mntmPersonByLastname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindPersonsByState fpbs = new FindPersonsByState();
				fpbs.setVisible(true);
			}
		});
		mnPerson.add(mntmPersonByLastname);
		
		JMenuItem mntmPersonByCity = new JMenuItem("Person By City");
		mntmPersonByCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindPersonsByCity fpbc = new FindPersonsByCity();
				fpbc.setVisible(true);
			}
		});
		mnPerson.add(mntmPersonByCity);
		
		JMenuItem mntmPersonByZipcode = new JMenuItem("Person by Zipcode");
		mntmPersonByZipcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindPersonsByZip fpbz = new FindPersonsByZip();
				fpbz.setVisible(true);
			}
		});
		mnPerson.add(mntmPersonByZipcode);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				About a = new About();
				a.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		
		JSeparator separator = new JSeparator();
		mnHelp.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnHelp.add(mntmExit);
		frame.getContentPane().setLayout(null);
	}
}
