package URS;
import java.sql.*;
import java.util.*;

public class Location 
{
	private int location_id;
	private String building;
	private String room_number;
	private String days;
	private String start_time;
	private String end_time;
	
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getRoom_number() {
		return room_number;
	}
	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Location insert(ResultSet rs)
	{
		Location l = new Location();
		try{
		l.setLocation_id(rs.getInt("location_id"));
		l.setBuilding(rs.getString("building"));
		l.setRoom_number(rs.getString("room_number"));
		l.setDays(rs.getString("days"));
		l.setStart_time(rs.getString("start_time"));
		l.setEnd_time(rs.getString("end_time"));
		}catch(SQLException e)
		{
			System.out.println("SQLException "+ e);
		}
		return l;
	}
	public Location loc_Split(String s1)
	{
		HelperClass help = new HelperClass();
		ArrayList<String> loc=help.Split(s1);
		System.out.println(loc);
		setBuilding(loc.get(0));
		setRoom_number(loc.get(1));
		setDays(loc.get(2));
		setStart_time(loc.get(3));
		setEnd_time(loc.get(4));
		return this;
	}
}
