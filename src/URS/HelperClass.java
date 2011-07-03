package URS;
import java.util.*;
import java.sql.*;

public class HelperClass 
{
	private Connection conn;
	public ArrayList<String> Split(String str)
	{    
		//String str = "ENGR 489 MWF 1000-1200";
		ArrayList<String> loc_tokens = new ArrayList();
		String Building="",Room="",Days="",Time="",StartTime="",EndTime="";
		int i=0,cnt,k,j;
		StringTokenizer s = new StringTokenizer(str);
		//to count num of tokens
		cnt = s.countTokens();  
		System.out.println("count is "+cnt);
		String[] strrev = new String[cnt];

		//chk if elements less than 4 - if all tba then no error nd set all tba
		// if any 1 not tba 
		if(cnt<4)
		{
			while(s.hasMoreTokens())
			{
				loc_tokens.add(i,s.nextToken());
				System.out.println("String is "+loc_tokens.get(i));
				i++;
			}
			loc_tokens.add("");
			loc_tokens.add("");
		}      
		else
		{  
			while(s.hasMoreTokens())
			{
				strrev[i]=s.nextToken();
				System.out.println("String is "+strrev[i]);
				i++;
			}

			int len = strrev.length;
			System.out.println("len is :"+len);

			Room = strrev[cnt-3];
			System.out.println("Room is :"+ Room);
			Days = strrev[cnt-2];
			System.out.println("Days is :"+ Days);
			Time = strrev[cnt-1];
			System.out.println("Time is :"+ Time);

			// j starts by removing 3 values nd k frm 0 to add in straight order
			for(j = cnt-4,k=0;j>=0;j--,k++)
			{
				Building += strrev[k] +" ";
			}        
			StringTokenizer s1 = new StringTokenizer(Time,"-");
			try{
				StartTime = s1.nextToken();
				EndTime =  s1.nextToken();
			}
			catch (Exception e)
			{ 
				System.out.println("Error - write the validation here");
			}   
			loc_tokens.add(Building);
			loc_tokens.add(Room);
			loc_tokens.add(Days);
			loc_tokens.add(StartTime);
			loc_tokens.add(EndTime);
		}
		return loc_tokens;
	}    

	public void create_Location(String s1,String s2,String s3,String s4,String s5)
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		try
		{
			PreparedStatement stmt = null;
			String str = "insert into location (building,room_number,days,start_time,end_time) values (?, ?, ?, ?, ?)";
			try {
				stmt = conn.prepareStatement(str);
				stmt.setString(1, s1);
				stmt.setString(2, s2);
				stmt.setString(3, s3);
				stmt.setString(4, s4);
				stmt.setString(5, s5);
				stmt.executeUpdate();

				conn.close();
				System.out.println("Location Record Added");
				System.out.println("Disconnected from Database");
			} catch (SQLException e) 
			{
				System.out.println(e);
			}
		}catch(Exception e)
		{
			System.out.println("Exception Generated: "+e);
		}       
	}



	public int get_LocationId(String s1,String s2,String s3,String s4,String s5)
	{
		DBConnection db = new DBConnection();
		conn=db.ConnectToDB();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int location = 0;
		try
		{
			String str = "select location_id from location where building = ? and room_number=? and days=? and start_time=? and end_time =?";

			try {
				stmt = conn.prepareStatement(str);
				stmt.setString(1, s1);
				stmt.setString(2, s2);
				stmt.setString(3, s3);
				stmt.setString(4, s4);
				stmt.setString(5, s5);
				rs = stmt.executeQuery();
				while(rs.next())
				{
					location = rs.getInt("location_id");
				}


				conn.close();
				System.out.println("Location Record Added");
				System.out.println("Disconnected from Database");
			} catch (SQLException e) 
			{
				System.out.println(e);
			}
		}catch(Exception e)
		{
			System.out.println("Exception Generated: "+e);
		}     
		return location;
	}
}