package com.example.bookApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		//get print writer
				PrintWriter pw=res.getWriter();
				
				//Set Content type
				res.setContentType("text/html");
				//Get book data from form
				String bookName=req.getParameter("bookName");
				String bookEdition=req.getParameter("bookEdition");
				String bookAuthor=req.getParameter("bookAuthor");
				String bookPrice=req.getParameter("bookPrice");
				double price=Double.parseDouble(bookPrice);
				//JDBC steps
				
				String query="Insert into bookdata(BookName,Edition,Author,Price) values(?,?,?,?)";
				try {
					//Load Driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					//Generate Connection
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root");
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, bookName);
					ps.setString(2,bookEdition);
					ps.setString(3, bookAuthor);
					ps.setDouble(4, price);
					int count=ps.executeUpdate();
					/*pw.print("<html>\r\n"
							+ "<head>\r\n"
							+ "<meta charset='UTF-8'>\r\n"
							+ "<title>Add Book</title>\r\n"
							+ "<link rel='stylesheet' href='css/bootstrap.css'>\r\n"
							+ "</head>");
					pw.println("<body><h2 class='bg-danger text-white text-center'>Book added succesfully</h2>");
					pw.print("<br></br><form><button formaction='retrive'>Back To List</button></form></body></html>");*/
					res.sendRedirect("http://localhost:8080/BookApp/retrive");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req,res);
	}
	
}
