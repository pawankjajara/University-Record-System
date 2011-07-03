package URS;
import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class UniversityClient 
{
	private javax.jms.Connection connection;
	private Session session;
	private Topic UniversityTopic;
	private MessageConsumer consumer;
	private Topic replyTopic;
	
	public static void main(String args[])
	{
		new UniversityClient();
	}
	
	public TextMessage Enroll_Student(String Student_ID,String Course_Name,String Course_Section) throws JMSException
	{		
		System.out.println("In Client: Enroll URS");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Enroll_Student"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+Student_ID+"\n"+Course_Name+"\n"+Course_Section);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();	
	    return Reply;
	}
	public TextMessage Unenroll_Student(String Student_ID, String Course_Name, String Course_Section) throws JMSException
	{
		System.out.println("In Client: Unenroll_Student");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Unenroll_Student"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+Student_ID+"\n"+Course_Name+"\n"+Course_Section);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Calculate_Bill(String Student_ID) throws JMSException
	{	

		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Calculate_Bill"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);

		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();

		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Reply: " +Reply.getText() ); 	    


		consumer.close();
		return Reply;
	}
	
	
	
	public TextMessage Create_Student(String First_Name,String Last_Name,String Student_ID,String Address,String City,String State,String Zip) throws JMSException
	{
		
		System.out.println("In Client: createStudent");
		System.out.println("first name"+ First_Name);
		
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Create_Student"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+7+"\n"+First_Name+"\n"+Last_Name+"\n"+Student_ID+"\n"+Address+"\n"+City+"\n"+State+"\n"+Zip);
		System.out.println("passed to server");
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("got request");
		System.out.println("Request ID & Status: " +Reply.getText() ); 	     	    
	    consumer.close();
		return Reply;
	}

	public TextMessage Remove_Student(String student_ID,String forceful_Unenroll) throws JMSException
	{
		
		System.out.println("In Client: removeStudent");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Remove_Student"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+student_ID+"\n"+forceful_Unenroll);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Request ID: " +Reply.getText()); 
		consumer.close();
		return Reply;
	    
	}
	
	
	
	public TextMessage Set_First_Name(String Student_ID,String First_Name) throws JMSException
	{		
		System.out.println("In Client: Set First Name");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_First_Name"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Student_ID+"\n"+First_Name);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_First_Name(String Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Get First Name");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_First_Name"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();	
	    return Reply;
	}
	
	public TextMessage Set_Last_Name(String Student_ID, String Last_Name) throws JMSException
	{
		
		System.out.println("In Client: Set Last Name");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Last_Name"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Student_ID+"\n"+Last_Name);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_Last_Name(String Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Get Last Name");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Last_Name"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Set_Address(String Student_ID, String Address) throws JMSException
	{
		
		System.out.println("In Client: Set address");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Address"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Student_ID+"\n"+Address);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_Address(String Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Get address");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Address"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Set_City(String Student_ID, String City) throws JMSException
	{
		
		System.out.println("In Client: Set city");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_City"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Student_ID+"\n"+City);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_City(String Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Get city");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_City"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Set_State(String Student_ID, String State) throws JMSException
	{
		
		System.out.println("In Client: Set state");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_State"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Student_ID+"\n"+State);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_State(String Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Get state");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_State"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Set_Zip(String Student_ID, String Zip) throws JMSException
	{
		
		System.out.println("In Client: Setzip");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Zip"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Student_ID+"\n"+Zip);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_Zip(String Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Get Get_Zip");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Zip"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Set_ID(String Old_Student_ID, String New_Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Set ID");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_ID"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Old_Student_ID+"\n"+New_Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	
	public TextMessage Get_Courses(String Student_ID) throws JMSException//String Student_ID
	{
		
		System.out.println("In Client: Get_Courses");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Courses"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_Enrolled_Units(String Student_ID) throws JMSException
	{
		
		System.out.println("In Client: Get_Enrolled_Units");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Enrolled_Units"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Student_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage List_Students() throws JMSException
	{
		
		System.out.println("In Client: List URS");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("List_Students"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+0);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Display_Student_Info(String studentID) throws JMSException
	{
		String sid=studentID;		
		System.out.println("In Client: display URS info");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Display_Student_Info"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+sid);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		consumer.close();
		return Reply;
	    		
	}
	

	public TextMessage Find_All_Persons(String searchType) throws JMSException
	{
		
		System.out.println("In Client: Find_All_Persons");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_All_Persons"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+searchType);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Request ID: " +Reply.getText());	    
	    consumer.close();
		return Reply;
	}
	
	public TextMessage Find_Person_By_Zip(String zipCode, String searchType) throws JMSException
	{
		
		System.out.println("In Client: Find_Person_By_Zip");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Person_By_Zip"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+zipCode+"\n"+searchType);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	     	    
	    consumer.close();
		return Reply;
	}
	
	public TextMessage Find_Person_By_City(String City, String searchType) throws JMSException
	{
		
		System.out.println("In Client: Find_Person_By_City");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Person_By_City"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+City+"\n"+searchType);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	     	    
	    consumer.close();
	    return Reply;
		
	}
	
	public TextMessage Find_Person_By_State(String State, String searchType) throws JMSException
	{
		
		System.out.println("In Client: Find_Person_By_State");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Person_By_State"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+State+"\n"+searchType);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	     	    
	    consumer.close();
	    return Reply;
		
	}
	
	
	
	public TextMessage Find_Person_By_Name(String name, String searchType) throws JMSException
	{
		
		System.out.println("In Client: Find_Person_By_Name");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Person_By_Name"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+name+"\n"+searchType);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Request ID: " +Reply.getText() ); 	     	    
	    consumer.close();
	    return Reply;
		
	}
	
	// Sagar's clients are here onwards !!
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////
	public TextMessage Get_Course_Units(String Course_Name, String Course_Section) throws JMSException
	{
		System.out.println("In Client: removeCourse");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Course_Units"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Course_Name+"\n"+Course_Section);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	public TextMessage Set_Course_Units(String Course_Name, String Course_Section, String Course_Units) throws JMSException
	{
		System.out.println("In Client: removeCourse");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Course_Units"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+Course_Name+"\n"+Course_Section+"\n"+Course_Units);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	public TextMessage Get_Course_Instructor(String Course_Name, String Course_Section) throws JMSException
	{
		System.out.println("In Client: getinstructorid");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Course_Instructor"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Course_Name+"\n"+Course_Section);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	
	public TextMessage Set_Course_Instructor(String Course_Name, String Course_Section, String Instructor_ID) throws JMSException
	{
		System.out.println("In Client: set course instructor");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Course_Instructor"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+Instructor_ID+"\n"+Course_Name+"\n"+Course_Section);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	public TextMessage Set_Course_Name(String Course_Name, String Course_Section, String New_Course_Name) throws JMSException
	{
		System.out.println("In Client: set course name");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Course_Name"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+Course_Name+"\n"+Course_Section+"\n"+New_Course_Name);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	public TextMessage Set_Course_Section(String Course_Name, String Course_Section, String New_Course_Section) throws JMSException
	{
		System.out.println("In Client: set course section");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Course_Section"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+Course_Name+"\n"+Course_Section+"\n"+New_Course_Section);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	
	public TextMessage Get_Location(String Course_Name, String Course_Section) throws JMSException
	{
		System.out.println("In Client: get location");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Location"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Course_Name+"\n"+Course_Section);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	
	public TextMessage Set_Location(String Location,String Course_Name, String Course_Section) throws JMSException
	{

		System.out.println("In Client: set location");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Location"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+Location+"\n"+Course_Name+"\n"+Course_Section);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText()); 	    
		consumer.close();
		return Reply;

	}
	
	public TextMessage Find_All_Courses() throws JMSException
	{	
		System.out.println("In Client: Find all courses");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_All_Courses"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+0+"");

		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	
	public TextMessage Find_Courses_By_Location(String Location) throws JMSException
	{	

		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Courses_By_Location"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Location);

		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	
	public TextMessage Find_Courses_By_Course_Name(String Course_Name) throws JMSException
	{	

		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Courses_By_Course_Name"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Course_Name);

		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();

		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	
	public TextMessage Get_Students(String CourseName, String Course_Section) throws JMSException
	{
		
			MessageProducer MP = session.createProducer(UniversityTopic);		
			TextMessage TM = session.createTextMessage("Get_Students"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+CourseName+"\n"+Course_Section);

			replyTopic = session.createTemporaryTopic();		
			consumer = session.createConsumer(replyTopic );

			TM.setJMSReplyTo(replyTopic);
			MP.send(TM);
			MP.close();

			TextMessage Reply = (TextMessage)consumer.receive();
			System.out.println("Reply: " +Reply.getText() ); 	    


			consumer.close();	 
			return Reply;
		
	}
	
	
	
	public TextMessage Remove_Course(String course_name,String course_section,String force_unenroll) throws JMSException
	{
		System.out.println("In Client: removeCourse");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		//TextMessage TM = session.createTextMessage("Remove_Course"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+""+"\n"+"04"+"\n"+"0");
		TextMessage TM = session.createTextMessage("Remove_Course"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+3+"\n"+course_name+"\n"+course_section+"\n"+force_unenroll);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
	}
	public TextMessage Find_Instructors_By_Department(String Dept) throws JMSException
	{	
		System.out.println("In Client: Find_Courses_By_Department");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Instructors_By_Department"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Dept);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Remove_Office_Hours(String Instructor_ID, String Location_Hours) throws JMSException
	{	
		System.out.println("In Client: Remove_Office_Hours");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Remove_Office_Hours"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Instructor_ID+"\n"+Location_Hours);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();	
	    return Reply;
	}
	
//	public TextMessage Get_Office_Hours(String ID) throws JMSException
//	{	
//		System.out.println("In Client: Get_Office_Hours");
//		MessageProducer MP = session.createProducer(UniversityTopic);		
//		TextMessage TM = session.createTextMessage("Get_Office_Hours"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+ID);
//		
//		replyTopic = session.createTemporaryTopic();		
//		consumer = session.createConsumer( replyTopic );
//		
//		TM.setJMSReplyTo(replyTopic);
//		MP.send(TM);
//		MP.close();
//		TextMessage Reply = (TextMessage)consumer.receive();
//		
//		System.out.println("Reply: " +Reply.getText() ); 	    
//	    consumer.close();
//	    return Reply;
//	}
	
	public TextMessage Add_Office_Hours(String Instructor_ID, String Location_Hours) throws JMSException
	{	
		System.out.println("In Client: Add_Office_Hours");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Add_Office_Hours"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Instructor_ID+"\n"+Location_Hours);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();	
	    return Reply;
	}
//	public TextMessage Get_Department(String Instructor_ID) throws JMSException
//	{	
//		System.out.println("In Client: Get Department");
//		MessageProducer MP = session.createProducer(UniversityTopic);		
//		TextMessage TM = session.createTextMessage("Get_Department"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Instructor_ID);
//		
//		replyTopic = session.createTemporaryTopic();		
//		consumer = session.createConsumer( replyTopic );
//		
//		TM.setJMSReplyTo(replyTopic);
//		MP.send(TM);
//		MP.close();
//		TextMessage Reply = (TextMessage)consumer.receive();
//		
//		System.out.println("Reply: " +Reply.getText() ); 	    
//	    consumer.close();
//	    return Reply;
//	}
	
//	public TextMessage Set_Department(String Instructor_ID,String dept) throws JMSException
//	{		
//		System.out.println("In Client: Set Department");
//		MessageProducer MP = session.createProducer(UniversityTopic);		
//		TextMessage TM = session.createTextMessage("Set_Department"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Instructor_ID+"\n"+dept);
//		
//		replyTopic = session.createTemporaryTopic();		
//		consumer = session.createConsumer( replyTopic );
//		
//		TM.setJMSReplyTo(replyTopic);
//		MP.send(TM);
//		MP.close();
//		TextMessage Reply = (TextMessage)consumer.receive();
//		
//		System.out.println("Reply: " +Reply.getText() ); 	    
//	    consumer.close();	
//	    return Reply;
//	}
//	public TextMessage Create_Instructor(String First_Name,String Last_Name,String Instructor_ID,String Address,String City,String State,String Zip,String Department) throws JMSException
//	{		
//		System.out.println("In Client: Create_Instructor");
//		MessageProducer MP = session.createProducer(UniversityTopic);		
//		TextMessage TM = session.createTextMessage("Create_Instructor"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+8+"\n"+Instructor_ID+"\n"+First_Name+"\n"+Last_Name+"\n"+Address+"\n"+City+"\n"+State+"\n"+Zip+"\n"+Department);		
//		replyTopic = session.createTemporaryTopic();		
//		consumer = session.createConsumer( replyTopic );
//		
//		TM.setJMSReplyTo(replyTopic);
//		MP.send(TM);
//		MP.close();
//		TextMessage Reply = (TextMessage)consumer.receive();
//		
//		System.out.println("Reply: " +Reply.getText() ); 	    
//	    consumer.close();
//	    return Reply;
//	}		
	
	public TextMessage Create_Course(String course_name,String course_section,String ins_id,String location,String units) throws JMSException
	{
		//int sum;
		System.out.println("In Client: createCourse");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		//TextMessage TM = session.createTextMessage(""+"\n"+"R001"+"\n"+5+"\n"+"CMPE271"+"\n"+"04"+"\n"+"001-00-1111"+"\n"+"clark 489 WTF 1500-1700"+"\n"+3);
		TextMessage TM = session.createTextMessage("Create_Course"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+5+"\n"+course_name+"\n"+course_section+"\n"+ins_id+"\n"+location+"\n"+units);
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );

		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();

		System.out.println("Reply: " +Reply.getText() ); 	    
		consumer.close();
		return Reply;
		
	}		
	public TextMessage Find_Courses_By_Instructor(String Instructor_ID) throws JMSException
	{	
		System.out.println("In Client: find courses by inst");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Find_Courses_By_Instructor"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Instructor_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();	
	    return Reply;
	}

	//Ruchita's functions begins here !!
	
	public TextMessage Create_Instructor(String First_Name,String Last_Name,String Instructor_ID,String Address,String City,String State,String Zip,String Department) throws JMSException
	{		
		System.out.println("In Client: Create_Instructor");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Create_Instructor"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+8+"\n"+Instructor_ID+"\n"+First_Name+"\n"+Last_Name+"\n"+Address+"\n"+City+"\n"+State+"\n"+Zip+"\n"+Department);		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}	
	public TextMessage Set_Department(String Instructor_ID,String dept) throws JMSException
	{		
		System.out.println("In Client: Set Department");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Set_Department"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+2+"\n"+Instructor_ID+"\n"+dept);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();	
	    return Reply;
	}
	public TextMessage Get_Department(String Instructor_ID) throws JMSException
	{	
		System.out.println("In Client: Get Department");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Department"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+Instructor_ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	public TextMessage Get_Office_Hours(String ID) throws JMSException
	{	
		System.out.println("In Client: Get_Office_Hours");
		MessageProducer MP = session.createProducer(UniversityTopic);		
		TextMessage TM = session.createTextMessage("Get_Office_Hours"+"\n"+RandomNumberGen.getRandomRequestID()+"\n"+1+"\n"+ID);
		
		replyTopic = session.createTemporaryTopic();		
		consumer = session.createConsumer( replyTopic );
		
		TM.setJMSReplyTo(replyTopic);
		MP.send(TM);
		MP.close();
		TextMessage Reply = (TextMessage)consumer.receive();
		
		System.out.println("Reply: " +Reply.getText() ); 	    
	    consumer.close();
	    return Reply;
	}
	
	
	public UniversityClient()
	{
		try
		{
			System.out.println("Inside University Client constructor");
		    Properties properties = new Properties();
		    properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		    properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
		    properties.put(Context.PROVIDER_URL, "localhost");
			
			InitialContext jndi = new InitialContext(properties);
			ConnectionFactory conFactory = (ConnectionFactory)jndi.lookup("XAConnectionFactory");
			connection = conFactory.createConnection();
			
			session = connection.createSession( false, Session.AUTO_ACKNOWLEDGE );
			UniversityTopic = (Topic)jndi.lookup("UniversityTopic");
			
			connection.start();
						
			//Get_Courses();
		}
		catch(NamingException NE)
		{
			System.out.println("Naming Exception: "+NE);
		}
		catch(JMSException JMSE)
		{
			System.out.println("JMS Exception: "+JMSE);
		}
	}	

}
