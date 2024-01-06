package com.example.bookApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/retrive")
public class RetrivalServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root");
			String qry="select * from BookData";
			PreparedStatement ps=con.prepareStatement(qry);
			ResultSet rs=ps.executeQuery();
			pw.print("<head>\r\n"
					+ "<meta charset='UTF-8'>\r\n"
					+ "<title>BooksList</title>\r\n"
					+ "<link rel='stylesheet' href='css/bootstrap.css'>\r\n"
					+ "</head>");
			pw.println("<body class='container-fluid card' style='width: 40rem;'>"
					+ "<h2 class=\"bg-danger text-white text-center\">Actions</h2>");
			pw.println("<form><button formAction='home.html'>Add Book</button>&nbsp;&nbsp;"
					+ "<button formaction='search.html'>SearchBook</button>&nbsp;&nbsp;"
					+ "<button formaction='searchauthor.html'>SearchAuthor</button>"+
					"</form>");
			pw.println("<br></br><h2 class='bg-danger text-white text-center'>BooksList</h2>");
			pw.println("<table class='table table-hover'><th>BookId</th><th>BookName</th><th>BookEdition</th>"
					+ "<th>BookAuthor</th><th>BookPrice</th><th>Operations</th>"
					+ "");
			while(rs.next()) {
				int id=rs.getInt(1);
				pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>"
						+ "<td>"+rs.getString(4)+"</td><td>"+rs.getDouble(5)+"</td><td><a href='edit?Id='10'/a>Edit</td></tr>");
				
			}
			pw.println("</table>");
			pw.print("<br></br>");
			pw.print("</body>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
