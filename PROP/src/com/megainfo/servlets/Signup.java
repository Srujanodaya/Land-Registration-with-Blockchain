package com.megainfo.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megainfo.dao.UserDAO;
import com.megainfo.model.User;
import com.megainfo.util.DateFormatter;

/**
 * Servlet implementation class signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		Boolean isUsernameExisting = false;
		int result = 0;
		int success = 0;
		/* User registration operation */
		if(operation.equalsIgnoreCase("user_signup")){
			System.out.println("Performing User Registration");
			Date today = new Date();
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setFirstname(request.getParameter("firstname"));
			user.setLastname(request.getParameter("lastname"));
			user.setDob(request.getParameter("dob"));
			user.setGender(request.getParameter("gender"));
			user.setPhone(request.getParameter("phone"));
			user.setEmail(request.getParameter("email"));
			user.setAddress(request.getParameter("address"));
			user.setCity(request.getParameter("city"));
			user.setState(request.getParameter("state"));
			user.setCountry(request.getParameter("country"));
			user.setZipcode(request.getParameter("zipcode"));
			user.setUser_type(request.getParameter("user_type"));
			DateFormatter df = new DateFormatter();
			String today_string = df.todayDateWithTime(today);
			user.setLast_login(today_string);
			
			UserDAO userDAO = new UserDAO();
			isUsernameExisting = userDAO.isUsernameExisting(user);
			if(isUsernameExisting){
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				request.setAttribute("errorExistingUsername", "Username already exists!");
				rd.forward(request, response);
			}else{
				result = userDAO.registerUser(user);
			}
			if(result > 0){
				System.out.println("User registration successfull");
				success = 1;
				response.sendRedirect("signup/success?s="+success);
			}
		}
	}

}
