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

@WebServlet("/searchAuthor")
public class SearchAuthor extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");
		int count=0;
		PrintWriter pw=res.getWriter();
		String author=req.getParameter("author");
		//pw.print(author);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root");
			String qry="select * from BookData where LOWER(Author) like lower(?)";
			PreparedStatement ps=con.prepareStatement(qry);
			//ps.setString(1,author);
			ps.setString(1, "%" + author + "%");
			ResultSet rs=ps.executeQuery();
			pw.print("<head>\r\n"
					+ "<meta charset='UTF-8'>\r\n"
					+ "<title>BooksList</title>\r\n"
					+ "<link rel='stylesheet' href='css/bootstrap.css'>\r\n"
					+ "</head>");
			pw.println("<body class='container-fluid card' style='width: 40rem;'>");
			//pw.print(search);
			pw.println("<br></br><h2 class='bg-danger text-white text-center'>Books</h2>");
			pw.println("<table border='1px' class='table table-hover'><th>BookId</th><th>BookName</th><th>BookEdition</th>"
					+ "<th>BookAuthor</th><th>BookPrice</th>");
			while(rs.next()) {
				//pw.print("Hello");
				pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"
						+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getDouble(5));
				count++;
			}
			if(count==0) {
				pw.print("<tr><td><strong>NA</strong></td><td><strong>NA</strong></td>"
						+ "<td><strong>NA</strong></td><td><strong>NA</strong></td>"
						+ "<td><strong>NA</strong></td></tr>");
			}
			
			pw.print("</table>"
					+ "<form><button formaction='retrive'>Back</button></form></body>");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
