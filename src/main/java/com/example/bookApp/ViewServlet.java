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

@WebServlet("/view")
public class ViewServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(req.getParameter("Id"));
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pw.print("<head>\r\n"
				+ "<meta charset='UTF-8'>\r\n"
				+ "<title>BookDetails</title>\r\n"
				+ "<link rel='stylesheet' href='css/bootstrap.css'>\r\n"
				+ "</head>");
		//pw.print(id);
		String qry="select * from bookdata where id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root");
			PreparedStatement ps=con.prepareStatement(qry);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				pw.print("<body><div class='mx-auto'><h4>Book Details</h4><d1 class='row'><dt class ='col-sm-2'>Id</dt>"
						+ "<dd class='col-sm-10'>"+rs.getInt(1)+"</dd>");
				pw.print("<d1 class='row'><dt class ='col-sm-2'>BookName</dt>"
						+ "<dd class='col-sm-10'>"+rs.getString(2)+"</dd>");
				pw.print("<d1 class='row'><dt class ='col-sm-2'>Edition</dt>"
						+ "<dd class='col-sm-10'>"+rs.getString(3)+"</dd>");
				pw.print("<d1 class='row'><dt class ='col-sm-2'>Author</dt>"
						+ "<dd class='col-sm-10'>"+rs.getString(4)+"</dd>");
				pw.print("<d1 class='row'><dt class ='col-sm-2'>Price</dt>"
						+ "<dd class='col-sm-10'>"+rs.getDouble(5)+"</dd>");
				
				pw.print("<form><button formaction='retrive'>Back</button></form></div></body>");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
