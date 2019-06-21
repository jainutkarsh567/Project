package wild1;
import java.io.BufferedReader;
import java.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import org.json.simple.*;

import com.google.gson.Gson;


import javax.naming.NameNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Servlet2 extends HttpServlet {
	static List<Animals2> animal=new ArrayList<Animals2>();
	static List<UserLogin> user= new ArrayList<UserLogin>();
	static List<Response> resp=new ArrayList<Response>();
	Response res;
	 int id;
	public void init() throws ServletException {
		System.out.println("Servlet Initialized!");
		animal.add(new Animals2(1,"Tiger"));
		animal.add(new Animals2(2,"Lion"));
		animal.add(new Animals2(3,"Elephant"));
		animal.add(new Animals2(4,"Giraffe"));
		animal.add(new Animals2(5,"Zebra"));
		animal.add(new Animals2(6,"Jackal"));
		animal.add(new Animals2(7,"Deer"));	
		
		//resp.add(new Response("Succesfull","User is succesfully added"));
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{	
		
	
	StringBuilder stringBuilder = new StringBuilder();
	    BufferedReader bufferedReader = null;
	  //  String string=new Gson().toJson(resp);
	   
	    try {
	        InputStream inputStream = request.getInputStream();
	        if (inputStream != null) {
	            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            char[] charBuffer = new char[128];
	            int bytesRead = -1;
	            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                stringBuilder.append(charBuffer, 0, bytesRead);
	            }
	        } else {
	            stringBuilder.append("");
	        }
	    } catch (IOException ex) {
	        throw ex;
	    } finally {
	        if (bufferedReader != null) {
	            try {
	                bufferedReader.close();
	            } catch (IOException ex) {
	                throw ex;
	            }
	        }
	    }
	    PrintWriter out=response.getWriter(); 
	    String body = stringBuilder.toString();
	    System.out.println(body);
	    Base64.Encoder encoder = Base64.getEncoder();  
	   // String command = request.getParameter("command");
	    //System.out.println(command);
	   // String n=request.getParameter("username");
	   // System.out.println(n);
	    
	    if(body.contains("gender")) 
		{	
	    	UserLogin users = new Gson().fromJson(body, UserLogin.class);   
			System.out.println(users);
			System.out.println(users.getPassword());
			String str=encoder.encodeToString(users.getPassword().getBytes());
			System.out.println(str);
			
			
			 try{  
			    	Class.forName("com.mysql.cj.jdbc.Driver");  
			    	Connection con=DriverManager.getConnection(  
			    	"jdbc:mysql://localhost:3306/user_database","root","ukki12345");  
			    	try {
			    		Statement stmt=con.createStatement();
			    		ResultSet rs=stmt.executeQuery("select MAX(id) max_id from user_info");
			    		 id = -1;
			    		if (rs.next()) {
			    		   id = rs.getInt("max_id");  
			    		}
			    	}
			    	catch(Exception ex)
			    	{
			    		System.out.println(ex);
			    	}
			    	String query = " insert into user_info (id, uname, username,password,age, gender)"
			    	        + " values (?, ?, ?, ?, ?, ?)";
			    	   id++;	
			    	 try {
			    	  PreparedStatement preparedStmt = con.prepareStatement(query);
			    	  preparedStmt.setInt (1, id);
			    	  preparedStmt.setString (2, users.getName());
			    	  preparedStmt.setString (3, users.getUsername());
			    	  preparedStmt.setString (4, str);
			    	  preparedStmt.setInt(5, users.getAge());
			    	  preparedStmt.setString (6, users.getGender());
			    	preparedStmt.execute();
			    	
			    		
			         HttpSession session=request.getSession();
			    	 session.setAttribute("username",users.getUsername());
			    	  out.println(new Gson().toJson("Succesful"));
			    	
			    	con.close();  
			    	}catch(Exception e)
			 		{
			    		System.out.println(e);
			    	}  
			}
			
			catch(Exception ex)
			{
				System.out.println(ex);
				String json=new Gson().toJson(Response.NULL.getMessage());
    			System.out.println(json);
    			out.println(json);
			}
			
		}

	 	else if(body.contains("token"))
	    			{ 	UserLogin users = new Gson().fromJson(body, UserLogin.class); 
	    			
	    			try{  
    			    	Class.forName("com.mysql.cj.jdbc.Driver");  
    			    	Connection con=DriverManager.getConnection(  
    			    	"jdbc:mysql://localhost:3306/user_database","root","ukki12345");   
    			    	Statement stmt=con.createStatement();  
    			    	
    			    	ResultSet rs=stmt.executeQuery("select * from user_info where username='"+users.getUsername()+"'"); 
    			    	
    			    	System.out.println(rs.getRow());
    			    	if(rs.next())
    			    		{
    			    			System.out.println("username is present");
    			    			
    			    			String password=rs.getString(4);
    			    			System.out.println(password);
    			    			
    			    		
    	    			    	byte[] actualByte = Base64.getDecoder().decode(password);  
    	    			    	String dStr= new String(actualByte);
    	    			    	System.out.println("Decoded string: "+dStr);
    			    			
    			    			String mail=users.getUsername();
    		    				Properties props = new Properties();
    		    		        props.put("mail.smtp.host", "true");
    		    		        props.put("mail.smtp.starttls.enable", "true");
    		    		        props.put("mail.smtp.host", "smtp.gmail.com");
    		    		        props.put("mail.smtp.port", "587");
    		    		        props.put("mail.smtp.auth", "true");
    		    		        //Establishing a session with required user details
    		    		        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    		    		            protected PasswordAuthentication getPasswordAuthentication() {
    		    		                return new PasswordAuthentication("utkarshjain.mech20@jecrc.ac.in", "ukki12345");
    		    		            }
    		    		        });
    		    		        try {
    		    		            //Creating a Message object to set the email content
    		    		            MimeMessage msg = new MimeMessage(session);
    		    		            //Storing the comma seperated values to email addresses
    		    		            String to = mail;
    		    		            System.out.println(mail);
    		    		            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
    		    		            addresses in an array of InternetAddress objects*/
    		    		            InternetAddress[] address = InternetAddress.parse(to, true);
    		    		            //Setting the recepients from the address variable
    		    		            msg.setRecipients(Message.RecipientType.TO, address);
    		    		            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
    		    		            msg.setSubject("Sample Mail : " + timeStamp);
    		    		            msg.setSentDate(new Date());
    		    		            msg.setText("your password for username "+users.getUsername()+" is" + dStr+"");
    		    		            msg.setHeader("XPriority", "1");
    		    		            Transport.send(msg);
    		    		            System.out.println("Mail has been sent successfully");
    		    		        } catch (MessagingException mex) {
    		    		            System.out.println("Unable to send an email" + mex);
    		    		        }
    		    		    
    		    			
    			    		}
    			    		
    			    	else
    			    	{
    			    		String json=new Gson().toJson(Response.FAILURE.getMessage());
			    			System.out.println(json);
			    			out.println(json + "Username does not exist");
    			    	}
    			    	con.close();  
    			    	}
	    				catch(Exception e)
	    			{
    			     System.out.println(e);		
    			    	} 
	    			}
	    		}	
	    	
	    
	/* boolean decision=true; 		
	for(int i=0;i<user.size();i++)
	{
		if(users.getUsername().equals(user.get(i).getUsername()))
			{	
				decision=true;
				if(users.getPassword().equals(user.get(i).getPassword()))
				{	decision=true;
					break;
				}
				
			}
		else {
			decision=false;
			}
	}
	if(decision==true)
	{
		System.out.println("correct password and username");
		String json=new Gson().toJson(Response.SUCCESS.getMessage());
		System.out.println(json);
		out.println(json);
	}
	else
	{
		System.out.println("incorrect username");
		String json=new Gson().toJson(Response.FAILURE.getMessage());
		System.out.println(json);
		out.println(json);
	}
	
}*/
	   
	
	    //System.out.println(usersData);

	   // String string=new Gson().toJson(animal);
	    //PrintWriter out=response.getWriter();  
	//    System.out.println(animal);
	/*	System.out.println("id"+request.getParameter("id") );
		PrintWriter out=response.getWriter();
		if (request.getParameter("id") != null) {
			String name1=request.getParameter("name");
			int id1=Integer.parseInt(request.getParameter("id"));
			animal.add(new Animals2(id1,name1));
		}
		String arr1=new Gson().toJson(animal);
		System.out.println(arr1);
		out.println(arr1);*/
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	
		

			PrintWriter out= response.getWriter();
			String arr=new Gson().toJson(animal);
			out.println(arr);
			
}
	
	
}