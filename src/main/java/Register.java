import java.io.*;  
import java.sql.*; 
//import java.sql.Date; 

import javax.servlet.ServletException;  
import javax.servlet.http.*; 
import javax.servlet.annotation.*;
//import java.util.Date;
  @WebServlet("/Register")
public class Register extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
        
String firstname=request.getParameter("firstname");  
String lastname=request.getParameter("lastname");  
String email=request.getParameter("email");  
String date=request.getParameter("date");  
String time=request.getParameter("time");  
String topic=request.getParameter("topic");  
String location=request.getParameter("Location");
//SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy");
//Date dt =objSDF.parse(date);


try{
            Class.forName(
                "com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/event_registration", "root", "");
            
            PreparedStatement ps=conn.prepareStatement("insert into event values(?,?,?,?,?,?,?)");
         //ps.executeUpdate("insert into registeruser values(?,?,?,?,?,?,?)");
        
ps.setString(1,firstname);  
ps.setString(2,lastname);  
ps.setString(3,email);  
ps.setString(4,date);
//ps.setDate(4, (java.sql.Date)dt);
ps.setString(5,time);  
ps.setString(6,topic);  
ps.setString(7,location);    
int i=ps.executeUpdate();  

ResultSet rs = ps.executeQuery("Select * from event");

out.println("<table border=1 style='background-color:White'>");

while (rs.next())
 {
out.println("<tr><td>" + rs.getString(1) + "<td>" + rs.getString(2) + "<td>" + rs.getString(3) + "<td>" + rs.getString(4) + "<td>" + rs.getString(5) + "<td>"
+ rs.getString(6) + "<td>"+ rs.getString(7) + "</tr>");
}
out.println("</table>");

out.print("You are successfully registered..."); 
out.close();
conn.close();
}

/*
if(i>0) 
 
out.print("You are successfully registered..."); 
      conn.close();

            }*/
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
}


