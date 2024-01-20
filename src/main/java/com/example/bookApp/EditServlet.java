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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		//get print writer
				PrintWriter pw=res.getWriter();
				
				//Set Content type
				res.setContentType("text/html");
				//Get book data from form
				int id=Integer.parseInt(req.getParameter("bookId"));
				String bookName=req.getParameter("bookName");
				String bookEdition=req.getParameter("bookEdition");
				String bookAuthor=req.getParameter("bookAuthor");
				String bookPrice=req.getParameter("bookPrice");
				double price=Double.parseDouble(bookPrice);
				
				//JDBC steps
				
				String query="update bookData set bookName=?,edition=?,author=?,price=? where id=?";
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
					ps.setInt(5, id);
					ps.executeUpdate();
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
