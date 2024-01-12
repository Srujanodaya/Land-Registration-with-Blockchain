package com.megainfo.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.megainfo.model.Cart;
import com.megainfo.model.Order;
import com.megainfo.model.Product;
import com.megainfo.model.User;
import com.megainfo.util.DateFormatter;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doPost(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		Boolean isProductIDExisting = false;
		int result= 0;
		int success= 0;
		Date today = new Date();
		DateFormatter df = new DateFormatter();
		String today_string = df.todayDateWithTime(today);
		RequestDispatcher rd = null;
		/* Product Registration Operation */
		if(operation.equalsIgnoreCase("product_registration")){
			System.out.println("Performing Product Registration");
			Product product = new Product();
			
			product.setProduct_id(request.getParameter("product_id"));
			product.setProduct_name(request.getParameter("product_name"));
			product.setProduct_category(request.getParameter("product_category"));
			String product_unit_price = request.getParameter("product_unit_price");
			BigDecimal pup = new BigDecimal(product_unit_price);
			product.setProduct_unit_price(pup);
			product.setProduct_description(request.getParameter("product_description"));
			product.setPin_code(request.getParameter("pin_code"));
			product.setProduct_registered_on(today_string);
			
			ProductDAO productDAO = new ProductDAO();
			isProductIDExisting = productDAO.isProductIDExisting(product);
			if(isProductIDExisting){
				rd = request.getRequestDispatcher("/product_registration.jsp");
				request.setAttribute("errorExistingProductID", "Product ID already exists!");
				rd.forward(request, response);
			}else{
				result = productDAO.registerProduct(product);
			}
			if(result > 0){
				System.out.println("Product registration successfull");
				success = 2;
				response.sendRedirect("registration/success?s="+success);
			}
			
		}else if (operation.equalsIgnoreCase("add_to_cart")) {
			System.out.println("Add to Cart Servlet");
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			String product_id = request.getParameter("product_id");
			CartDAO cartDAO = new CartDAO();
			ProductDAO productDAO = new ProductDAO();
			List<Product> productDetails = new ArrayList<Product>();
			if(username == null || username == ""){
				System.out.println("Empty Session");
				productDetails = productDAO.loadRecentAddedProducts();
				request.setAttribute("productDetails", productDetails);
				request.setAttribute("msgError", "no_session");
				rd = request.getRequestDispatcher("/user_home.jsp");
				rd.forward(request, response);
			}else{
				Cart cart = new Cart();
				
				cart.setUsername(username);
				cart.setProduct_id(product_id);
				
				Boolean isProductAlreadyInCart = cartDAO.checkExistingProductInCart(cart);
				if(isProductAlreadyInCart){
					productDetails = productDAO.loadRecentAddedProducts();
					request.setAttribute("productDetails", productDetails);
					request.setAttribute("msgError", "product_existing");
					request.setAttribute("existing_product_id", cart.getProduct_id());
					rd = request.getRequestDispatcher("/user_home.jsp");
					rd.forward(request, response);
				}else{
					result = cartDAO.addToCart(cart);
					
					if(result > 0){
						productDetails = productDAO.loadRecentAddedProducts();
						request.setAttribute("productDetails", productDetails);
						request.setAttribute("msgSuccess", "Product : "+product_id+" is added to cart Successfully.");
						rd = request.getRequestDispatcher("/user_home.jsp");
						rd.forward(request, response);
					}else{
						productDetails = productDAO.loadRecentAddedProducts();
						request.setAttribute("productDetails", productDetails);
						request.setAttribute("msgError", "Product : "+product_id+" is not Added to Cart!!!");
						rd = request.getRequestDispatcher("/user_home.jsp");
						rd.forward(request, response);
					}
				}
			}
			
			
		}else if (operation.equalsIgnoreCase("pd_add_to_cart")) {
			System.out.println("Add to Cart Servlet from Product Details");
			HttpSession session = request.getSession();
			String username = (String)session.getAttribute("username");
			String product_id = request.getParameter("product_id");
			Product product = new Product();
			CartDAO cartDAO = new CartDAO();
			ProductDAO productDAO = new ProductDAO();
			if(username == null || username == ""){
				System.out.println("Empty Session");
				request.setAttribute("msgError", "no_session");
				rd = request.getRequestDispatcher("/user_product_details.jsp");
				rd.forward(request, response);
			}else{
				Cart cart = new Cart();
				
				cart.setUsername(username);
				cart.setProduct_id(product_id);
				
				Boolean isProductAlreadyInCart = cartDAO.checkExistingProductInCart(cart);
				if(isProductAlreadyInCart){
					product = productDAO.loadProductBYID(product_id);
					request.setAttribute("productDetails", product);
					request.setAttribute("msgError", "product_existing");
					request.setAttribute("existing_product_id", cart.getProduct_id());
					rd = request.getRequestDispatcher("/user_product_details.jsp");
					rd.forward(request, response);
				}else{
					cartDAO.updateAddToCartViews(username, product_id);
					result = cartDAO.addToCart(cart);
					
					if(result > 0){
						product = productDAO.loadProductBYID(product_id);
						request.setAttribute("productDetails", product);
						request.setAttribute("msgSuccess", "add_to_cart_success");
						request.setAttribute("added_product_id", product_id);
						rd = request.getRequestDispatcher("/user_product_details.jsp");
						rd.forward(request, response);
					}else{
						product = productDAO.loadProductBYID(product_id);
						request.setAttribute("productDetails", product);
						request.setAttribute("msgError", "Product : "+product_id+" is not Added to Cart!!!");
						rd = request.getRequestDispatcher("/user_product_details.jsp");
						rd.forward(request, response);
					}
				}
			}
			
			
		}else if(operation.equalsIgnoreCase("update_user_profile")){
			System.out.println("Update user profile");
			/*update the user profile*/
			User user = new User();
			
			user.setFirstname(request.getParameter("firstname"));
			user.setLastname(request.getParameter("lastname"));
			user.setDob(request.getParameter("dob"));
			user.setPhone(request.getParameter("phone"));
			user.setEmail(request.getParameter("email"));
			user.setAddress(request.getParameter("address"));
			user.setCity(request.getParameter("city"));
			user.setState(request.getParameter("state"));
			user.setCountry(request.getParameter("country"));
			user.setZipcode(request.getParameter("zipcode"));
			user.setUser_type(request.getParameter("user_type"));
			
			UserDAO userDAO = new UserDAO();
			result = userDAO.updateUserProfile(user);
			if(result > 0){
				success = 3;
				response.sendRedirect("profile/success?s="+success);
			}
		}else if(operation.equalsIgnoreCase("order_product")){
			System.out.println("Order Cart Product Method Called");
			
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			String product_id = request.getParameter("ordered_product_id");
			if(username == null){
				request.setAttribute("msgError", "no_session");
				rd = request.getRequestDispatcher("/user_cart.jsp");
				rd.forward(request, response);
			}else{
				Order order = new Order();
				
				order.setIs_order_cancelled(false);
				order.setIs_order_delivered(false);
				order.setOrder_status("Pending");
				order.setOrdered_by(username);
				order.setOrdered_on(today_string);
				order.setOrdered_product_id(request.getParameter("ordered_product_id"));
				order.setOrdered_product_units(Integer.parseInt(request.getParameter("ordered_product_units")));
				
				String unit_price = request.getParameter("unit_price");
				BigDecimal unit_price_BD = new BigDecimal(unit_price);
				BigDecimal ordered_product_units_BD = new BigDecimal(request.getParameter("ordered_product_units"));
				
				BigDecimal total_order_amount = unit_price_BD.multiply(ordered_product_units_BD);
				order.setTotal_order_amount(total_order_amount);
				
				OrderDAO orderDAO = new OrderDAO();
				orderDAO.updateOrderCount(username, product_id);
				result = orderDAO.orderProduct(order);
				
				CartDAO cartDAO = new CartDAO();
				ProductDAO productDAO = new ProductDAO();
				List<String> productIDList = new ArrayList<String>();
				
				if(result > 0){
					productIDList = cartDAO.loadProductID(username);
					List<Product> productDetails = new ArrayList<Product>();
					for(String pid : productIDList){
						productDetails.add(productDAO.loadProductBYID(pid));
					}
					request.setAttribute("productDetails", productDetails);
					request.setAttribute("msgSuccess", "order_success");
					request.setAttribute("ordered_product_id", order.getOrdered_product_id());
					rd = request.getRequestDispatcher("/user_cart.jsp");
					rd.forward(request, response);
				}else{
					productIDList = cartDAO.loadProductID(username);
					List<Product> productDetails = new ArrayList<Product>();
					for(String pid : productIDList){
						productDetails.add(productDAO.loadProductBYID(pid));
					}
					request.setAttribute("productDetails", productDetails);
					request.setAttribute("msgError", "order_failed");
					request.setAttribute("ordered_product_id", order.getOrdered_product_id());
					rd = request.getRequestDispatcher("/user_cart.jsp");
					rd.forward(request, response);
				}
				
			}
		}
	}

}
