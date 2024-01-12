package com.megainfo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.megainfo.dao.CartDAO;
import com.megainfo.dao.OrderDAO;
import com.megainfo.dao.ProductDAO;
import com.megainfo.dao.UserDAO;
import com.megainfo.model.Order;
import com.megainfo.model.Product;
import com.megainfo.model.User;

/**
 * Servlet implementation class RedirectServlet
 */
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String servletPath = request.getServletPath();
		RequestDispatcher rd = null;
		//String last_login = null;
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(servletPath.equalsIgnoreCase("/login")){
			session.invalidate();
			rd = request.getRequestDispatcher("/login.jsp");		
		}else if(servletPath.equalsIgnoreCase("/home")){
			rd = request.getRequestDispatcher("/index.jsp");
		}else if(servletPath.equalsIgnoreCase("/user/signup")){
			rd = request.getRequestDispatcher("/signup.jsp");
		}else if(servletPath.equalsIgnoreCase("/admin/home")){
			rd = request.getRequestDispatcher("/admin_home.jsp");
		}else if(servletPath.equalsIgnoreCase("/admin/product/registration")){
			rd = request.getRequestDispatcher("/product_registration.jsp");
		}else if(servletPath.equalsIgnoreCase("/user/home")){
			//UserDAO userDAO = new UserDAO();
			ProductDAO productDAO = new ProductDAO();
			//last_login = userDAO.loadLastLogin(username);
			List<Product> productDetails = new ArrayList<Product>();
			productDetails = productDAO.loadRecentAddedProducts();
			System.out.println("Product Size : " +productDetails.size());
			//request.setAttribute("last_login", last_login);
			request.setAttribute("productDetails", productDetails);
			rd = request.getRequestDispatcher("/user_home.jsp");
		}else if(servletPath.equalsIgnoreCase("/user/view/cart")){
			CartDAO cartDAO = new CartDAO();
			ProductDAO productDAO = new ProductDAO();
			List<String> productIDList = new ArrayList<String>();
			productIDList = cartDAO.loadProductID(username);
			List<Product> productDetails = new ArrayList<Product>();
			for(String pid : productIDList){
				System.out.println("Product ID List : " +pid);
				productDetails.add(productDAO.loadProductBYID(pid));
			}
			request.setAttribute("productDetails", productDetails);
			rd = request.getRequestDispatcher("/user_cart.jsp");
		}else if(servletPath.equalsIgnoreCase("/user/view/profile")){
			if(username == null){
				request.setAttribute("msgError", "No Session");
			}
			UserDAO userDAO = new UserDAO();
			User user = new User();
			User userDetails = new User();
			user.setUsername(username);
			userDetails = userDAO.loadUserDetails(user);
			request.setAttribute("userDetails", userDetails);
			rd = request.getRequestDispatcher("/user_profile.jsp");
		}else if(servletPath.equalsIgnoreCase("/user/edit/profile")){
			UserDAO userDAO = new UserDAO();
			User user = new User();
			User userDetails = new User();
			user.setUsername(username);
			userDetails = userDAO.loadUserDetails(user);
			request.setAttribute("userDetails", userDetails);
			rd = request.getRequestDispatcher("/user_edit_profile.jsp");
		}else if(servletPath.equalsIgnoreCase("/user/view/orders")){
			if(username == null){
				System.out.println("Null Session");
				request.setAttribute("msgError", "no_session");
			}else{
				Order order = new Order();
				order.setOrdered_by(username);
				order.setIs_order_cancelled(false);
				order.setIs_order_delivered(false);
				
				OrderDAO orderDAO = new OrderDAO();
				List<Order> orderDetails = new ArrayList<Order>();
				orderDetails = orderDAO.loadAllOrders(order);
				System.out.println("Order Size : " +orderDetails.size());
				request.setAttribute("orderDetails", orderDetails);
			}
			rd = request.getRequestDispatcher("/user_orders.jsp");
		}
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
