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
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;



public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        // TODO Auto-generated constructor stub
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
	    System.out.println("i am in login servlet");
	    System.out.println(body);
	    Base64.Encoder encoder = Base64.getEncoder();  
	    UserLogin users = new Gson().fromJson(body, UserLogin.class);   
		System.out.println(users);
		System.out.println(users.getPassword() + "   " + users.getUsername());
		try {
		System.out.println("login");
		try{  
		    	Class.forName("com.mysql.cj.jdbc.Driver");  
		    	Connection con=DriverManager.getConnection(  
		    	"jdbc:mysql://localhost:3306/user_database","root","ukki12345");   
		    	Statement stmt=con.createStatement();  
		    	System.out.println(users.getUsername());
		    	//	Base64.Decoder decoder = Base64.getDecoder();  
		    	String str=encoder.encodeToString(users.getPassword().getBytes());
				System.out.println(str);
		    	byte[] actualByte = Base64.getDecoder().decode(str);  
		    	String dStr= new String(actualByte);
		    	System.out.println("Decoded string: "+dStr);
		    	ResultSet rs=stmt.executeQuery("select * from user_info where username='"+users.getUsername()+"' && password='"+str+"'"); 
		    	String json;
		    	//System.out.println(rs.getRow());
		    	if(rs.next())
		    		{
		    		/*	System.out.println(rs.getString("username"));

		    			json=new Gson().toJson(Response.LOGIN.getMessage());
		    			String name=new Gson().toJson(rs.getString("uname"));
		    			//out.println(json);
		    			out.println( name);
		    			 HttpSession session=request.getSession();
		    			 session.setAttribute("login",name);  
    			        */
		    		
		    		Pojo pojo=new Pojo(rs.getString("username"),rs.getString("uname"),rs.getInt("age"),rs.getString("gender"));
    			       System.out.println(pojo); 
    			       HttpSession session=request.getSession();
    			       String login=new Gson().toJson(pojo);
    			       String login1=new Gson().toJson(pojo);
    			       System.out.println(login);
		    			 session.setAttribute("login",login);
		    			 System.out.println(session.getAttribute("login"));
		    			 out.println(login1);
    			     }
		    	else
		    	{
		    		json=new Gson().toJson(Response.FAILURE.getMessage());
	    		}
		    	//System.out.println(json);
    			
		    	con.close();  
		    	}catch(Exception e)
		 		{
		    		System.out.println(e);
		    	}  
		}
		
		catch(Exception ex)
		{
			String json=new Gson().toJson(Response.NULL.getMessage());
			System.out.println(json);
			out.println(json);
		}
}

	   
		// TODO Auto-generated method stub
		//doGet(request, response);
	}


