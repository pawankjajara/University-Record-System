package URS;
import java.sql.Connection;
import java.util.regex.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.sql.*;

public class ValidationClass 
{
	public HashMap state_table = new HashMap();
	private Connection conn;
	public ValidationClass()
	{
		state_table.put("alabama", "al");
		state_table.put("alaska", "ak");
		state_table.put("arizona","az");
		state_table.put("arkansas", "ar");
		state_table.put("california","ca");
		state_table.put("colorado", "co");
		state_table.put("connecticut", "ct");
		state_table.put("delaware", "de");
		state_table.put("florida", "fl");
		state_table.put("georgia", "ga");
		state_table.put("hawaii", "hi");
		state_table.put("idaho", "id");
		state_table.put("illinois","il");
		state_table.put("indiana", "in");
		state_table.put("iowa" , "ia");
		state_table.put("kansas", "KS");
		state_table.put("kentucky", "ky");
		state_table.put("louisiana", "la");
		state_table.put("maine", "me");
		state_table.put("maryland", "md");
		state_table.put("massachusetts", "ma");
		state_table.put("michigan", "mi");
		state_table.put("minnesota", "mn");
		state_table.put("mississippi", "ms");
		state_table.put("missouri", "mo");
		state_table.put("montana", "mt");
		state_table.put("nebraska", "ne");
		state_table.put("nevada", "nv");
		state_table.put("new hampshire", "nh");
		state_table.put("new jersey", "nj");
		state_table.put("new mexico", "nm");
		state_table.put("new york", "ny");
		state_table.put("north carolina", "nc");
		state_table.put("north dakota", "nd");
		state_table.put("ohio", "oh");
		state_table.put("oklahoma", "ok");
		state_table.put("oregon", "or");
		state_table.put("pennsylvania", "pa");
		state_table.put("rhode island", "ri");
		state_table.put("south carolina", "sc");
		state_table.put("south dakota", "sd");
		state_table.put("tennessee", "tn");
		state_table.put("texas", "tx");
		state_table.put("utah", "ut");
		state_table.put("vermont", "vt");
		state_table.put("virginia", "va");
		state_table.put("washington", "wa");
		state_table.put("west virginia", "wv");
		state_table.put("wisconsin", "wi");
		state_table.put("wyoming", "wy");
	}
	public boolean validateState(String state) throws ExceptionClass
	{
		if(!state.equals(null))
		{
			if(state_table.containsKey(state.toLowerCase())||state_table.containsValue(state.toLowerCase()))
				return true;
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_state);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_state);
	}

	public boolean validateID (String id) throws ExceptionClass 
	{
		int id_part1,id_part2,id_part3;
		if(id==null||id.length()!=11)
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
		if((id.substring(3, 4).equals("-"))&&(id.substring(6, 7).equals("-")))
		{
			try{
				id_part1 = Integer.parseInt(id.substring(0, 3));
				id_part2 = Integer.parseInt(id.substring(4,6));
				id_part3 = Integer.parseInt(id.substring(7,11));
			}
			catch(NumberFormatException nfe)
			{
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
			}
			if(((id_part1<=999 && id_part1>=0)&&(id_part2<=99 && id_part2>=0)&&(id_part3<=9999 && id_part3>=0)))
			{
				return true;
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_id);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_id);
	}
	public boolean validateZip(String zip) throws ExceptionClass
	{
		if(zip == null )
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_zip);
		}
		if(zip.length()==5)
		{
			try{
				if((Integer.parseInt(zip)>=00000||Integer.parseInt(zip)<=99999))
					return true;
				else
					throw new ExceptionClass(UniversityExceptionList.malformed_zip);
			}catch(NumberFormatException nfe)
			{
				throw new ExceptionClass(UniversityExceptionList.malformed_zip);
			}
		}
		else if(zip.length()==10)
		{
			if(!(zip.substring(5, 6).equals("-")))
			{
				throw new ExceptionClass(UniversityExceptionList.malformed_zip);
			}
			int zip_part1,zip_part2;
			try{
				zip_part1 = Integer.parseInt(zip.substring(0, 5));
				zip_part2 = Integer.parseInt(zip.substring(6,10));
			}
			catch(NumberFormatException nfe)
			{
				throw new ExceptionClass(UniversityExceptionList.malformed_zip);
			}
			if(((zip_part1>=00000||zip_part1<=99999)||(zip_part2>=0000||zip_part2<=9999)))
			{
				return true;
			}
			else
				throw new ExceptionClass(UniversityExceptionList.malformed_zip);
		}
		else
			throw new ExceptionClass(UniversityExceptionList.malformed_zip);
	}
	public boolean room_booked_conflict_check(Location user_loc)throws ExceptionClass
	{
		if(validate_hours(user_loc.getDays()+" "+user_loc.getStart_time()+"-"+user_loc.getEnd_time()) && !user_loc.getDays().equals("TBA"))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			Location loc = new Location();
			ArrayList<Location> loc_array = new ArrayList<Location>();
			try
			{
				PreparedStatement stmt = null;
				ResultSet rs = null;
				String str="select * from location";
				stmt = conn.prepareStatement(str);
				rs = stmt.executeQuery();
				int i=0;
				while(rs.next())
				{
					loc_array.add(loc.insert(rs));
					i++;
				}
				conn.close();
				int j=0;
				while(j<i)
				{
					System.out.println(loc_array.get(j).getStart_time());
					System.out.println(" End time: "+loc_array.get(j).getEnd_time());
					if(user_loc.getBuilding().equals(loc_array.get(j).getBuilding()) && user_loc.getRoom_number().equals(loc_array.get(j).getRoom_number()))
					{
						System.out.println("Inside building");
						if(user_loc.getDays().contains(loc_array.get(j).getDays()) || loc_array.get(j).getDays().contains(user_loc.getDays()))
						{
							System.out.println("Inside days");
							if(user_loc.getStart_time().equals(loc_array.get(j).getStart_time()) || user_loc.getEnd_time().equals(loc_array.get(j).getEnd_time()) )
							{
								System.out.println("Throwing exception");
								throw new ExceptionClass(UniversityExceptionList.room_booked); 
							}
							else if((Integer.parseInt(user_loc.getStart_time())<Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())>Integer.parseInt(loc_array.get(j).getEnd_time())) ||
									(Integer.parseInt(user_loc.getStart_time())>Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())<Integer.parseInt(loc_array.get(j).getEnd_time())) ||
									(Integer.parseInt(user_loc.getStart_time())<Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())<Integer.parseInt(loc_array.get(j).getEnd_time())) && Integer.parseInt(user_loc.getEnd_time())>Integer.parseInt(loc_array.get(j).getStart_time()) ||
									(Integer.parseInt(user_loc.getStart_time())>Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())>Integer.parseInt(loc_array.get(j).getEnd_time())) && Integer.parseInt(user_loc.getStart_time())<Integer.parseInt(loc_array.get(j).getEnd_time()))
							{
								throw new ExceptionClass(UniversityExceptionList.room_booked);
							}
						}
					}
					j++;
				}
			}catch(SQLException e) 
			{
				System.out.println(e);
			}
		}
		return true;
	}
	public boolean schedule_conflict_check(Location user_loc, String id)throws ExceptionClass
	{
		if(validate_hours(user_loc.getDays()+" "+user_loc.getStart_time()+"-"+user_loc.getEnd_time()) && !user_loc.getDays().equals("TBA"))
		{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			Location loc = new Location();
			ArrayList<Location> loc_array = new ArrayList<Location>();
			try
			{
				PreparedStatement stmt = null;
				ResultSet rs = null;
				String str1="select role from person where person_id=?";
				stmt = conn.prepareStatement(str1);
				stmt.setString(1,id);
				rs = stmt.executeQuery();
				String r="";
				while(rs.next())
				{
					r=rs.getString("role");
				}
				int i=0;
				if(r.equals("I"))
				{
					String str="select distinct location.* from location , course where (course.location_id = location.location_id and course.instructor_id = ?) ";
					stmt = conn.prepareStatement(str);
					stmt.setString(1,id);
					rs = stmt.executeQuery();
					while(rs.next())
					{
						loc_array.add(i,loc.insert(rs));
						i++;
					}
					System.out.println("After course check");
					for(int k=0;k<loc_array.size();k++)
					{
						System.out.println(""+loc_array.get(k).getStart_time());
						System.out.println(""+loc_array.get(k).getEnd_time());
					}
					str="select distinct location.* from location , instructor_location where (instructor_location.location_id = location.location_id and instructor_location.instructor_id = ?) ";
					stmt = conn.prepareStatement(str);
					stmt.setString(1,id);
					rs = stmt.executeQuery();
					while(rs.next())
					{
						loc_array.add(i,loc.insert(rs));
						i++;
					}
					System.out.println("After office hours check");
					for(int k=0;k<loc_array.size();k++)
					{
						System.out.println(""+loc_array.get(k).getStart_time());
						System.out.println(""+loc_array.get(k).getEnd_time());
					}
				}
				else
				{
					str1="select distinct location.* from location, student_course,course where (student_course.course_name=course.course_name and student_course.course_section=course.course_section and location.location_id=course.location_id and student_course.student_id=?)";
					stmt = conn.prepareStatement(str1);
					stmt.setString(1,id);
					rs = stmt.executeQuery();
					while(rs.next())
					{
						loc_array.add(i,loc.insert(rs));
						i++;
					}
					System.out.println("After course check");
					for(int k=0;k<loc_array.size();k++)
					{
						System.out.println(""+loc_array.get(k).getStart_time());
						System.out.println(""+loc_array.get(k).getEnd_time());
					}
				}
				conn.close();
				int j=0;
				while(j<i)
				{
					System.out.println(loc_array.get(j).getStart_time());
					System.out.println(" End time: "+loc_array.get(j).getEnd_time());
					//if(user_loc.getBuilding().equals(loc_array.get(j).getBuilding()) && user_loc.getRoom_number().equals(loc_array.get(j).getRoom_number()))
					//{
					System.out.println("Inside building");
					System.out.println("user days:" + user_loc.getDays());
					System.out.println("db days:" + loc_array.get(j).getDays());
					int user_length=0,db_length=0,days_flag=0;
					if(!loc_array.get(j).getDays().equals("TBA"))
					{
						user_length=user_loc.getDays().length()-1;
						while(user_length>=0 && days_flag!=1)
						{
							db_length=loc_array.get(j).getDays().length()-1;
							while(db_length>=0)
							{
								System.out.println("Char in user string: "+user_loc.getDays().charAt(user_length));
								System.out.println("Char in db string: "+loc_array.get(j).getDays().charAt(db_length));
								System.out.println("db length: "+db_length);
								System.out.println("user string length: "+user_length);
								if(loc_array.get(j).getDays().charAt(db_length)==user_loc.getDays().charAt(user_length))
								{
									System.out.println("inside 2nd while:" + days_flag);
									days_flag=1;
									break;
								}
								db_length--;
							}
							user_length--;
						}
					}
					if((user_loc.getDays().contains(loc_array.get(j).getDays()) || loc_array.get(j).getDays().contains(user_loc.getDays()) || days_flag==1) && !loc_array.get(j).getDays().equals("TBA")) 
					{
						System.out.println("Inside days");
						if(user_loc.getStart_time().equals(loc_array.get(j).getStart_time()) || user_loc.getEnd_time().equals(loc_array.get(j).getEnd_time()) )
						{
							System.out.println("Throwing exception");
							throw new ExceptionClass(UniversityExceptionList.schedule_conflict); 
						}
						else if((Integer.parseInt(user_loc.getStart_time())<Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())>Integer.parseInt(loc_array.get(j).getEnd_time())) ||
								(Integer.parseInt(user_loc.getStart_time())>Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())<Integer.parseInt(loc_array.get(j).getEnd_time())) ||
								(Integer.parseInt(user_loc.getStart_time())<Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())<Integer.parseInt(loc_array.get(j).getEnd_time())) && Integer.parseInt(user_loc.getEnd_time())>Integer.parseInt(loc_array.get(j).getStart_time()) ||
								(Integer.parseInt(user_loc.getStart_time())>Integer.parseInt(loc_array.get(j).getStart_time()) && Integer.parseInt(user_loc.getEnd_time())>Integer.parseInt(loc_array.get(j).getEnd_time())) && Integer.parseInt(user_loc.getStart_time())<Integer.parseInt(loc_array.get(j).getEnd_time()))
						{
							throw new ExceptionClass(UniversityExceptionList.schedule_conflict);
						}
					}
					//}
					j++;
				}
			}catch(SQLException e) 
			{
				System.out.println(e);
			}
			return true;
		}
		else
			return true;
	}
	public boolean validate_location(Location user_loc,String id)throws ExceptionClass
	{
		System.out.println("Building: " + user_loc.getBuilding());
		System.out.println("Room _number: " + user_loc.getRoom_number());
		System.out.println("Days: " + user_loc.getDays());
		System.out.println("Start time: " + user_loc.getStart_time());
		System.out.println("End time: " + user_loc.getEnd_time());
		if((user_loc.getBuilding().equalsIgnoreCase("TBA") && !user_loc.getRoom_number().equalsIgnoreCase("TBA")) 
				|| (!user_loc.getBuilding().equalsIgnoreCase("TBA") && user_loc.getRoom_number().equalsIgnoreCase("TBA")))
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_location);
		}
		if((user_loc.getBuilding().equalsIgnoreCase("TBA") 
				&& user_loc.getRoom_number().equalsIgnoreCase("TBA")) 
				|| (!user_loc.getBuilding().equalsIgnoreCase("TBA") 
						&& !user_loc.getRoom_number().equalsIgnoreCase("TBA")))
		{
			if(!user_loc.getDays().equalsIgnoreCase("TBA"))
			{
				if(!(user_loc.getStart_time().equals("")||user_loc.getEnd_time().equals("")))
					System.out.println(schedule_conflict_check(user_loc,id));
			}
		}
		return true;
	}
	public boolean validate_hours(String input_string)throws ExceptionClass
	{
		int flag=0;
		String pattern= "M?T?W?R?F?S?U?";
		String pattern1="[0-2][0-9][0-5][0-9]-[0-2][0-9][0-5][0-9]";
		String pattern2="[0-1][0-9][0-5][0-9]";
		String pattern3="2[0-3][0-5][0-9]";
		//String input_string = "M 2300-0230";
		String Days="",Time="",StartTime="",EndTime="";
		StringTokenizer s = new StringTokenizer(input_string);
		try{
			Days = s.nextToken();
			Time = s.nextToken();
			StringTokenizer s1 = new StringTokenizer(Time,"-");
			StartTime = s1.nextToken();
			EndTime =s1.nextToken();
		}catch(Exception e)
		{
			if(Days.equals("TBA")&&StartTime.equals("")&&EndTime.equals(""))
				flag=1;
			else
			{
				System.out.println("Element missing");
				throw new ExceptionClass(UniversityExceptionList.malformed_hours);
			}
		}
		if(flag==0)
		{
			boolean start_status=false;
			boolean end_status=false;
			if(Pattern.matches(pattern, Days))
			{
				if(Pattern.matches(pattern1, Time))
				{
					if(Integer.parseInt(StartTime)>2000)
					{
						if(Pattern.matches(pattern3, StartTime))
						{
							System.out.println("Start Time :True");
							start_status=true;
						}
						else
						{
							System.out.println("Start Time : Exceeding hours of 24");
							throw new ExceptionClass(UniversityExceptionList.malformed_hours);
						}
					}
					else if(Integer.parseInt(StartTime)<2000)
					{
						if(Pattern.matches(pattern2, StartTime))
						{
							System.out.println("Start Time: True");
							start_status=true;
						}
						else
						{
							System.out.println("Start Time: Not in proper format");
							throw new ExceptionClass(UniversityExceptionList.malformed_hours);
						}
					}
					if(Integer.parseInt(EndTime)>2000)
					{
						if(Pattern.matches(pattern3, EndTime))
						{
							System.out.println("End Time :True");
							end_status=true;
						}
						else
						{
							System.out.println("End Time : Exceeding hours of 24");
							throw new ExceptionClass(UniversityExceptionList.malformed_hours);
						}
					}
					else if(Integer.parseInt(EndTime)<2000)
					{
						if(Pattern.matches(pattern2, EndTime))
						{
							System.out.println("End Time: True");
							end_status=true;
						}
						else
						{
							System.out.println("End Time: Not in proper format");
							throw new ExceptionClass(UniversityExceptionList.malformed_hours);
						}
					}
				}
				else
				{
					System.out.println("Wrong hours format");
					throw new ExceptionClass(UniversityExceptionList.malformed_hours);
				}
			}
			else
			{
				System.out.println("Wrong days");
				throw new ExceptionClass(UniversityExceptionList.malformed_hours);
			}
			if(start_status==true && end_status==true)
				if(Integer.parseInt(StartTime)>Integer.parseInt(EndTime))
					System.out.println("End time before start time");
		}
		return true;
	}
	public boolean validate_units(int units)throws ExceptionClass
	{
		if(units<0 || units>24)
			throw new ExceptionClass(UniversityExceptionList.malformed_units);
		else
			return true;
	}
	public boolean validate_student(String stud_id)throws ExceptionClass
	{
		try{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String str1 = "select student_id from student where student_id=?";
			stmt = conn.prepareStatement(str1);
			stmt.setString(1, stud_id);
			rs = stmt.executeQuery();
			String studentid="";
			while(rs.next())
			{
				studentid=rs.getString("student_id");
			}
			if(!studentid.equals(""))
				return true;
			else
				return false;
		}catch(SQLException e)
		{
			System.out.println("Exception: "+e);
		}
		return false;
	}
	public boolean validate_instructor(String inst_id)throws ExceptionClass
	{
		try{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String str1 = "select instructor_id from instructor where instructor_id=?";
			stmt = conn.prepareStatement(str1);
			stmt.setString(1, inst_id);
			rs = stmt.executeQuery();
			String instid="";
			while(rs.next())
			{
				instid=rs.getString("instructor_id");
			}
			if(!instid.equals(""))
				return true;
			else 
				return false;
		}catch(SQLException e)
		{
			System.out.println("Exception: "+e);
		}
		return false;
	}
	public boolean validate_person(String person_id)throws ExceptionClass
	{
		try{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String str1 = "select person_id from person where person_id=?";
			stmt = conn.prepareStatement(str1);
			stmt.setString(1, person_id);
			rs = stmt.executeQuery();
			String personid="";
			while(rs.next())
			{
				personid=rs.getString("person_id");
			}
			if(!personid.equals(""))
				return true;
			else 
				return false;
		}catch(SQLException e)
		{
			System.out.println("Exception: "+e);
		}
		return false;
	}
	public boolean validate_course(String coursename, String coursesection)throws ExceptionClass
	{
		try{
			DBConnection db = new DBConnection();
			conn=db.ConnectToDB();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String str1 = "select course_name from course where course_name=? and course_section=?";
			stmt = conn.prepareStatement(str1);
			stmt.setString(1, coursename);
			stmt.setString(2, coursesection);
			rs = stmt.executeQuery();
			String courseName="";
			while(rs.next())
			{
				courseName=rs.getString("course_name");
			}
			if(!courseName.equals(""))
				return true;
			else 
				return false;
		}catch(SQLException e)
		{
			System.out.println("Exception: "+e);
		}
		return false;
	}
	
	public boolean validate_malformedlocation(Location user_loc)throws ExceptionClass
	{
		System.out.println("Building: " + user_loc.getBuilding());
		System.out.println("Room _number: " + user_loc.getRoom_number());
		System.out.println("Days: " + user_loc.getDays());
		System.out.println("Start time: " + user_loc.getStart_time());
		System.out.println("End time: " + user_loc.getEnd_time());
		if((user_loc.getBuilding().equalsIgnoreCase("TBA") && !user_loc.getRoom_number().equalsIgnoreCase("TBA")) 
				|| (!user_loc.getBuilding().equalsIgnoreCase("TBA") && user_loc.getRoom_number().equalsIgnoreCase("TBA")))
		{
			throw new ExceptionClass(UniversityExceptionList.malformed_location);
		}
		if(!user_loc.getDays().equalsIgnoreCase("TBA"))
			{
				if((user_loc.getStart_time().equalsIgnoreCase("TBA") || user_loc.getEnd_time().equalsIgnoreCase("TBA")))
					throw new ExceptionClass(UniversityExceptionList.malformed_location);
			}
		return true;
	}
}