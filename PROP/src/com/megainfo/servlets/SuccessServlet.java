package com.megainfo.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SuccessServlet
 */
public class SuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int success = Integer.parseInt(request.getParameter("s"));
		RequestDispatcher rd = null;
		if(success == 1){
			request.setAttribute("msgUserSignUp", "User Registration Successfull");
			rd = request.getRequestDispatcher("/signup.jsp");
		}else if(success == 2){
			System.out.println("Success from success servlet");
			request.setAttribute("msgSuccess", "Product Registered Successfully");
			rd = request.getRequestDispatcher("/product_registration.jsp");
		}else if(success == 3){
			request.setAttribute("msgSuccess", "Profile Updated Successfully");
			/*rd = request.getRequestDispatcher("/user_profile.jsp");*/
			rd = request.getRequestDispatcher("/user/view/profile");
		}else if(success == 4){
			request.setAttribute("msgSuccess", "Product Modified Successfully");
			rd = request.getRequestDispatcher("/admin_product_modify.jsp");
		}
		else{
			request.setAttribute("msgError", "Error Occurred!");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
