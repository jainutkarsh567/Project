package wild1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


public class Forgot_Password extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Forgot_Password() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	    
		UserLogin users = new Gson().fromJson(body, UserLogin.class); 
		System.out.println("i am in forgot");
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
    		            msg.setText("your password for username "+users.getUsername()+" is " + dStr+"");
    		            msg.setHeader("XPriority", "1");
    		            Transport.send(msg);
    		            System.out.println("Mail has been sent successfully");
    		            out.println("Successful");
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


