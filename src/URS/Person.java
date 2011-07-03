package URS;
import java.sql.*;
import java.util.*;


public class Person 
{
	private Connection conn;
	ValidationClass validate=new ValidationClass();
	HelperClass helper = new HelperClass();
	
	public ArrayList<String> Find_All_Persons(String searchType) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		ArrayList<String> personList = new ArrayList<String>();
		ArrayList<String> studentList = new ArrayList<String>();
		ArrayList<String> instructorList = new ArrayList<String>();
		
		try
		{
			System.out.println("Connecting" );
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String str = "",count="";
			
			if(searchType.equals("0"))
			{
				str = "select count(1) cnt from student";
				stmt = conn.prepareStatement(str);
				rs = stmt.executeQuery();
						
				while(rs.next())
				{
					count = rs.getString("cnt");
				}
				rs.close();
				System.out.println("count " + count);
				if(!count.equals(""))
				{
					str = "select student_id from student";
					stmt = conn.prepareStatement(str);
					rs = stmt.executeQuery();
							
					while(rs.next())
					{
						studentList.add(rs.getString(1));
					}
					rs.close();
					//conn.close();
					return studentList;
				}
				else
				{
					System.out.println("no values in studnet tbl");
				}
			}
			
			else if(searchType.equals("1"))
			{
				str = "select count(1) cnt from instructor";
				stmt = conn.prepareStatement(str);
				rs = stmt.executeQuery();
						
				while(rs.next())
				{
					count = rs.getString("cnt");
				}
				rs.close();
				System.out.println("count " + count);
				if(!count.equals(""))
				{
					str = "select instructor_id from instructor";
					stmt = conn.prepareStatement(str);
					rs = stmt.executeQuery();
							
					while(rs.next())
					{
						instructorList.add(rs.getString(1));
					}
					rs.close();
					//conn.close();
					return instructorList;
				}
				else
				{
					System.out.println("no values in instructor tbl");
				}
			}
			
			else if(searchType.equals("2"))
			{
				str = "select count(1) cnt from person";
				stmt = conn.prepareStatement(str);
				rs = stmt.executeQuery();
						
				while(rs.next())
				{
					count = rs.getString("cnt");
				}
				rs.close();
				System.out.println("count " + count);
				if(!count.equals(""))
				{
					str = "select person_id from person";
					stmt = conn.prepareStatement(str);
					rs = stmt.executeQuery();
							
					while(rs.next())
					{
						personList.add(rs.getString(1));
					}
					rs.close();
					//conn.close();
					return personList;
				}
				else
				{
					System.out.println("no values in person tbl");
				}
			}
			conn.close();
			
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}
		
