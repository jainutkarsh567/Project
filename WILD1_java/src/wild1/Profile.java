package wild1;

import java.io.IOException;
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

/**
 * Servlet implementation class Profile
 */

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		 try{  
			  
		        response.setContentType("text/html");  
		        PrintWriter out = response.getWriter();  
		          
		        HttpSession session=request.getSession(false);  
		        String n=(String)session.getAttribute("login"); 
		        System.out.println("profile" + n);
		        
		        try{  
		        	UserLogin users = new Gson().fromJson(n, UserLogin.class); 
			    	Class.forName("com.mysql.cj.jdbc.Driver");  
			    	Connection con=DriverManager.getConnection(  
			    	"jdbc:mysql://localhost:3306/user_database","root","ukki12345");   
			    	Statement stmt=con.createStatement();  
			    	ResultSet rs=stmt.executeQuery("select * from user_info where username='"+users.getUsername()+"'"); 
			    	String json;
			    	System.out.println(rs.getRow());
			    	if(rs.next())
			    	{
			    		Pojo pojo=new Pojo(rs.getString("username"),rs.getString("uname"),rs.getInt("age"),rs.getString("gender"));
			    		String profile=new Gson().toJson(pojo);
			    		out.println(profile);
			    	}
		        
		       
		  
		        out.close();  
		  
		                }catch(Exception e){System.out.println(e);}  
		  
		 

}catch(Exception e){System.out.println(e);}  
	}
}
	

