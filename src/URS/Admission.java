package URS;
//import ExceptionClass;
//import UniversityExceptionList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;


public class Admission 
{
	ValidationClass validate = new ValidationClass();
	private Connection conn;
	
	public void Enroll_Student(String stud_id, String coursename, String coursesection)throws ExceptionClass	
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		String str;
		int enrolled_units=0;
		int new_units=0;
		try
		{	
			if(validate.validateID(stud_id))
			{
				if(validate.validate_student(stud_id))
				{
					if(validate.validate_course(coursename, coursesection))
					{
						PreparedStatement stmt = null;
						ResultSet rs = null;					
					str = "select enrolled_units from student where student_id= ?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, stud_id);			

					rs = stmt.executeQuery();
					if (rs.next()) 
					{
						enrolled_units = rs.getInt("enrolled_units");
					}
					rs.close();
					
					
					System.out.println("Enrolled units : " + enrolled_units);
					str="select units from course where course_name= ? and course_section=?";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, coursename);
					stmt.setString(2, coursesection);
					rs = stmt.executeQuery();						
						
					if (rs.next()) 
					{
						new_units = rs.getInt("units");
					}
					rs.close();
					
					
					System.out.println("COurse units : "+ new_units);
					enrolled_units=enrolled_units+new_units;
					System.out.println("Added Enrolled units: "+ enrolled_units);
					if(enrolled_units>24)
						throw new ExceptionClass(UniversityExceptionList.too_many_units);
					else
					{
						str = "select student_id from student_course where student_id= ? and course_name= ? and course_section= ? ";
						stmt = conn.prepareStatement(str);
						stmt.setString(1, stud_id);
						stmt.setString(2, coursename);
						stmt.setString(3, coursesection);
						rs = stmt.executeQuery();
						String id1="";
						if (rs.next()) 
						{
							id1 = rs.getString("student_id");
						}
						rs.close();
						System.out.println("Is enrolled : " +id1);
						if(id1.equals(""))
						{
							System.out.println("Not enrolled");
							str = "select building, room_number, days, start_time, end_time from location where location_id = (select location_id from course where course_name= ? and course_section= ? )";
							stmt = conn.prepareStatement(str);							
							stmt.setString(1, coursename);
							stmt.setString(2, coursesection);
							rs = stmt.executeQuery();
							String course_loc="";
							if (rs.next()) 
							{
								course_loc=rs.getString("building")+" "+rs.getString("room_number")+" "+rs.getString("days")+" "+rs.getString("start_time")+"-"+rs.getString("end_time");
							}
							rs.close();
							Location loc1=new Location();							
							Location course_loc1=loc1.loc_Split(course_loc);
								
							if(validate.schedule_conflict_check(course_loc1, stud_id))
							{
								System.out.println("No conflict");
								str = "insert into student_course (student_id, course_name,course_section) values (?, ?, ?)";
								stmt = conn.prepareStatement(str);
								stmt.setString(1, stud_id);
								stmt.setString(2, coursename);
								stmt.setString(3, coursesection);
								stmt.executeUpdate();
								
								str = "update student set enrolled_units = ? where student_id = ?";																								
								stmt = conn.prepareStatement(str);
								stmt.setInt(1, enrolled_units);
								stmt.setString(2, stud_id);								
								stmt.executeUpdate();
								System.out.println("Student enrolled");
								conn.close();
							}
							else
								throw new ExceptionClass(UniversityExceptionList.schedule_conflict);
						}
						else
							throw new ExceptionClass(UniversityExceptionList.student_already_enrolled);
						}					
					}
					
					else
						throw new ExceptionClass(UniversityExceptionList.no_such_course);
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
	}
	
	public void Unenroll_Student(String student_id,String course_name,String course_section) throws ExceptionClass
	{
		try
		{

			if(validate.validateID(student_id))
			{
				DBConnection db = new DBConnection();
				conn=db.ConnectToDB();
				System.out.println("Connecting" );
				PreparedStatement stmt = null;
				ResultSet rs = null;
				//if student present
				String str = "select count(1) cnt from student where student_id = ?";
				stmt = conn.prepareStatement(str);
				stmt.setString(1, student_id);
				rs = stmt.executeQuery();
				int cnt=0;
				
				while(rs.next())
				{
					cnt=rs.getInt("cnt");
				}
				if(cnt>0)
				{
					//check if course present in course tbl
					str = "select course_name from course where course_name = ? ";
					stmt = conn.prepareStatement(str);
					stmt.setString(1, course_name);
					String course_exist ="";
					rs = stmt.executeQuery();
					while(rs.next())
					{
						course_exist=rs.getString("course_name");
					}
					if(!course_exist.equals(""))
					{
						System.out.println("course exists in course db");
						str = "select student_id from student_course where student_id = ?" +
								"and course_name =? and course_section=?";
						
						stmt = conn.prepareStatement(str);
						stmt.setString(1, student_id);
						stmt.setString(2, course_name);
						stmt.setString(3, course_section);
						String student ="";
						rs = stmt.executeQuery();
						int count=0,enrolled_units=0,units=0;
						
						while(rs.next())//get number of courses
						{
							student=rs.getString(1);
						}
						if(!student.equals(""))//student enrolled & present in student_course
						{
							//subtract enrolled units for that student
							str = "select enrolled_units from student where student_id = ?";
							stmt = conn.prepareStatement(str);
							stmt.setString(1, student_id);
							rs = stmt.executeQuery();
							while(rs.next())
							{
								enrolled_units=rs.getInt("enrolled_units");
							}
							System.out.println("Enrolled units are "+ enrolled_units);
							//get units for those courses to unenroll
							str = "select units from course c, student_course sc where c.course_name = sc.course_name AND sc.course_name = ? ";
							stmt = conn.prepareStatement(str);
							stmt.setString(1, course_name);
							rs = stmt.executeQuery();
							while(rs.next())	
							{
								units= rs.getInt("units");
							}
							System.out.println("units for that course are "+ units);
							
							enrolled_units = enrolled_units- units;
							
							System.out.println("After subtraction Enrolled units are"+ enrolled_units);
													
							str = "delete from student_course where student_id = ? and course_name = ? and course_section = ? ";
							stmt = conn.prepareStatement(str);
							stmt.setString(1, student_id);
							stmt.setString(2, course_name);
							stmt.setString(3, course_section);
													
							stmt.executeUpdate();
							
							str = "update student set enrolled_units = ? where student_id = ?";
							stmt = conn.prepareStatement(str);
							stmt.setString(2, student_id);
							stmt.setInt(1, enrolled_units);
							
							stmt.executeUpdate();
							rs.close();
							conn.close();
							System.out.println("Student now Unenrolled");
							System.out.println("Disconnected from Database");
							
						}
						else
						{
							System.out.println("Student not yet enrolled");
							throw new ExceptionClass(UniversityExceptionList.student_not_enrolled);
						}
					}
					else
						throw new ExceptionClass(UniversityExceptionList.no_such_course);
				}
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
		catch(SQLException e)
		{
			System.out.println("Error"+ e);
		}
	}
	
	
}

















