package URS;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;


import javax.jms.*;
import javax.naming.*;
import java.io.*;
import java.util.*;

public class UniversityServer implements MessageListener
{		
	
	private Connection connection;
	private Session session;
	private Topic UniversityTopic;
	private MessageConsumer consumer;    
	private Instructor instructor = new Instructor();
	private HelperClass help = new HelperClass(); 
	private Admission admission = new Admission();
	private Student student= new Student();	
	private Person person= new Person();
	private Course course = new Course();
	private ValidationClass validate = new ValidationClass();
	
	public static void main(String args[])
	{
		new UniversityServer();
	}
	
	public void sendReply(Message request,String requestId, int status)
	{
		try
		{
			MessageProducer MP = session.createProducer(null);
			Destination reply = request.getJMSReplyTo();
			TextMessage TM = session.createTextMessage();			
			TM.setText(""+requestId+"\n"+status);
			MP.send(reply, TM);
			MP.close();
		}
		catch(JMSException JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
		}
	}
			
	public void sendReply_list(Message request,String requestId,int zero,int numparam,ArrayList paramlist)
	{
		try
		{
		System.out.println("List size in server:"+paramlist.size());
		MessageProducer MP = session.createProducer(null);
		Destination reply = request.getJMSReplyTo();
		
		String str;
		str=requestId+"\n"+zero+"\n"+numparam+"\n";
		for(Iterator<String> i=paramlist.iterator();i.hasNext();)
		{
			str=str+i.next()+"\n";
		}
		System.out.println("output: "+str);
							
		TextMessage TM = session.createTextMessage();			
		TM.setText(str);
		MP.send(reply, TM);
		MP.close();
	    }
		catch(JMSException JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
		}
	}
	
