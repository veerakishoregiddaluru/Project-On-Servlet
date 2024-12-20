

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class April
 */

public class November2021 extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String path="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/jntua";
	String sql="select * from results where roll=?";
	String user="root";
	String pass="Kishore214@";
	PrintWriter pw=null;

		public void init() throws ServletException {
			
			
			try {
				Class.forName(path);
				con=DriverManager.getConnection(url,user,pass);
				pstmt=con.prepareStatement(sql);
			
				
				
			}catch (Exception e) {
e.printStackTrace();

			}
		}
		public void service(HttpServletRequest req, HttpServletResponse res)  {
			try {
	String id=req.getParameter("roll");
			int roll1=Integer.parseInt(id);
		pstmt.setInt(1, roll1);
		pw=res.getWriter();
		rs=pstmt.executeQuery();
			pw.println("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head><title>Jntua Results Out Now</title></head>");
			pw.println("<body>");
			pw.println("<img src='jntua.jpeg' height=100 width=600/>");
			pw.println("<h1 style='color:red'>Welcome!</h2>");
			pw.println("<h1 style='color:green'>Check Your Results Here!</h2>");

			pw.println("<table border=2><tr><th>Roll No</th><th>Name</th><th>M1</th><th>M2</th><th>M3</th></tr>");
		if(rs.next()) {
			int roll=rs.getInt(1);
			String name=rs.getString(2);
			int m1=rs.getInt(3);
			int m2=rs.getInt(4);
			int m3=rs.getInt(5);
			pw.println("<tr><td>"+roll+"</td><td>"+name+"</td><td>"+m1+"</td><td>"+m2+"</td><td>"+m3+"</td></tr>");
			

	
		pw.println("</table></body></html>");
		}
		else {
			res.sendRedirect("/JntuaResults2/InvalidResults.html");
		}

		pw.println("<br>");
		pw.println("<h1>Note:</h1><p style='color:red'>Result Disclaimer : Data and information are provided for informational purposes only, and the final results will be given by the respective Controller of Examinations.</p>");
			}catch (Exception e) {

			e.printStackTrace();
			}
		}
		@Override
			public void destroy() {
				try {
					
					
					con.close();
					pstmt.close();
					rs.close();
					pw.close();
				}catch (Exception e) {
e.printStackTrace();				}
				
			}
	

}
