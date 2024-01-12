package com.megainfo.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.megainfo.dao.ProductDAO;
import com.megainfo.model.Product;
import com.megainfo.util.DateFormatter;

/**
 * Servlet implementation class ModifyServlet
 */
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String operation = request.getParameter("operation");
		String servletPath = request.getServletPath();
		Boolean isProductIDExisting = false;
		int result = 0;
		int success = 0;
		Date today = new Date();
		DateFormatter df = new DateFormatter();
		String today_string = df.todayDateWithTime(today);
		RequestDispatcher rd = null;
		String product_id = null;
		/* Modify Product Details */
		if (servletPath.equalsIgnoreCase("/admin/modify/product")) {
			Product product = new Product();
			ProductDAO productDAO = new ProductDAO();
			if (operation.equalsIgnoreCase("view_details")) {
				product_id = request.getParameter("product_id");
				product = productDAO.loadProductBYID(product_id);
				request.setAttribute("productDetails", product);
				rd = request.getRequestDispatcher("/admin_product_modify.jsp");
				rd.forward(request, response);
			} else if (operation.equalsIgnoreCase("modify_product")) {
				System.out.println("Modify Product Details");
				product.setProduct_id(request.getParameter("product_id"));
				product.setProduct_category(request.getParameter("product_category"));
				product.setProduct_description(request.getParameter("product_description"));
				product.setProduct_name(request.getParameter("product_name"));
				String product_unit_price = request.getParameter("product_unit_price");
				BigDecimal product_unit_price_BD = new BigDecimal(product_unit_price);
				product.setProduct_unit_price(product_unit_price_BD);

				result = productDAO.modifyProductDetails(product);
				if (result > 0) {
					System.out.println("Product modification successfull");
					success = 4;
					response.sendRedirect("product/success?s=" + success);
				}
			}
		} else {
			if (servletPath.equalsIgnoreCase("/admin/delete/product")) {
				Product product = new Product();
				ProductDAO productDAO = new ProductDAO();
				System.out.println("Deleate Product Details");
				product.setProduct_id(request.getParameter("product_id"));
				result = productDAO.deleteProduct(product);
				if (result > 0) {
					System.out.println("Item Deleted successfull");
					response.sendRedirect("/PROP/admin/view/products");
				} else {
					System.out.println("No Such Item to Delete");
				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