		return null;
		
	}
	
	///
	
	public ArrayList<String> Find_Person_By_Zip(String patternZip,String searchType) throws ExceptionClass
	{		
		DBConnection db = new DBConnection();
		ArrayList<String> reply = new ArrayList<String>();
		conn=db.ConnectToDB();
		if(validate.validateZip(patternZip))
		{
			try{		
			String str=null,cnt="";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			if(searchType.equals("0"))
			{
				str = "select student_id from student where student_id in (select person_id from person where zip= ? ) ";
				
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternZip);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("student_id"));
					System.out.println(cnt);
					
				}
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("1"))
			{
				str="select instructor_id from instructor where instructor_id in (select person_id from person where zip= ? ) ";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternZip);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("instructor_id"));
					System.out.println(cnt);
					
				}
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("2"))
			{
				str="select person_id from person where zip=?";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternZip);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("person_id"));
				}
				
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
				
			}	
			
			rs.close();
 			conn.close();
 			System.out.println("Disconnected from Database");
			}
			catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_zip);
	
		return null;
	}
	
	public ArrayList<String> Find_Person_By_City (String patternCity, String searchType) throws ExceptionClass
	{
		
		DBConnection db = new DBConnection();
		ArrayList<String> reply = new ArrayList<String>();
		//String sType=searchType,pType=patternZip;		
		conn=db.ConnectToDB();
		//if(validate.validateZip(patternCity))
		//{
			try{		
			String str=null,cnt="";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			if(searchType.equals("0"))
			{
				str = "select student_id from student where student_id in (select person_id from person where city= ? ) ";
				
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternCity);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("student_id"));
					System.out.println(cnt);
					
				}
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("1"))
			{
				str="select instructor_id from instructor where instructor_id in (select person_id from person where city= ? ) ";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternCity);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("instructor_id"));
					System.out.println(cnt);
					
				}
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("2"))
			{
				str="select person_id from person where city=?";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternCity);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("person_id"));
				}
				
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
				
			}	
			
			rs.close();
 			conn.close();
 			System.out.println("Disconnected from Database");
			}
			catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
			
		//}
		//else
			//throw new ExceptionClass(UniversityExceptionList.malformed_zip);
	
		return null;
	}
	
	public ArrayList<String> Find_Person_By_State(String patternState,String searchType)throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		ArrayList<String> reply = new ArrayList<String>();
				
		conn=db.ConnectToDB();
		if(validate.validateState(patternState))
		{
			try{		
			String str=null,cnt="";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			if(searchType.equals("0"))
			{
				str = "select student_id from student where student_id in (select person_id from person where state= ? ) ";
				
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternState);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("student_id"));
					System.out.println(cnt);
					
				}
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("1"))
			{
				str="select instructor_id from instructor where instructor_id in (select person_id from person where state= ? ) ";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternState);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("instructor_id"));
					System.out.println(cnt);
					
				}
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("2"))
			{
				str="select person_id from person where state= ? ";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternState);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("person_id"));
				}
				
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
				
			}	
			
			rs.close();
 			conn.close();
 			System.out.println("Disconnected from Database");
			}
			catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_state);
		return null;

	}
		
	
	public ArrayList<String> Find_Person_By_Name (String patternName,String searchType) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		ArrayList<String> reply = new ArrayList<String>();
				
		conn=db.ConnectToDB();
		
		try{		
			String str=null,cnt="";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			if(searchType.equals("0"))
			{
				str = "select student_id from student where student_id in (select person_id from person where last_name = ? ) ";
				
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternName);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("student_id"));
					System.out.println(cnt);
					
				}
				rs.close();
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("1"))
			{
				str="select instructor_id from instructor where instructor_id in (select person_id from person where last_name= ? ) ";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternName);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("instructor_id"));
					System.out.println(cnt);
					
				}
				rs.close();
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
			}
			
			else if(searchType.equals("2"))
			{
				str="select person_id from person where last_name= ? ";
				stmt = conn.prepareStatement(str);
				stmt.setString(1,patternName);
				rs=stmt.executeQuery();
				while(rs.next())
				{				
					cnt = rs.getString(1);
					reply.add(rs.getString(1));
					System.out.println(rs.getString("person_id"));
				}
				rs.close();
				if(cnt.equals(""))
				{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}
				else
					return reply;
				
			}	
			
			//rs.close();
 			conn.close();
 			System.out.println("Disconnected from Database");
			}
			catch(Exception e){
				System.out.println("Exception Generated: "+e);				
			}
	
		return null;

	}
	
	
	public ArrayList<String> Get_Courses(String s1)throws ExceptionClass
	{		
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		ArrayList<String> reply = new ArrayList<String>();
		try
		{				
			if(validate.validateID(s1))
			{
				PreparedStatement stmt = null;
				ResultSet rs = null;						
				if(validate.validate_person(s1))
				{
					String str="select role from person where person_id=?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, s1);
					rs = stmt.executeQuery();
					String role="";
					while(rs.next())
					{
						role=rs.getString("role");
					}
					System.out.println("Role= "+role);
					rs.close();
					if(role.equals("I"))
					{
						str="select course_name, course_section from course where instructor_id= ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, s1);
						rs = stmt.executeQuery();		
						while(rs.next())
						{
							System.out.println("Course name: "+rs.getString("course_name"));
							reply.add(rs.getString("course_name") + " " +rs.getString("course_section"));
						}
					}
					else
					{
						str="select course_name, course_section from student_course where student_id= ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, s1);
						rs = stmt.executeQuery();		
						while(rs.next())
						{
							reply.add(rs.getString("course_name") + " " +rs.getString("course_section"));
						}
					}
					conn.close();
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}catch(SQLException e) 
		{
			System.out.println(e);
		}	
		return reply;
	}
				
	public void Set_First_Name(String person_id,String fname) throws ExceptionClass
	{
		if(validate.validateID(person_id))
		{				
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{	
					PreparedStatement stmt = null;
					ResultSet rs=null;
					
					if(validate.validate_person(person_id))
					{
						String str = "update person set first_name = ? where person_id = ?";
					
						stmt = conn.prepareStatement(str);
						stmt.setString(2,person_id );
						stmt.setString(1,fname );
	
						stmt.executeUpdate();					
						conn.close();					
						System.out.println("Disconnected from Database");
					}
					else
						throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}catch (SQLException e) 
				{
					throw new ExceptionClass(UniversityExceptionList.general_exception);
				}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);	
	}

	public ArrayList<String> Get_First_Name(String person_id) throws ExceptionClass
	{		
		ArrayList<String> reply=new ArrayList();
		if(validate.validateID(person_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{						
				PreparedStatement stmt = null;
				String first_name=null;
				ResultSet rs = null;				
				if(validate.validate_person(person_id))
				{
					String str = "select first_name from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, person_id);	
					rs = stmt.executeQuery();	
					while (rs.next()) 
					{
						first_name = rs.getString("first_name");					
					}
					rs.close();
					System.out.println("FIrst name received:" + first_name);
					conn.close();
					System.out.println("Disconnected from Database");					
                    reply.add(first_name);                    
					return reply;
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch (SQLException e) 
			{
				throw new ExceptionClass(UniversityExceptionList.general_exception);
			}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
	
	public void Set_Last_Name(String person_id,String lname) throws ExceptionClass
	{
		if(validate.validateID(person_id))
		{				
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{	
					PreparedStatement stmt = null;
					ResultSet rs=null;					
					if(validate.validate_person(person_id))
					{
						String str = "update person set last_name = ? where person_id = ?";					
						stmt = conn.prepareStatement(str);
						stmt.setString(2,person_id );
						stmt.setString(1,lname );
						stmt.executeUpdate();					
						conn.close();					
						System.out.println("Disconnected from Database");
					}
					else
						throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}catch (SQLException e) 
				{
					throw new ExceptionClass(UniversityExceptionList.general_exception);
				}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);	
	}

	public ArrayList<String> Get_Last_Name(String person_id) throws ExceptionClass
	{
		ArrayList<String> reply=new ArrayList();
		if(validate.validateID(person_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{						
				PreparedStatement stmt = null;
				String last_name=null;
				ResultSet rs = null;				
				if(validate.validate_person(person_id))
				{
					String str = "select last_name from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, person_id);
	
					rs = stmt.executeQuery();
	
					while (rs.next()) 
					{
						last_name = rs.getString("last_name");					
					}
					rs.close();				
					conn.close();
					System.out.println("Disconnected from Database");
					reply.add(last_name);                    
					return reply;					
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch (SQLException e) 
			{
				throw new ExceptionClass(UniversityExceptionList.general_exception);
			}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
	
	public void Set_Address(String person_id,String address) throws ExceptionClass
	{
		if(validate.validateID(person_id))
		{				
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{	
					PreparedStatement stmt = null;
					ResultSet rs=null;					
					if(validate.validate_person(person_id))
					{
						String str = "update person set address = ? where person_id = ?";
					
						stmt = conn.prepareStatement(str);
						stmt.setString(2,person_id );
						stmt.setString(1,address);
	
						stmt.executeUpdate();					
						conn.close();					
						System.out.println("Disconnected from Database");
					}
					else
						throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}catch (SQLException e) 
				{
					throw new ExceptionClass(UniversityExceptionList.general_exception);
				}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);	
	}

	public ArrayList<String> Get_Address(String person_id) throws ExceptionClass
	{
		ArrayList<String> reply=new ArrayList();
		if(validate.validateID(person_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{						
				PreparedStatement stmt = null;
				String address=null;
				ResultSet rs = null;				
				if(validate.validate_person(person_id))
				{
					String str = "select address from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, person_id);
	
					rs = stmt.executeQuery();
	
					while (rs.next()) 
					{
						address = rs.getString("address");					
					}
					rs.close();				
					conn.close();
					System.out.println("Disconnected from Database");
					reply.add(address);                    
					return reply;					
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch (SQLException e) 
			{
				throw new ExceptionClass(UniversityExceptionList.general_exception);
			}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
	
	public void Set_City(String person_id,String city) throws ExceptionClass
	{
		if(validate.validateID(person_id))
		{				
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{	
					PreparedStatement stmt = null;
					ResultSet rs=null;					
					if(validate.validate_person(person_id))
					{
						String str = "update person set city = ? where person_id = ?";
					
						stmt = conn.prepareStatement(str);
						stmt.setString(2,person_id );
						stmt.setString(1,city);
	
						stmt.executeUpdate();					
						conn.close();					
						System.out.println("Disconnected from Database");
					}
					else
						throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}catch (SQLException e) 
				{
					throw new ExceptionClass(UniversityExceptionList.general_exception);
				}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);	
	}

	public ArrayList<String> Get_City(String person_id) throws ExceptionClass
	{
		ArrayList<String> reply = new ArrayList();
		if(validate.validateID(person_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{						
				PreparedStatement stmt = null;
				String city=null;
				ResultSet rs = null;				
				if(validate.validate_person(person_id))
				{
					String str = "select city from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, person_id);	
					rs = stmt.executeQuery();	
					while (rs.next()) 
					{
						city = rs.getString("city");					
					}
					rs.close();				
					conn.close();
					System.out.println("Disconnected from Database");
					reply.add(city);                    
					return reply;
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch (SQLException e) 
			{
				throw new ExceptionClass(UniversityExceptionList.general_exception);
			}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
	
	public void Set_State(String person_id,String state) throws ExceptionClass
	{
		if(validate.validateID(person_id))
		{
			if(validate.validateState(state))
			{
				DBConnection db = new DBConnection();
				conn=db.ConnectToDB();
				try{	
						PreparedStatement stmt = null;
						ResultSet rs=null;						
						if(validate.validate_person(person_id))
						{
							String str = "update person set state = ? where person_id = ?";
						
							stmt = conn.prepareStatement(str);
							stmt.setString(2,person_id );
							stmt.setString(1,state);
		
							stmt.executeUpdate();					
							conn.close();					
							System.out.println("Disconnected from Database");
						}
						else
							throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}catch (SQLException e) 
				{
					throw new ExceptionClass(UniversityExceptionList.general_exception);
				}
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_state);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);	
	}

	public ArrayList<String> Get_State(String person_id) throws ExceptionClass
	{
		ArrayList<String> reply = new ArrayList();
		if(validate.validateID(person_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{						
				PreparedStatement stmt = null;
				String state=null;
				ResultSet rs = null;				
				if(validate.validate_person(person_id))
				{
					String str = "select state from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, person_id);
	
					rs = stmt.executeQuery();
	
					while (rs.next()) 
					{
						state = rs.getString("state");					
					}
					rs.close();				
					conn.close();
					System.out.println("Disconnected from Database");
					reply.add(state);                    
					return reply;					
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch (SQLException e) 
			{
				throw new ExceptionClass(UniversityExceptionList.general_exception);
			}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
	
	public void Set_Zip(String person_id,String zip) throws ExceptionClass
	{
		if(validate.validateID(person_id))
		{				
			if(validate.validateZip(zip))
			{
				DBConnection db = new DBConnection();
				conn=db.ConnectToDB();
				try{	
						PreparedStatement stmt = null;
						ResultSet rs=null;						
						if(validate.validate_person(person_id))
						{
							String str = "update person set zip = ? where person_id = ?";
						
							stmt = conn.prepareStatement(str);
							stmt.setString(2,person_id );
							stmt.setString(1,zip);
		
							stmt.executeUpdate();					
							conn.close();					
							System.out.println("Disconnected from Database");
						}
						else
							throw new ExceptionClass(UniversityExceptionList.no_such_person);
					}catch (SQLException e) 
					{
						throw new ExceptionClass(UniversityExceptionList.general_exception);
					}
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_zip);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);	
	}

	public ArrayList<String> Get_Zip(String person_id) throws ExceptionClass
	{
		ArrayList<String> reply = new ArrayList();
		if(validate.validateID(person_id))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try{						
				PreparedStatement stmt = null;
				String zip=null;
				ResultSet rs = null;				
				if(validate.validate_person(person_id))
				{
					String str = "select zip from person where person_id = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, person_id);
	
					rs = stmt.executeQuery();
	
					while (rs.next()) 
					{
						zip = rs.getString("zip");					
					}
					rs.close();				
					conn.close();
					System.out.println("Disconnected from Database");
					reply.add(zip);                    
					return reply;					
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch (SQLException e) 
			{
				throw new ExceptionClass(UniversityExceptionList.general_exception);
			}			
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
	
	public void Set_ID(String person_id,String id) throws ExceptionClass
	{
		if(validate.validateID(person_id))
		{			
			if(validate.validateID(id))
			{
				DBConnection db = new DBConnection();
				conn=db.ConnectToDB();
				try{	
						PreparedStatement stmt = null;
						ResultSet rs=null;						
						if(validate.validate_person(person_id))
						{
							String str1="select person_id from person where person_id=?";
							stmt = conn.prepareStatement(str1);
							stmt.setString(1,id );
							rs = stmt.executeQuery();
							String instructor1="";
							if (rs.next()) 
							{
								instructor1 = rs.getString("person_id");
							}
							rs.close();
							if(instructor1.equals(""))
							{
								String str = "update person set person_id = ? where person_id = ?";
							
								stmt = conn.prepareStatement(str);
								stmt.setString(2,person_id );
								stmt.setString(1,id);
			
								stmt.executeUpdate();					
								conn.close();					
								System.out.println("Disconnected from Database");
							}
							else
								throw new ExceptionClass(UniversityExceptionList.person_exists);
						}
						else
							throw new ExceptionClass(UniversityExceptionList.no_such_person);
				}catch (SQLException e) 
				{
					throw new ExceptionClass(UniversityExceptionList.general_exception);
				}
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);	
	}
}
