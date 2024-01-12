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
import com.megainfo.dao.ProductDAO;
import com.megainfo.model.Cart;
import com.megainfo.model.Product;

/**
 * Servlet implementation class RemoveServlet
 */
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String operation = request.getParameter("operation");
		int result= 0;
		//int success= 0;
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		RequestDispatcher rd = null;
		if(operation.equalsIgnoreCase("remove_from_cart")){
			System.out.println("User remove product from cart invoked");
			String product_id = request.getParameter("product_id");
			Cart cart = new Cart();
			cart.setProduct_id(product_id);
			cart.setUsername(username);
			CartDAO cartDAO = new CartDAO();
			ProductDAO productDAO = new ProductDAO();
			List<String> productIDList = new ArrayList<String>();
			
			if(username == null){
				request.setAttribute("msgError", "no_session");
				rd = request.getRequestDispatcher("/user_cart.jsp");
				rd.forward(request, response);
			}else{
				result = cartDAO.removeProductFromCart(cart);
				if(result > 0){
					productIDList = cartDAO.loadProductID(username);
					List<Product> productDetails = new ArrayList<Product>();
					for(String pid : productIDList){
						System.out.println("Product ID List : " +pid);
						productDetails.add(productDAO.loadProductBYID(pid));
					}
					request.setAttribute("msgSuccess", "product_removed");
					request.setAttribute("removed_product_id", cart.getProduct_id());
					request.setAttribute("productDetails", productDetails);
					rd = request.getRequestDispatcher("/user_cart.jsp");
					rd.forward(request, response);
				}
			}	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
