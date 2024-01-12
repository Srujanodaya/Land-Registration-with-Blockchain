package com.megainfo.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megainfo.dao.UserDAO;
import com.megainfo.model.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String user_type = request.getParameter("user_type");
		String servletUrl = request.getServletPath();
		System.out.println("Path : " + servletUrl);

		if (user_type.equals("Admin")) {
			if (username.equals("admin") && password.equals("admin")) {
				session.setAttribute("userid", username);
				/*
				 * RequestDispatcher rd = request.getRequestDispatcher("admin/home");
				 * rd.forward(request, response);
				 */
				response.sendRedirect("admin/home");
			} else {
				session.setAttribute("userid", username);
				request.setAttribute("message", "Invalid Login Credentials!");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.include(request, response);
			}
		} else if (user_type.equals("buyer")) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setUser_type(user_type);
			String last_login = null;
			System.out.println("Check User Login Credentials");
			UserDAO userDAO = new UserDAO();
			Boolean isValidUserLogin = userDAO.isValidUserLogin(user);
			if (isValidUserLogin) {
				last_login = userDAO.loadLastLogin(username);
				session.setAttribute("last_login", last_login);
				userDAO.updateLastLogin();
				session.setAttribute("username", username);
				/*
				 * RequestDispatcher rd = request.getRequestDispatcher("admin/home");
				 * rd.forward(request, response);
				 */
				response.sendRedirect("user/home");
			} else {
				request.setAttribute("message", "Invalid Login Credentials!");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.include(request, response);
			}
		}

		else if (user_type.equals("seller")) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setUser_type(user_type);
			String last_login = null;
			System.out.println("Check User Login Credentials");
			UserDAO userDAO = new UserDAO();
			Boolean isValidUserLogin = userDAO.isValidUserLogin(user);
			if (isValidUserLogin) {
				last_login = userDAO.loadLastLogin(username);
				session.setAttribute("last_login", last_login);
				userDAO.updateLastLogin();
				session.setAttribute("username", username);
				/*
				 * RequestDispatcher rd = request.getRequestDispatcher("admin/home");
				 * rd.forward(request, response);
				 */
				response.sendRedirect("admin/home");
			} else {
				request.setAttribute("message", "Invalid Login Credentials!");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.include(request, response);
			}
		}
	}

}
