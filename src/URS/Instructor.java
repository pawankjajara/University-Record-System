package URS;
import java.sql.*;
import java.util.*;


public class Instructor 
{
	private Connection conn;
	ValidationClass validate=new ValidationClass();
	HelperClass helper = new HelperClass();
	
	public void Create_Instructor(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8)throws ExceptionClass
	{
		if(validate.validateID(s1))
		{
			System.out.println("ID good");
			if(validate.validateState(s6))
			{
				if(validate.validateZip(s7))
				{
					DBConnection db = new DBConnection();
					conn=db.ConnectToDB();				
					
					try{										
						PreparedStatement stmt = null;
						ResultSet rs = null;									
						if(!validate.validate_person(s1))							
						{			
							String str = "insert into person (person_id, first_name, last_name, address, city, state, zip, role) values (?, ?, ?, ?, ?, ?, ?, ?)";				
							stmt = conn.prepareStatement(str);
							stmt.setString(1, s1);
							stmt.setString(2, s2);
							stmt.setString(3, s3);
							stmt.setString(4, s4);
							stmt.setString(5, s5);
							stmt.setString(6, s6);
							stmt.setString(7, s7);
							stmt.setString(8, "I");
							stmt.executeUpdate();
			 																		
							str = "insert into instructor values (?, ?)";			
							stmt = conn.prepareStatement(str);			
							stmt.setString(1, s1);
							stmt.setString(2, s8);
							stmt.executeUpdate();
							conn.close();
							System.out.println("Record Added");
							System.out.println("Disconnected from Database");
						}
						else
						{
							throw new ExceptionClass(UniversityExceptionList.person_exists);
						}
					} catch (SQLException e) 
					{
						throw new ExceptionClass(UniversityExceptionList.create_exception);
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
	
	public void Set_Department(String s1,String s2)throws ExceptionClass
	{
		if(validate.validateID(s1))
		{						
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			try
			{				
				PreparedStatement stmt = null;
				ResultSet rs = null;			
							
				if(validate.validate_instructor(s1))						
				{
					String str = "update instructor set department = ? where instructor_id = ? ";		
					
					stmt = conn.prepareStatement(str);
					stmt.setString(1, s2);
					stmt.setString(2, s1);		
					
					stmt.executeUpdate();
					
					System.out.println("Record Updated");
					System.out.println("Disconnected from Database");
					conn.close();
				}	
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);			
			}catch(SQLException e) 
			{
				System.out.println(e);
			}
		}
		else
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
	}

	public ArrayList<String> Get_Department(String s1)throws ExceptionClass
	{
		String dept="";
		ArrayList<String> reply=new ArrayList();
		if(validate.validateID(s1))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
		
			try
			{				
				PreparedStatement stmt = null;
				ResultSet rs = null,rs1=null;										
				if(validate.validate_instructor(s1))						
				{
					String str = "select department dept from instructor where instructor_id = ? ";		
					
					stmt = conn.prepareStatement(str);				
					stmt.setString(1, s1);		
					
					rs1 = stmt.executeQuery();
					while(rs1.next())
					{
						dept=rs1.getString("dept");
					}
					System.out.println("Record Retrieved ");
					System.out.println("Disconnected from Database");
					conn.close();					
                    reply.add(dept);                    
					return reply;
				}	
				else
					throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}catch(SQLException e) 
			{
				System.out.println(e);
			}
		}
		else
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
		return reply;
	}			
	
	public ArrayList<String> Find_Instructors_By_Department(String s1)throws ExceptionClass
	{		
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		ArrayList<String> reply = new ArrayList<String>();
		try
		{				
			PreparedStatement stmt = null;
			ResultSet rs = null;						
			
			String str="select instructor_id from instructor where department= ?";
			stmt = conn.prepareStatement(str);
			stmt.setString(1, s1);
			rs = stmt.executeQuery();		
			while(rs.next())
			{				
				reply.add(rs.getString("instructor_id"));
			}			
			conn.close();											
		}catch(SQLException e) 
		{
			System.out.println(e);
		}	
		return reply;
	}
		
	public void Add_Office_Hours(String s1,String s2)throws ExceptionClass
	{		
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		Location loc1=new Location();		
		Location user_loc=loc1.loc_Split(s2);
		try{
		System.out.println("Before validate location");
		if(validate.validateID(s1))
		{			
			System.out.println("Before validate inst");
			if(validate.validate_instructor(s1))
			{
				if(validate.validate_location(user_loc,s1))
				{
					System.out.println("Location good");
					System.out.println("Before validate hours");
					if(validate.validate_hours(user_loc.getDays()+" "+user_loc.getStart_time()+"-"+user_loc.getEnd_time()))
					{						
						PreparedStatement stmt = null;
						ResultSet rs = null;																				
						System.out.println("Before scehudule conflic");
						if(validate.schedule_conflict_check(user_loc, s1))
						{
							System.out.println("Before validate room");					
							if(validate.room_booked_conflict_check(user_loc))		
							{
								ArrayList<String> loc = helper.Split(s2);
								int count=loc.size();
								helper.create_Location(loc.get(0),loc.get(1),loc.get(2),loc.get(3),loc.get(4));										
								String str = "select location_id from location where building=? and room_number= ? and days= ? and start_time=? and end_time=?";
								
								stmt = conn.prepareStatement(str);
								stmt.setString(1, loc.get(0));
								stmt.setString(2, loc.get(1));
								stmt.setString(3, loc.get(2));
								stmt.setString(4, loc.get(3));
								stmt.setString(5, loc.get(4));
								rs = stmt.executeQuery();
								int location_id=0;
								while(rs.next())
								{				
									location_id=rs.getInt("location_id");
								}
										
								str = "insert into instructor_location (instructor_id, location_id) values (?, ?)";		
								stmt = conn.prepareStatement(str);
								stmt.setString(1, s1);
								stmt.setInt(2, location_id);			
								stmt.executeUpdate();
								conn.close();
							}
							else
							{
								throw new ExceptionClass(UniversityExceptionList.room_booked);
							}
						}
						else
						{
							throw new ExceptionClass(UniversityExceptionList.schedule_conflict);
						}
					}
					else
						throw new ExceptionClass(UniversityExceptionList.malformed_hours);						
				}
				else
				{
					throw new ExceptionClass(UniversityExceptionList.malformed_location);					
				}
			}
			else
			{
				throw new ExceptionClass(UniversityExceptionList.no_such_person);
			}
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}catch(SQLException e)
		{
			System.out.println(e);
		}		
	}
	
	public ArrayList<String> Get_Office_Hours(String s1)throws ExceptionClass
	{		
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		ArrayList<String> reply = new ArrayList();
		try
		{							
			if(validate.validateID(s1))
			{
				if(validate.validate_instructor(s1))
				{
					PreparedStatement stmt = null;
					ResultSet rs = null;						
					String str = "select building, room_number, days, start_time, end_time from location where location_id in (select location_id from instructor_location where instructor_id=?)";			
					stmt = conn.prepareStatement(str);
					stmt.setString(1, s1);			
					rs = stmt.executeQuery();			
					while(rs.next())
					{				
						reply.add(rs.getString("building")+" "+rs.getString("room_number")+" "+rs.getString("days")+" "+rs.getString("start_time")+" "+rs.getString("end_time"));
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
	
	public void Remove_Office_Hours(String s1,String s2)throws ExceptionClass
	{		
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		Location loc1=new Location();		
		Location user_loc=loc1.loc_Split(s2);
		try{
			if(validate.validateID(s1))
			{
				if(validate.validate_instructor(s1))
				{
					System.out.println("Before validate location");			
					//if(validate.validate_location(user_loc))
					{
						System.out.println("Location good");
						System.out.println("Before validate hours");
						if(validate.validate_hours(user_loc.getDays()+" "+user_loc.getStart_time()+"-"+user_loc.getEnd_time()))
						{								
							PreparedStatement stmt = null;
							ResultSet rs = null;
							ArrayList<String> loc = helper.Split(s2);
							String str = "select location_id, building, room_number, days, start_time, end_time from location where location_id in (select location_id from instructor_location where instructor_id=?)";			
							stmt = conn.prepareStatement(str);
							stmt.setString(1, s1);			
							rs = stmt.executeQuery();
							int count=0;
							int del_location=0;
							while(rs.next())
							{								
								if(loc.get(0).equals(rs.getString("building")))								
									count++;									
								if(loc.get(1).equals(rs.getString("room_number")))									
									count++;									
								if(loc.get(2).equals(rs.getString("days")))						
									count++;								
								if(loc.get(3).equals(rs.getString("start_time")))									
									count++;									
								if(loc.get(4).equals(rs.getString("end_time")))							
									count++;									
								if(count==5)
								{
									del_location=rs.getInt("location_id");
									break;
								}									
								else
									count=0;						
							}		
							if(count==5)	
							{
								str="delete from location where location_id=?";										
								stmt = conn.prepareStatement(str);
								stmt.setInt(1, del_location);				
								stmt.executeUpdate();
								conn.close();
							}						
							else
								throw new ExceptionClass(UniversityExceptionList.no_such_office_hours);											
						}
						else
							throw new ExceptionClass(UniversityExceptionList.malformed_hours);
					}
				//	else
					//	throw new ExceptionClass(UniversityExceptionList.malformed_location);
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
}
