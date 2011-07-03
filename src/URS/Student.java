package URS;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Student 
{
	private Connection conn;
	ValidationClass validate=new ValidationClass();
	private static Scanner scanner = new Scanner(System.in);

	public void Create_Student(String s1,String s2,String s3,String s4,String s5,String s6,String s7) throws ExceptionClass
	{

		if(validate.validateID(s3))
		{
			if(validate.validateState(s6))
			{
				if(validate.validateZip(s7))
				{	
					DBConnection db = new DBConnection();
					conn=db.ConnectToDB();
					try
					{
						System.out.println("Connecting" );
						PreparedStatement stmt = null;
						ResultSet rs = null;
						if(validate.validate_student(s3))
						{
							throw new ExceptionClass(UniversityExceptionList.person_exists);
						}else
						{
							String str = "insert into person (person_id,first_name, last_name, address, city, state, zip,role) values (?, ?, ?, ?, ?, ?, ?, ?)";
							stmt = conn.prepareStatement(str);
							stmt.setString(1, s3);
							stmt.setString(2, s1);
							stmt.setString(3, s2);
							stmt.setString(4, s4);
							stmt.setString(5, s5);
							stmt.setString(6, s6);
							stmt.setString(7, s7);
							stmt.setString(8, "S");
							
							stmt.executeUpdate();
							System.out.println("query 2 :" );
							str = "insert into student (student_id,enrolled_units) values (?,?) ";
							stmt =conn.prepareStatement(str);
							stmt.setString(1,s3);
							stmt.setInt(2,0);

							stmt.executeUpdate();
							//conn.commit();
							conn.close();
							System.out.println("New Student created");
							System.out.println("Disconnected from Database");
						}
						
					}
					catch (SQLException e) {
						throw new ExceptionClass(UniversityExceptionList.create_exception);
					}

					catch(Exception e){
						System.out.println("Exception Generated: "+e);				
					}
				}
				else
				{
					throw new ExceptionClass(UniversityExceptionList.malformed_zip);
				}
			}
			else
			{
				throw new ExceptionClass(UniversityExceptionList.malformed_state);
			}
		}
		else
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}

	} 

	//delete student
	public void Remove_Student(String student_id,String force_unenroll) throws ExceptionClass
	{
		try
		{
			if(validate.validateID(student_id))
			{
				DBConnection db = new DBConnection();
				conn=db.ConnectToDB();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				int choice =0,cnt=0;
				String str =null;

				choice = Integer.parseInt(force_unenroll);
				
				
				if(validate.validate_student(student_id))
				{
					//System.out.println("Forcefully Unenroll? 1. Yes 0. No");
					//choice = scanner.nextInt();

					if(choice==1) //delete student and delete mapping
					{
						System.out.println("Courses taken by student will also be deleted");

						str = "delete from person where person_id = ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, student_id);
						stmt.executeUpdate();

						str = "delete from student_course where student_id = ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, student_id);
						stmt.executeUpdate();
						//conn.commit();
						conn.close();
						System.out.println("Student Deleted with Student Id :"+ student_id);
						System.out.println("Disconnected from Database");

					}
					else if(choice==0)// throw exception
					{
						str = "select count(2) cnt from student_course where student_id = ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, student_id);
						//stmt.executeUpdate();
						rs = stmt.executeQuery();
						int count=0;
						while(rs.next())
						{
							count=rs.getInt("cnt");
						}
						System.out.println("Count :" +count);
						if(count>0)
						{
							System.out.println("Student is enrolled in course.");
							throw new ExceptionClass(UniversityExceptionList.has_courses);
						}
						else
						{
							System.out.println("Student Not enrolled in any course. So deleted");
							str = "delete from person where person_id = ?";
							stmt = conn.prepareStatement(str);
							stmt.setString(1, student_id);
							stmt.executeUpdate();
							conn.close();
						}

					}
					else
					{
						System.out.println("Wrong Input!!");
						throw new ExceptionClass(UniversityExceptionList.general_exception);
					}
				}//else
				else {
					System.out.println("Person not found");
				throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}

			}//if
			else
			{	
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
			}	
		}

		catch (SQLException e)
		{
			System.out.println(e);
			throw new ExceptionClass(UniversityExceptionList.remove_exception);
		}
	}


	/*
	public void Set_First_Name(String student_id,String fname) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{	
			
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				if(validate.validate_student(student_id))
				{
					String str = "update person set first_name = ? where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(2,student_id );
					stmt.setString(1,fname );

					stmt.executeUpdate();
					//conn.commit();
					conn.close();
					System.out.println("Student Name set"+ fname);
					System.out.println("Disconnected from Database");
				
				}
				else
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	
	}

	public ArrayList<String> Get_First_Name(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{

			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try
			{		
				System.out.println("student id :" + student_id);
				PreparedStatement stmt = null;
				ArrayList<String> first_name= new ArrayList<String>();
				ResultSet rs = null;
				
				if(validate.validate_student(student_id))
				{
					String str = "select first_name from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);
	
					rs = stmt.executeQuery();
	
					while (rs.next()) 
					{
						first_name.add(rs.getString("first_name"));
						System.out.println("First Name Retrieved is: "+first_name);
					}
					rs.close();
					//conn.commit();
					conn.close();
	
					System.out.println("Disconnected from Database");
					return first_name;
					
				}
				else
				throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}
			catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}

		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		return null;
	}

	public void Set_Last_Name(String student_id,String lname) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try
			{		
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				if(validate.validate_student(student_id))
				{
					String str = "update person set last_name = ? where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(2, student_id);
					stmt.setString(1, lname);
					stmt.executeUpdate();
					//conn.commit();
					conn.close();
					System.out.println("student last Name set");
					System.out.println("Disconnected from Database");
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch(SQLException e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		
	}

	public ArrayList<String> Get_Last_Name(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				
				ArrayList<String> last_name= new ArrayList<String>();
				ResultSet rs = null;

				if(validate.validate_student(student_id))
				{
					String str = "select last_name from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);

					rs = stmt.executeQuery();

					if (rs.next()) 
					{
						last_name.add(rs.getString(1));
					}
					rs.close();
					//conn.commit();
					conn.close();

					System.out.println("Record selected"+ last_name);
					System.out.println("Disconnected from Database");
					return last_name;
				}
				else {
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		return null;
	}

	//address

	public void Set_Address(String student_id,String address) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{

			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				ResultSet rs = null;
				if(validate.validate_student(student_id))
				{
					String str = "update person set address = ? where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(2, student_id);
					stmt.setString(1, address);

					stmt.executeUpdate();
					//conn.commit();
					conn.close();
					System.out.println("person address set");
					System.out.println("Disconnected from Database");
				} 
				else
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}

	public ArrayList<String> Get_Address(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{	
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				
				ArrayList<String> address=new ArrayList<String>();
				ResultSet rs = null;

				if(validate.validate_student(student_id))
				{
					String str = "select address from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);

					rs = stmt.executeQuery();

					while (rs.next()) 
					{
						address.add(rs.getString(1));
					}
					rs.close();
					//conn.commit();
					conn.close();

					System.out.println("Address selected");
					System.out.println("Disconnected from Database");
					return address;
				}
				else {
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		return null;
	}

	//city

	public void Set_City(String student_id,String city) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				
				ResultSet rs = null;
				if(validate.validate_student(student_id))
				{
					String str = "update person set city = ? where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, city);
					stmt.setString(2, student_id);

					stmt.executeUpdate();
					//conn.commit();
					conn.close();
					System.out.println("person city set" + city);
					System.out.println("Disconnected from Database");
				} else
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}

	public ArrayList<String> Get_City(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				ArrayList<String> city=new ArrayList<String>();
				ResultSet rs = null;

				if(validate.validate_student(student_id))
				{
					String str = "select city from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);

					rs = stmt.executeQuery();

					while (rs.next()) 
					{
						city.add(rs.getString(1));
					}
					rs.close();
					//conn.commit();
					conn.close();

					System.out.println("City selected");
					System.out.println("Disconnected from Database");
					return city;
				}
				 else
					{
						throw new ExceptionClass(UniversityExceptionList.no_such_person);
					}
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		return null;
	}

	//state

	public void Set_State(String student_id,String state) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			validate.validateState(state);
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				ResultSet rs = null;
				if(validate.validate_student(student_id))
				{

					String str = "update person set state = ? where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(2, student_id);
					stmt.setString(1, state);

					stmt.executeUpdate();
					//conn.commit();
					conn.close();
					System.out.println("person state set");
					System.out.println("Disconnected from Database");
				}
				else
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}

	public ArrayList<String> Get_State(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				
				ArrayList<String> state=new ArrayList<String>();
				ResultSet rs = null;

				if(validate.validate_student(student_id))
				{
					String str = "select state from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);

					rs = stmt.executeQuery();

					if (rs.next()) 
					{
						state.add(rs.getString(1));
					}
					rs.close();
					//conn.commit();
					conn.close();

					System.out.println("State selected");
					System.out.println("Disconnected from Database");
					return state;
				}
				else
					throw new ExceptionClass (UniversityExceptionList.no_such_person);
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		return null;
	}

	//Zip


	public void Set_Zip(String student_id,String zip) throws ExceptionClass
	{

		if(validate.validateZip(zip))
		{
			if(validate.validateID(student_id))
			{
				DBConnection db = new DBConnection();
				conn=db.ConnectToDB();
				try
				{		

					PreparedStatement stmt = null;
					ResultSet rs = null;
					
					if(validate.validate_student(student_id))
					{
						String str = "update person set zip = ? where person_id = ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, zip);
						stmt.setString(2, student_id);
	
						stmt.executeUpdate();
						//conn.commit();
						conn.close();
						System.out.println("person zip set");
						System.out.println("Disconnected from Database");
					}
					else
					{	throw new ExceptionClass(UniversityExceptionList.no_such_person);
					}
	
				}
				catch(Exception e){
					System.out.println("Exception Generated: "+e);				
				}
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_zip);
	}

	public ArrayList<String> Get_Zip(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{		

				PreparedStatement stmt = null;
				
				ArrayList<String> zip=new ArrayList<String>();
				ResultSet rs = null;

				if(validate.validate_student(student_id))
				{
					String str = "select zip from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);

					rs = stmt.executeQuery();

					while (rs.next()) 
					{
						zip.add(rs.getString(1));
					}
					rs.close();
					//conn.commit();
					conn.close();

					System.out.println("Zip selected");
					System.out.println("Disconnected from Database");
					return zip;
				}
				 else
						throw new ExceptionClass (UniversityExceptionList.no_such_person);
			}catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		return null;
	}

	//iD

	public void Set_ID(String old_id,String ID) throws ExceptionClass
	{

		if(validate.validateID(ID))
		{
			if(validate.validateID(old_id))
			{
				DBConnection db = new DBConnection();
				conn=db.ConnectToDB();
				try{		
	
					PreparedStatement stmt = null;
					ResultSet rs =null;
					//QUERY FOR CHECKING IF PERSON ID EXISTs IN DB
					String role ="";
					if(validate.validate_student(old_id))
					{
						//QUERY FOR CHECKING IF new ID ALREADY EXIST IN DB
						String str = "select count(1) cnt from person where person_id= ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, ID);
						rs = stmt.executeQuery();
						int count=0;
						while(rs.next())
						{
							count=rs.getInt("cnt");
						}
						rs.close();
						System.out.println("Count :" +count);
						if(count==0)
						{
							str = "select role from person where person_id = ? ";
							stmt = conn.prepareStatement(str);
							stmt.setString(1, old_id);
							rs = stmt.executeQuery();
							while(rs.next())
							{
								role = rs.getString("role");
							}
							rs.close();
							
							str = "update person set person_id = ? where person_id = ?";
		
							stmt = conn.prepareStatement(str);
							stmt.setString(2, old_id);
							stmt.setString(1, ID);
							stmt.executeUpdate();
							
							if(role.equalsIgnoreCase("S"))
							{
								str = "update student set student_id = ? where student_id = ?";
								stmt = conn.prepareStatement(str);
								stmt.setString(1, ID);
								stmt.setString(2, old_id);
								stmt.executeUpdate();
								
								str = "update student_course set student_id = ? where student_id = ?";
								stmt = conn.prepareStatement(str);
								stmt.setString(1, ID);
								stmt.setString(2, old_id);
								stmt.executeUpdate();
		
							}
							else if(role.equalsIgnoreCase("I"))
							{
								str = "update instructor set instructor_id = ? where instructor_id = ?";
								stmt = conn.prepareStatement(str);
								stmt.setString(1, ID);
								stmt.setString(2, old_id);
								stmt.executeUpdate();
								
								str = "update instructor_location set instructor_id = ? where instructor_id = ?";
								stmt = conn.prepareStatement(str);
								stmt.setString(1, ID);
								stmt.setString(2, old_id);
								stmt.executeUpdate();
								
							}
							//conn.commit();
							conn.close();
							System.out.println("person ID set");
							System.out.println("Disconnected from Database");
						}
						else
						{
							throw new ExceptionClass(UniversityExceptionList.person_exists);
						}
					}
					else
						throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				catch(Exception e){
					System.out.println("Exception Generated: "+e);				
				}
				
			}
			else
				throw new ExceptionClass (UniversityExceptionList.malformed_id);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
*/
	//create student

	//get courses
	public ArrayList<String> Get_Courses(String student_id)throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();

			ArrayList<String> reply = new ArrayList<String>();

			try
			{
				PreparedStatement stmt = null;
				ResultSet rs =null;
				String student = "";
				
				if(validate.validate_student(student_id))
				{
					String str = "select course_name, course_section from student_course where student_id = ?";
	
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);
					//stmt.executeUpdate();
					rs = stmt.executeQuery();
	
					while (rs.next()) 
					{
						reply.add(rs.getString(1)+" "+rs.getString(2));
						
					}
	
	
					rs.close();
					//conn.commit();
					conn.close();
					System.out.println("courses returned");
					System.out.println("Disconnected from Database");
					return reply;
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			} 
			catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);

		return null;
	}

	//get enrolled units

	public ArrayList<String> Get_Enrolled_Units(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			ArrayList<String> enrolled_units=new ArrayList<String>();
			try
			{
				PreparedStatement stmt = null;
				ResultSet rs =null;
				
				if(validate.validate_student(student_id))
				{
					String str = "select enrolled_units from student where student_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);
	
					rs = stmt.executeQuery();
	
					if (rs.next()) 
					{
						enrolled_units.add(rs.getString(1));
	
					}
					rs.close();
	
					//stmt.executeUpdate();
					//conn.commit();
					conn.close();
					System.out.println("enrolled_units returned");
					System.out.println("Disconnected from Database");
					return enrolled_units;
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			} 
			catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);

		return null;
	}

	//list students
	public ArrayList<String> List_Students() throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		ArrayList<String> reply = new ArrayList<String>();
		try{		

			PreparedStatement stmt = null;
			ResultSet rs = null;
			String str = "select first_name,last_name from person";
			stmt = conn.prepareStatement(str);

			rs = stmt.executeQuery();

			while (rs.next()) 
			{
				reply.add(rs.getString(1)+" "+rs.getString(2));
			}
			rs.close();
			//conn.commit();
			conn.close();

			System.out.println("student selected");
			System.out.println("Disconnected from Database");

		}
		catch (SQLException e) {
			throw new ExceptionClass(UniversityExceptionList.no_such_person);
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}
		return reply;
	}

	//display student info
	public ArrayList<String> Display_Student_Info(String student_id) throws ExceptionClass
	{
		if(validate.validateID(student_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			ArrayList<String> reply= new ArrayList<String>();
			try
			{		
				ResultSet rs = null;
				PreparedStatement stmt = null;

				String student = "";
				String str1 = "select person_id from person where person_id=?";
				stmt = conn.prepareStatement(str1);
				stmt.setString(1,student_id );
				rs = stmt.executeQuery();
				
				while (rs.next()) 
				{
					student = rs.getString(1);
				}
				rs.close();
				
				if(!student.equals(""))
				{
					String str = "select p.first_name,p.last_name,p.address,p.city,p.state,p.zip,s.enrolled_units,sc.course_name from person p,student s,student_course sc where p.person_id = sc.student_id AND sc.student_id = s.student_id AND s.student_id = ? ";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, student_id);
					rs = stmt.executeQuery();
					while (rs.next()) 
					{
						reply.add(rs.getString(1));
						reply.add(rs.getString(2));
						reply.add(rs.getString(3));
						reply.add(rs.getString(4));
						reply.add(rs.getString(5));
						reply.add(rs.getString(6));
						reply.add(rs.getString(7));
						reply.add(rs.getString(8));
						
					}
	
					rs.close();
					//conn.commit();
					conn.close();
	
					System.out.println("show student information");
					System.out.println("Disconnected from Database");
					return reply;
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}
			catch (SQLException e) {
				System.out.println("Error"+e);
			}

		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		return null;
	}
}

