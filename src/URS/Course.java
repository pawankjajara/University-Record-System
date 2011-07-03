package URS;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class Course 
{
	private Person st = new Person();
	private Student s = new Student();
	Location loc1=new Location();
	ValidationClass validate=new ValidationClass();
	private static Scanner scanner = new Scanner(System.in);
	private HelperClass help = new HelperClass(); 
	private Connection conn;

	// 1. to add a course
	public void create_Course(String s1,String s2,String s3,int s4,String s5) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		if(!validate.validate_course(s1,s2))
		{
			// malformed units
			if(validate.validate_units(Integer.parseInt(s5)))
			{				
				PreparedStatement stmt = null;
				String str = "insert into course (course_name,course_section,instructor_id,location_id,units) values (?, ?, ?, ?, ?)";

				try {
					stmt = conn.prepareStatement(str);
					stmt.setString(1, s1);
					stmt.setString(2, s2);
					stmt.setString(3, s3);
					stmt.setInt(4, s4);
					stmt.setString(5, s5);
					stmt.executeUpdate();

					conn.close();
					System.out.println("Course Record Added");
					System.out.println("Disconnected from Database");
				} catch (SQLException e) 
				{
					throw new ExceptionClass(UniversityExceptionList.create_exception);
				}

			}
			else throw new ExceptionClass(UniversityExceptionList.course_exists);
		}
		else
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_units);
		}

	}


	//2. to remove 

	public void remove_Course(String s1,String s2,String s3) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		try{		

			if(validate.validate_course(s1,s2))
			{
				//System.out.println("Forcefully Unenroll? 1. Yes 0. No");
				//int choice = scanner.nextInt();
				PreparedStatement stmt = null;
				ResultSet rs = null;
				int choice = Integer.parseInt(s3);
				System.out.println("choice is"+choice);

				if(choice ==1)
				{
					// chk if it has students;if yes delete the mapping

					try {
						String sqlDeleteCourse = "delete from course where course_name = ? and course_section = ?";
						stmt = conn.prepareStatement(sqlDeleteCourse);
						stmt.setString(1,s1);
						stmt.setString(2,s2);
						stmt.executeUpdate();

						sqlDeleteCourse = "delete from student_course where course_name = ? and course_section = ?";
						stmt = conn.prepareStatement(sqlDeleteCourse);
						stmt.setString(1, s1);
						stmt.setString(1, s2);
						stmt.executeUpdate();

					} catch (SQLException e) 
					{
						//System.out.println(“Error”);
					}


					conn.close();
					System.out.println("Course deleted successfully");
					System.out.println("Disconnected from Database");
				} 

				else if (choice ==0){
					// throw a exception if it has students
					String str = "select count(2) cnt from student_course where course_name = ? and course_section = ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, s1);
					stmt.setString(2,s2);
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
						System.out.println("courses has students enrolled.");
						throw new ExceptionClass(UniversityExceptionList.has_students);
					}
					else
					{
						System.out.println("course has no students enrolled. So deleted");
						String sqlDeleteCourse = "delete from course where course_name = ? and course_section = ?";
						stmt = conn.prepareStatement(sqlDeleteCourse);
						stmt.setString(1,s1);
						stmt.setString(2,s2);
						stmt.executeUpdate();
						conn.close();
					}

				}
				else
				{
					System.out.println("Wrong Input!!");
					throw new ExceptionClass(UniversityExceptionList.general_exception);
				}
			} // end of validate if
			else
			{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			throw new ExceptionClass(UniversityExceptionList.remove_exception);
		}
	}






	//3. to get course units
	public ArrayList<String> get_Course_Units(String s1,String s2) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		ArrayList<String> reply = new ArrayList<String>();
		//int units=0;
		try{		
			if(validate.validate_course(s1,s2))
			{

				PreparedStatement stmt = null;
				String sql = "select units from course where course_name = ? and course_section = ?";
				ResultSet rs = null;
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,s1);
					stmt.setString(2,s2);

					rs = stmt.executeQuery();
					while(rs.next()) 
					{
						reply.add(rs.getString("units"));	
					}
					rs.close();
				} catch (SQLException e) 
				{		
					e.printStackTrace();
				}
			} // end of validate if
			else
			{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}
		return reply;         
	}

	//4. to set course units
	public void set_Course_Units(String s1,String s2,String s3) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		try{		
			if(validate.validate_course(s1,s2))
			{
				if(validate.validate_units(Integer.parseInt(s3)))
				{
					int units =Integer.parseInt(s3);
					System.out.println("units in course is "+units);
					PreparedStatement stmt = null;
					String updatesql = "update course set units=? where course_name = ? and course_section = ?";
					//ResultSet rs = null;
					try {
						stmt = conn.prepareStatement(updatesql);
						stmt.setString(1,s3);
						stmt.setString(2,s1);
						stmt.setString(3,s2);

						stmt.executeUpdate();
						
						
						updatesql = "update student set enrolled_units=enrolled_units+? where student_id in(select student_id from student_courses where course_name=? and course_section=?)";
						stmt = conn.prepareStatement(updatesql);
						stmt.setString(1,s3);
						stmt.setString(2,s1);
						stmt.setString(3,s2);
						stmt.executeUpdate();
						conn.close();
					} catch (SQLException e) 
					{		
						e.printStackTrace();
					}


				}// end of validate if
				else
				{
					throw new ExceptionClass(UniversityExceptionList.malformed_units);
				}
			}

			else
			{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}            
	}


	//5. to get course instructor
	public ArrayList<String> get_Course_Instructor(String s1,String s2) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		//int ins_id=0;
		ArrayList<String> reply = new ArrayList<String>();
		try{		
			if(validate.validate_course(s1,s2))
			{
				PreparedStatement stmt = null;
				String sql = "select instructor_id from course where course_name = ? and course_section = ?";
				ResultSet rs = null;
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,s1);
					stmt.setString(2,s2);

					rs = stmt.executeQuery();
					while (rs.next()) 
					{
						reply.add(rs.getString("instructor_id"));	
					}
					rs.close();
				} catch (SQLException e) 
				{		
					e.printStackTrace();
				}
			}
			else
			{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}
		return reply;         
	}


	//6. to set course instructor
	public void set_Course_Instructor(String s1,String s2,String s3) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();

		try{	
			PreparedStatement stmt = null;
			ResultSet rs = null;

			String str = "select building, room_number, days, start_time, end_time from location where location_id = (select location_id from course where course_name= ? and course_section= ? )";
			stmt = conn.prepareStatement(str);                                                        
			stmt.setString(1, s2);
			stmt.setString(2, s3);
			rs = stmt.executeQuery();
			String course_loc="";
			while (rs.next()) 
			{
				course_loc=rs.getString("building")+" "+rs.getString("room_number")+" "+rs.getString("days")+" "+rs.getString("start_time")+" "+rs.getString("end_time");
			}
			rs.close();
			Location loc1=new Location();                                                        
			Location course_loc1=loc1.loc_Split(course_loc);




			if(validate.validate_instructor(s1))
			{
				if(validate.validateID(s1))
				{
					if(validate.validate_course(s2,s3))
					{			

						if(validate.schedule_conflict_check(course_loc1,s1))
						{

							String updatesql = "update course set instructor_id=? where course_name = ? and course_section = ?";
							//ResultSet rs = null;
							try {
								stmt = conn.prepareStatement(updatesql);
								stmt.setString(1,s1);
								stmt.setString(2,s2);
								stmt.setString(3,s3);

								stmt.executeUpdate();

								conn.close();
							} catch (SQLException e) 
							{		
								e.printStackTrace();
							}
						}
						else
						{
							throw new ExceptionClass(UniversityExceptionList.schedule_conflict);
						}
					}

					else
					{	
						throw new ExceptionClass(UniversityExceptionList.no_such_course);
					}
				} 		
				else{
					throw new ExceptionClass(UniversityExceptionList.malformed_id);
				}
			}
			else
				throw new ExceptionClass(UniversityExceptionList.no_such_person);

		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}            
	}

	//7 . to set course instructor
	public void set_Course_Name(String s1,String s2,String s3) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		try{		
			if(validate.validate_course(s1,s2))
			{
				if(!validate.validate_course(s3,s2))
				{

					PreparedStatement stmt = null;
					String updatesql = "update course set course_name=? where course_name = ? and course_section = ?";
					//ResultSet rs = null;
					try {
						stmt = conn.prepareStatement(updatesql);
						stmt.setString(1,s3);
						stmt.setString(2,s1);
						stmt.setString(3,s2);

						stmt.executeUpdate();

						conn.close();
					} catch (SQLException e) 
					{		
						e.printStackTrace();
					}
				}
				else throw new ExceptionClass(UniversityExceptionList.course_exists);

			}
			else{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}            
	}

	//8. to set course section
	public void set_Course_Section(String s1,String s2,String s3) throws ExceptionClass
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		try{		
			if(validate.validate_course(s1,s2))
			{
				if(!validate.validate_course(s1,s3))
				{
					PreparedStatement stmt = null;
					String updatesql = "update course set course_section=? where course_name = ? and course_section = ?";
					//ResultSet rs = null;
					try {
						stmt = conn.prepareStatement(updatesql);
						stmt.setString(1,s3);
						stmt.setString(2,s1);
						stmt.setString(3,s2);

						stmt.executeUpdate();

						conn.close();
					} catch (SQLException e) 
					{		
						e.printStackTrace();
					}
				}
				else throw new ExceptionClass(UniversityExceptionList.course_exists);

			}
			else{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}            
	}

	//9. to get location
	public ArrayList<String> get_Location(String s1,String s2) throws ExceptionClass
	{
		ArrayList<String> reply = new ArrayList<String>();
		String building=null,room_number=null,days=null,start_time=null,end_time=null;
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		//int units=0;
		try{		
			if(validate.validate_course(s1,s2))
			{
				PreparedStatement stmt = null;
				String sql = "select l.building,l.room_number,l.days,l.start_time,l.end_time from course as c join location as l on c.location_id=l.location_id where course_name = ? and course_section = ?";
				ResultSet rs = null;
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1,s1);
					stmt.setString(2,s2);

					rs = stmt.executeQuery();

					while(rs.next()) 
					{
						reply.add(rs.getString("building")+" "+rs.getString("room_number")+" "+rs.getString("days")+" "+rs.getString("start_time")+" "+rs.getString("end_time"));

					}
					rs.close();

				} catch (SQLException e) 
				{		
					e.printStackTrace();
				}
			}
			else{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}
		}
		catch(Exception e){
			System.out.println("Exception Generated: "+e);				
		}

		return reply;         
	}

	//10. to set location 

	public void set_Location(String s1,String s2,String s3) throws ExceptionClass
	{
		int location=0,locold=0;
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();	
		
		try{
			if(validate.validate_course(s2,s3))
			{	
				Location list2 = loc1.loc_Split(s1);
				// malformed location
				if(validate.validate_malformedlocation(list2))
				{
					System.out.println("Location good");
					System.out.println("Before validate hours");
					if(validate.validate_hours(list2.getDays()+" "+list2.getStart_time()+"-"+list2.getEnd_time()))
					{
						
						if(validate.room_booked_conflict_check(list2))		
						{	
							// for has students exception
							PreparedStatement stmt = null;
							ResultSet rs = null;
							String str = "select count(2) cnt from student_course where course_name = ? and course_section = ?";
							stmt = conn.prepareStatement(str);
							stmt.setString(1, s2);
							stmt.setString(2,s3);
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
								System.out.println("courses has students enrolled.");
								throw new ExceptionClass(UniversityExceptionList.has_students);
							}
							
							else{
								//for schedule conflcit of instructor
								stmt = null;
								String ins_id="";
								String sql = "select instructor_id from course where course_name = ? and course_section = ?";
								rs = null;
								stmt = conn.prepareStatement(sql);
								stmt.setString(1,s2);
								stmt.setString(2,s3);

									rs = stmt.executeQuery();
									while(rs.next()) 
									{
										ins_id = (rs.getString("instructor_id"));	
									}
									
							if(validate.schedule_conflict_check(list2, ins_id))	
							{
								
							 
							String Building = list2.getBuilding();
							
							String Room = list2.getRoom_number();
							String Days = list2.getDays();
							String StartTime = list2.getStart_time();
							String EndTime = list2.getEnd_time();
							
								
							//to get location id frm loc table
							 str = "select location_id from location where building = ? and room_number= ? and days = ? and start_time = ? and end_time = ?";
							stmt = conn.prepareStatement(str);
							stmt.setString(1,Building);
							stmt.setString(2,Room);
							stmt.setString(3,Days);
							stmt.setString(4,StartTime);
							stmt.setString(5,EndTime);
							rs = stmt.executeQuery();
							while(rs.next())
							{
								location = rs.getInt("location_id");
								System.out.println("Location is :"+location);
							}

							if(location == 0){
								help.create_Location(Building,Room,Days,StartTime,EndTime);
								location = help.get_LocationId(Building,Room,Days,StartTime,EndTime);
								System.out.println("Location is after create:"+location);
								conn=db.ConnectToDB();
							}


							// select the old value from course table nd delete tht value
							str = "select location_id from course where course_name = ? and course_section = ?";
							stmt = conn.prepareStatement(str);
							stmt.setString(1,s2);
							stmt.setString(2,s3);
							rs = stmt.executeQuery();
							while(rs.next()) 
							{
								locold = rs.getInt("location_id");
								System.out.println("Old loc is"+locold);
							}
							rs.close();





							str = "update course set location_id=? where course_name = ? and course_section = ?";
							//ResultSet rs = null;
							stmt = conn.prepareStatement(str);
							stmt.setInt(1,location);
							stmt.setString(2,s2);
							stmt.setString(3,s3);
							stmt.executeUpdate();
							System.out.println("Course Record Added");


							//delete old loc frm db

							str = "delete from location where location_id = ?";	
							stmt = conn.prepareStatement(str);
							stmt.setInt(1,locold);
							stmt.executeUpdate();
							System.out.println("Deleted Record Added");

							conn.close();
							}
							else{
								  throw new ExceptionClass(UniversityExceptionList.schedule_conflict);
								}
							
							
							} // end of new else
						} // end of room_book_conflict
						else
						{
							throw new ExceptionClass(UniversityExceptionList.room_booked);
						}
					}// end of validate hrs if
					else
					{
						throw new ExceptionClass(UniversityExceptionList.malformed_hours);
					}
				} // location if end
				else
				{
					throw new ExceptionClass(UniversityExceptionList.malformed_location);
				}
			}
			else{	
				throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}

		} catch (SQLException e) 
		{		
			e.printStackTrace();
		}


	}

	//11. to find all courses
	public ArrayList<String> find_All_Courses()
	{			
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();	
		ArrayList<String> reply = new ArrayList<String>();
		try
		{				
			PreparedStatement stmt = null;
			ResultSet rs = null;			
			String str = "select course_name,course_section from course";
			stmt = conn.prepareStatement(str);			
			rs = stmt.executeQuery();		
			while(rs.next())
			{
				reply.add(rs.getString("course_name")+ " "+rs.getString("course_section"));	
				//reply.add(rs.getString("course_section"));	

			}
			conn.close();
		}
		catch(Exception e) 
		{
			System.out.println("Exception Generated: "+e);		
		}
		return reply;
	}

	//12. to find all courses by location
	public ArrayList<String> find_Courses_By_Location(String s1) throws ExceptionClass
	{			

		int location=0;
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();	
		ArrayList<String> reply = new ArrayList<String>();
			

		try
		{		
			Location list2 = loc1.loc_Split(s1);
			// malformed location
			if(validate.validate_malformedlocation(list2))
			{
				
				String Building = list2.getBuilding();
				String Room = list2.getRoom_number();
				String Days = list2.getDays();
				String StartTime = list2.getStart_time();
				String EndTime = list2.getEnd_time();
				PreparedStatement stmt = null;
				ResultSet rs = null;		
				//to get location id frm loc table
				String str = "select location_id from location where building = ? and room_number= ? and days = ? and start_time = ? and end_time = ?";
				try {
					stmt = conn.prepareStatement(str);
					stmt.setString(1,Building);
					stmt.setString(2,Room);
					stmt.setString(3,Days);
					stmt.setString(4,StartTime);
					stmt.setString(5,EndTime);
					rs = stmt.executeQuery();
					while(rs.next())
					{
						location = rs.getInt("location_id");
					}
					System.out.println("Location is :"+location);

				} catch (SQLException e) 
				{
					//System.out.println(“Error”);
				}

				//str = "select c.course_name,c.course_section from course as c join location as l on l.location_id=c.location_id where c.location_id = ?";
				str = "select course_name,course_section from course where location_id = ?";
				stmt = conn.prepareStatement(str);
				stmt.setInt(1,location);
				rs = stmt.executeQuery();		
				while(rs.next())
				{
					reply.add(rs.getString("course_name")+ " "+rs.getString("course_section"));	
					
				}
				conn.close();
			} // location if end
			else
			{
				throw new ExceptionClass(UniversityExceptionList.malformed_location);
			}
		}

		catch(Exception e) 
		{
			System.out.println("Exception Generated: "+e);		
		}
		return reply;
	}

	// 13. to find course by course name
	public ArrayList<String> find_Courses_By_Course_Name(String s1) throws ExceptionClass
	{			


		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();	
		ArrayList<String> reply = new ArrayList<String>();
		try
		{			
			PreparedStatement stmt = null;
			ResultSet rs = null;		
			String str = "select course_name,course_section from course where course_name = ?";
			stmt = conn.prepareStatement(str);
			stmt.setString(1,s1);
			rs = stmt.executeQuery();		
			while(rs.next())
			{
				reply.add(rs.getString("course_name")+ " "+rs.getString("course_section"));	
			}
			conn.close();
		}
		catch(Exception e) 
		{
			System.out.println("Exception Generated: "+e);
			throw new ExceptionClass(UniversityExceptionList.no_such_course);
		}
		return reply;
	}

	//14 . calculate bill
	public ArrayList<String> Calculate_Bill(String s1) throws ExceptionClass
	{			


		System.out.println("in cal billl");	
		ArrayList<String> reply = new ArrayList<String>();
		ArrayList<String> reply2 = new ArrayList<String>();
		ArrayList<String> reply1 = new ArrayList<String>();
		int units=0;
		String state="";

		try{
			if(validate.validateID(s1))
			{
				if(validate.validate_student(s1))
				{

					try {
						reply = st.Get_State(s1);
						reply2 = s.Get_Enrolled_Units(s1);

					} catch (ExceptionClass e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					for(Iterator<String> i=reply2.iterator();i.hasNext();)
					{
						units =Integer.parseInt(i.next());
						//state = i.next();
					}
					for(Iterator<String> i=reply.iterator();i.hasNext();)
					{

						state = i.next();
					}



					System.out.println("units is "+units);
					System.out.println("State is "+state);
					//int units = Integer.parseInt(reply.get(0));
					//System.out.println(reply.get(1));
					//String state = reply.get(1);
					double bill = 0;

					if(state.equalsIgnoreCase("ca"))
					{
						if(units<6)
						{
							bill = 456 + 243.6;
						}
						else 
						{
							bill = 786 + 243.6;
						}
					}
					else{
						bill = (282 * units) + 243.6;
					}

					String sbill = Double.toString(bill);
					reply1.add(""+sbill);
					System.out.println("Bill is:"+bill);

				} 		
				else{
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
					
				    }
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
			catch(Exception e) 
			{
				System.out.println("Exception Generated: "+e);		
			}


			return reply1;
		}	



		//15. get students 
		public ArrayList<String> get_Students(String s1,String s2) throws ExceptionClass
		{			
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();	
			ArrayList<String> reply = new ArrayList<String>();
			try
			{	
				if(validate.validate_course(s1,s2))
				{
					PreparedStatement stmt = null;
					ResultSet rs = null;			
					String str = "select student_id from student_course where course_name=? and course_section=?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1,s1);
					stmt.setString(2,s2);
					rs = stmt.executeQuery();			

					while(rs.next())
					{
						reply.add(rs.getString("student_id"));	

					}
					conn.close();
				}
				else throw new ExceptionClass(UniversityExceptionList.no_such_course);
			}

			catch(Exception e) 
			{
				System.out.println("Exception Generated: "+e);		
			}
			return reply;
		}
		
		//16.find courses by inst
		public ArrayList<String> Find_Courses_By_Instructor(String s1) throws ExceptionClass
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
					if(validate.validate_instructor(s1))
					{
                      
						String str="select course_name,course_section from course where instructor_id= ?";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, s1);
						rs = stmt.executeQuery();		
						while(rs.next())
						{
							reply.add(rs.getString("course_name") + " " +rs.getString("course_section"));
						}
						conn.close();
					}
					else{
						throw new ExceptionClass(UniversityExceptionList.no_such_person);
						}
				}
					else{
						throw new ExceptionClass(UniversityExceptionList.malformed_id);
					    }
					}
			catch(Exception e) 
			{
				System.out.println("Exception Generated: "+e);		
			}
			return reply;
		}  
		

	}
