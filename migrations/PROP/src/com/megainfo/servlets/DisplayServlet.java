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

import com.megainfo.dao.ProductDAO;
import com.megainfo.model.Product;

/**
 * Servlet implementation class DisplayServlet
 */
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayServlet() {
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
		String servletPath = request.getServletPath();
		String operation = null;
		RequestDispatcher rd = null;
		Boolean isFirstProductView = false;
		String product_id = null;
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if (servletPath.equalsIgnoreCase("/user/view/products")) {
			System.out.println("User view products invoked");
			int page = 1;
			int recordsPerPage = 15;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			ProductDAO productDAO = new ProductDAO();
			List<Product> productDetails = new ArrayList<Product>();
			productDetails = productDAO.loadAllProducts((page - 1) * recordsPerPage, recordsPerPage);
			int noOfRecords = productDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("productDetails", productDetails);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			rd = request.getRequestDispatcher("/product_details.jsp");
			/* rd.forward(request, response); */
		} else if (servletPath.equalsIgnoreCase("/user/view/product/details")) {
			operation = request.getParameter("operation");
			product_id = request.getParameter("product_id");
			Product product = new Product();
			if (username == null) {
				request.setAttribute("msgError", "no_session");
				if (operation.equalsIgnoreCase("home_view_details")) {
					rd = request.getRequestDispatcher("/user_home.jsp");
				} else if (operation.equalsIgnoreCase("pd_view_details")) {
					rd = request.getRequestDispatcher("/product_details.jsp");
				}
			} else {
				ProductDAO productDAO = new ProductDAO();
				isFirstProductView = productDAO.checkFirstProductView(username, product_id);
				if (isFirstProductView) {
					productDAO.insertFirstProductView(username, product_id);
					System.out.println("New View Added");
					product = productDAO.loadProductBYID(product_id);
					request.setAttribute("productDetails", product);
					rd = request.getRequestDispatcher("/user_product_details.jsp");
				} else {
					productDAO.updateProductViews(username, product_id);
					System.out.println("View Count Incremented");

					product = productDAO.loadProductBYID(product_id);
					request.setAttribute("productDetails", product);
					rd = request.getRequestDispatcher("/user_product_details.jsp");
				}
			}
		} else if (servletPath.equalsIgnoreCase("/user/view/products/filter")) {
			System.out.println("Filtering Products");
			String category = request.getParameter("category");
			String price = request.getParameter("price");
			String product = request.getParameter("product").trim();
			String pin_code = request.getParameter("pin_code");
			String is_product_ordered = "0";
			System.out.println("pin_code " + pin_code);

			if (username == null) {
				request.setAttribute("msgError", "no_session");
			} else {
				ProductDAO productDAO = new ProductDAO();
				Boolean isFirstCategorySearch, isFirstProductSearch, isFirstPinSearch,
						isFirstCategoryProductSearch = false;
				Boolean isProductExisting = false;
				if (category != "" && product == "") {
					isFirstCategorySearch = productDAO.checkFirstCategorySearch(username, category);
					if (isFirstCategorySearch) {
						System.out.println("First Category Search ...");
						productDAO.insertFirstSearchCount(username, category, product, pin_code);
					} else {
						System.out.println("Not First Category Search");
						productDAO.updateSearchCount(username, category, product);
					}
				} else if (category == "" && product != "") {
					isProductExisting = productDAO.checkProductExistence(product);
					if (isProductExisting) {
						System.out.println("Product Found");
						isFirstProductSearch = productDAO.checkFirstProductSearch(username, product);
						if (isFirstProductSearch) {
							System.out.println("First Product Search ...");
							productDAO.insertFirstSearchCount(username, category, product, pin_code);
						} else {
							System.out.println("Not First Product Search");
							productDAO.updateSearchCount(username, category, product);
						}
					}
				} else if (category == "" && pin_code != "") {
					isProductExisting = productDAO.checkProductExistenceWithPin(pin_code);
					System.out.println("isProductExistingWithPin " + isProductExisting);
					if (isProductExisting) {
						isFirstPinSearch = productDAO.checkFirstPinSearch(username, pin_code);
						System.out.println("Product with PIN Found " + isFirstPinSearch);
						if (isFirstPinSearch) {
							System.out.println("First Product Search with Pin ...");
							productDAO.insertFirstSearchCount(username, category, product, pin_code);
						} else {
							System.out.println("Not First Product Search with Pin");
							productDAO.updateSearchCount(username, category, product);
						}
					}
				} else if (category != "" && product != "") {
					isProductExisting = productDAO.checkProductExistence(product);
					if (isProductExisting) {
						System.out.println("Product Found");
						isFirstCategoryProductSearch = productDAO.checkFirstCategoryProductSearch(username, category,
								product);
						if (isFirstCategoryProductSearch) {
							System.out.println("First Category Product Search ...");
							productDAO.insertFirstSearchCount(username, category, product, pin_code);
						} else {
							System.out.println("Not First Category Product Search");
							productDAO.updateSearchCount(username, category, product);
						}
					}
				}

				List<Product> productDetails = new ArrayList<Product>();
				productDetails = productDAO.filterProducts(category, price, product, pin_code);
				request.setAttribute("productDetails", productDetails);
				request.setAttribute("filter_category", category);
				request.setAttribute("filter_product", product);
				request.setAttribute("filter_pin", pin_code);

			}
			rd = request.getRequestDispatcher("/product_details.jsp");
		} else if (servletPath.equalsIgnoreCase("/admin/view/products")) {
			System.out.println("Admin view products invoked");
			int page = 1;
			int recordsPerPage = 15;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			ProductDAO productDAO = new ProductDAO();
			List<Product> productDetails = new ArrayList<Product>();
			productDetails = productDAO.loadAllProducts((page - 1) * recordsPerPage, recordsPerPage);
			int noOfRecords = productDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("productDetails", productDetails);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			rd = request.getRequestDispatcher("/admin_product_details.jsp");
		}
		rd.forward(request, response);
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