	public void onMessage(Message message)
	{				
		TextMessage TM = (TextMessage)message;
		String requestId="",operation="";
		int paramCount;
		try
		{	
			System.out.println("In Server: OnMessage");
			String text = TM.getText();
			TokenizeHold hold = Tokenize.getInstanceofTokenize().getTokens(text);
			
			operation = hold.getOperation(); 
			requestId = hold.getRequestId();
				
			System.out.println("Operation Name :" + operation);
			System.out.println("Got "+hold.getParameterCount()+" parameters");
			
			paramCount = hold.getParameterCount();
			List<String> paramValues = hold.getParameterValues();
			
			for(int i=0; i<paramCount;i++)
				System.out.println(paramValues.get(i));
			
			if( operation.equalsIgnoreCase("Create_Instructor"))
			{
				instructor.Create_Instructor(paramValues.get(0), paramValues.get(1),paramValues.get(2),paramValues.get(3), paramValues.get(4),paramValues.get(5),paramValues.get(6),paramValues.get(7));
				sendReply(message,requestId, 0);
			}
			else if( operation.equalsIgnoreCase("Set_Department"))
			{
				instructor.Set_Department(paramValues.get(0), paramValues.get(1));				
				sendReply(message,requestId, 0);
			}
			else if( operation.equalsIgnoreCase("Get_Department"))
			{
				ArrayList<String> department = instructor.Get_Department(paramValues.get(0));
				sendReply_list(message,requestId,0,department.size(),department );				
			}
			else if( operation.equalsIgnoreCase("Get_Courses"))
			{
				ArrayList<String> list = person.Get_Courses(paramValues.get(0));
				sendReply_list(message,requestId,0,list.size(),list );
			}
			else if( operation.equalsIgnoreCase("Find_Instructors_By_Department"))
			{
				ArrayList<String> list = instructor.Find_Instructors_By_Department(paramValues.get(0));
				sendReply_list(message,requestId,0,list.size(),list );
			}
			else if( operation.equalsIgnoreCase("Get_Office_Hours"))
			{
				ArrayList<String> list = instructor.Get_Office_Hours(paramValues.get(0));
				sendReply_list(message,requestId,0,list.size(),list );
			}
			else if( operation.equalsIgnoreCase("Add_Office_Hours"))
			{
				instructor.Add_Office_Hours(paramValues.get(0),paramValues.get(1));
				sendReply(message,requestId,0);				
			}
			else if( operation.equalsIgnoreCase("Remove_Office_Hours"))
			{
				instructor.Remove_Office_Hours(paramValues.get(0),paramValues.get(1));
				sendReply(message,requestId,0);				
			}			
			else if( operation.equalsIgnoreCase("Set_First_Name"))
			{
				System.out.println("Inside Onmsg setfirstname");
				person.Set_First_Name(paramValues.get(0), paramValues.get(1));
				sendReply(message,requestId,0);
			}
			else if( operation.equalsIgnoreCase("Get_First_Name"))
			{
				System.out.println("Inside On msg inside get first name");
				ArrayList<String> firstname = person.Get_First_Name(paramValues.get(0));
				sendReply_list(message,requestId,0,firstname.size(),firstname);
			}
			else if( operation.equalsIgnoreCase("Set_Last_Name"))
			{
				person.Set_Last_Name(paramValues.get(0), paramValues.get(1));
				sendReply(message,requestId,0);
			}
			else if( operation.equalsIgnoreCase("Get_Last_Name"))
			{
				ArrayList<String> lastname = person.Get_Last_Name(paramValues.get(0));
				sendReply_list(message,requestId,0,lastname.size(),lastname);
			}
			else if( operation.equalsIgnoreCase("Set_Address"))
			{
				person.Set_Address(paramValues.get(0), paramValues.get(1));
				sendReply(message,requestId,0);
			}
			else if( operation.equalsIgnoreCase("Get_Address"))
			{
				ArrayList<String> address = person.Get_Address(paramValues.get(0));
				sendReply_list(message,requestId,0,address.size(),address);
			}
			else if( operation.equalsIgnoreCase("Set_City"))
			{
				person.Set_City(paramValues.get(0), paramValues.get(1));
				sendReply(message,requestId,0);
			}
			else if( operation.equalsIgnoreCase("Get_City"))
			{
				ArrayList<String> city = person.Get_City(paramValues.get(0));
				sendReply_list(message,requestId,0,city.size(),city);
			}
			else if( operation.equalsIgnoreCase("Set_State"))
			{
				person.Set_State(paramValues.get(0), paramValues.get(1));
				sendReply(message,requestId,0);
			}
			else if( operation.equalsIgnoreCase("Get_State"))
			{
				ArrayList<String> state = person.Get_State(paramValues.get(0));
				sendReply_list(message,requestId,0,state.size(),state);
			}
			else if( operation.equalsIgnoreCase("Set_Zip"))
			{
				person.Set_Zip(paramValues.get(0), paramValues.get(1));
				sendReply(message,requestId,0);
			}
			else if( operation.equalsIgnoreCase("Get_Zip"))
			{
				ArrayList<String> zip = person.Get_Zip(paramValues.get(0));
				sendReply_list(message,requestId,0,zip.size(),zip);
			}
			else if( operation.equalsIgnoreCase("Set_ID"))
			{
				person.Set_ID(paramValues.get(0), paramValues.get(1));
				sendReply(message,requestId,0);
			}
			else if( operation.equalsIgnoreCase("Enroll_Student"))
			{
				admission.Enroll_Student(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId,0);				
			}						
			else if(operation.equalsIgnoreCase("Create_Student"))
			{
				System.out.println("in server");
				student.Create_Student(paramValues.get(0), paramValues.get(1),paramValues.get(2),paramValues.get(3), paramValues.get(4),paramValues.get(5),paramValues.get(6));
				sendReply(message,requestId, 0);
			}
			else if (operation.equalsIgnoreCase("Remove_Student"))
			{					
				student.Remove_Student(paramValues.get(0),paramValues.get(1));
				sendReply(message,requestId, 0);
			}						
			else if (operation.equalsIgnoreCase("Get_Enrolled_Units"))
			{					
				System.out.println("in server Get_Enrolled_Units"+ paramValues.get(0));
				ArrayList<String> list = student.Get_Enrolled_Units(paramValues.get(0));
				sendReply_list(message,requestId,0,list.size(),list);
			}
			else if (operation.equalsIgnoreCase("List_Students"))
			{					
				System.out.println("in server List_Students");
				ArrayList<String> list = student.List_Students();
				sendReply_list(message,requestId,0,list.size(),list);

			}
			else if (operation.equalsIgnoreCase("Display_Student_Info"))
			{					
				
				ArrayList<String> list = student.Display_Student_Info(paramValues.get(0));
				sendReply_list(message,requestId,0,list.size(),list);
			}
			else if(operation.equalsIgnoreCase("Unenroll_Student"))
			{
				admission.Unenroll_Student(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId, 0);
			}				
			if(operation.equalsIgnoreCase("Create_Course"))
			{
				Location loc1=new Location();
				//to get location id by calling split merge
				Location list1 = loc1.loc_Split(paramValues.get((3)));
				// malformed location
				if(validate.validate_malformedlocation(list1))
				{
					System.out.println("Location good");
					System.out.println("Before validate hours");
					if(validate.validate_hours(list1.getDays()+" "+list1.getStart_time()+"-"+list1.getEnd_time()))
					{
						if(validate.validateID(paramValues.get(2)))
						{

							if(validate.schedule_conflict_check(list1, paramValues.get(2)))
							{
								System.out.println("Before validate room");					
								if(validate.room_booked_conflict_check(list1))		
								{

									String Building = list1.getBuilding();
									String Room = list1.getRoom_number();
									String Days = list1.getDays();
									String StartTime = list1.getStart_time();
									String EndTime = list1.getEnd_time();

									//call to locationcourse to add into location table
									help.create_Location(Building,Room,Days,StartTime,EndTime);
									int location = help.get_LocationId(Building,Room,Days,StartTime,EndTime);
									course.create_Course(paramValues.get(0), paramValues.get(1),paramValues.get(2),location, paramValues.get(4));
									sendReply(message,requestId,0);
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
						else{
							throw new ExceptionClass(UniversityExceptionList.no_such_person);
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



			//2. to remove a course
			else if(operation.equalsIgnoreCase("Remove_Course"))
			{
				System.out.println("In remove case :server");
				course.remove_Course(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId,0);
			}

			//3. to get course units
			else if(operation.equalsIgnoreCase("Get_Course_Units"))
			{
				System.out.println("In get units :server");
				ArrayList<String> list= course.get_Course_Units(paramValues.get(0),paramValues.get(1));
				sendReply_list(message,requestId,0,list.size(),list);
			}

			//4. to set course units
			else if(operation.equalsIgnoreCase("Set_Course_Units"))
			{
				System.out.println("In set units :server");
				course.set_Course_Units(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId,0);
			}

			//5. to get course instructor
			else if(operation.equalsIgnoreCase("Get_Course_Instructor"))
			{
				System.out.println("In get Instructor :server");
				ArrayList<String> list = course.get_Course_Instructor(paramValues.get(0),paramValues.get(1));
				sendReply_list(message,requestId,0,list.size(),list);
			}

			//6. to set course instructor
			else if(operation.equalsIgnoreCase("Set_Course_Instructor"))
			{
				System.out.println("In set instructor :server");
				course.set_Course_Instructor(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId,0);
			}

			//7. to set course name
			else if(operation.equalsIgnoreCase("Set_Course_Name"))
			{
				System.out.println("In set course name :server");
				course.set_Course_Name(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId,0);
			}

			//8. to set course section
			else if(operation.equalsIgnoreCase("Set_Course_Section"))
			{
				System.out.println("In set course section :server");
				course.set_Course_Section(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId,0);
			}

			//9. to get location
			else if(operation.equalsIgnoreCase("Get_Location"))
			{
				System.out.println("In get Location :server");
				ArrayList<String> list  = course.get_Location(paramValues.get(0),paramValues.get(1));
				sendReply_list(message,requestId,0,list.size(),list);
			}

			//10. to set location
			else if(operation.equalsIgnoreCase("Set_Location"))
			{
				System.out.println("In set Location :server");
				course.set_Location(paramValues.get(0),paramValues.get(1),paramValues.get(2));
				sendReply(message,requestId,0);
			}

			//11. to find all courses
			else if(operation.equalsIgnoreCase("Find_All_Courses"))
			{
				System.out.println("In find courses:Sever");
				ArrayList<String> list = course.find_All_Courses();				
				sendReply_list(message,requestId,0,list.size(),list);

			}

			//12. to find all courses by location
			else if(operation.equalsIgnoreCase("Find_Courses_By_Location"))
			{
				System.out.println("In find courses by loc:Sever");
				ArrayList<String> list = course.find_Courses_By_Location(paramValues.get(0));				
				sendReply_list(message,requestId,0,list.size(),list);

			}

			//13. to find courses by course name
			else if(operation.equalsIgnoreCase("Find_Courses_By_Course_Name"))
			{
				System.out.println("In find courses by course name:Sever");
				ArrayList<String> list = course.find_Courses_By_Course_Name(paramValues.get(0));				
				sendReply_list(message,requestId,0,list.size(),list);		

			}

			//14.calculate bill
			else if(operation.equalsIgnoreCase("Calculate_Bill"))
			{
				System.out.println("In calculate bill:Sever");
				ArrayList<String> list = course.Calculate_Bill(paramValues.get(0));				
				sendReply_list(message,requestId,0,list.size(),list);			

			}

			// 15.get_students
			else if(operation.equalsIgnoreCase("Get_Students"))
			{
				System.out.println("In get_students:Sever");
				ArrayList<String> list = course.get_Students(paramValues.get(0),paramValues.get(1));				
				sendReply_list(message,requestId,0,list.size(),list);		
			}
			else if( operation.equalsIgnoreCase("Find_Courses_By_Instructor"))
			{
				ArrayList<String> list = course.Find_Courses_By_Instructor(paramValues.get(0));
				sendReply_list(message,requestId,0,list.size(),list );
			}
			else
			{		
				sendReply(message,requestId, UniversityExceptionList.general_exception);
				//returnValues.add(UniversityExceptionList.malformed_message);
			}					
		} catch (ExceptionClass e) 
		{									
			sendReply(message,requestId,e.getException());
		}
		catch(JMSException JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
		}
	}
	
	public UniversityServer()
	{
		try
		{
			
		    Properties properties = new Properties();
		    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		    properties.put(Context.PROVIDER_URL, "localhost");
		    
			InitialContext jndi = new InitialContext(properties);
			ConnectionFactory conFactory = (ConnectionFactory)jndi.lookup("XAConnectionFactory");
			connection = conFactory.createConnection();
			
			session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );
			
			try
			{
				UniversityTopic = (Topic)jndi.lookup("UniversityTopic");
			}
			catch(NamingException NE1)
			{
				System.out.println("NamingException: "+NE1+ " : Continuing anyway...");
			}
			
			if( null == UniversityTopic )
			{
				UniversityTopic = session.createTopic("UniversityTopic");
				jndi.bind("CounterTopic", UniversityTopic);
			}
			
			consumer = session.createConsumer( UniversityTopic );
			consumer.setMessageListener(this);
			
			connection.start();

			Thread.currentThread().join();
			consumer.close();
		}
		catch(NamingException NE)
		{
			System.out.println("Naming Exception: "+NE);
		}
		catch(Exception JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
		}		
	}

}
