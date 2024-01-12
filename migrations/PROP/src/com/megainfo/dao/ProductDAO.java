package com.megainfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megainfo.model.Product;
import com.megainfo.util.AESEncryption;
import com.megainfo.util.AESUtil;
import com.megainfo.util.DBUtil;
import com.megainfo.util.DateFormatter;
import com.megainfo.util.db.DBConnection;

public class ProductDAO {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private int noOfRecords;
	Date today = new Date();
	DateFormatter df = new DateFormatter();
	String today_string = df.todayDateWithTime(today);

	public int registerProduct(Product product) {
		int result = 0;
		String query = "INSERT INTO product_details VALUES(?,?,?,?,?,?,?,?)";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			String encryptedPid = AESEncryption.encrypt(product.getProduct_id(), AESUtil.key, AESUtil.salt);
			// ps.setString(1, encryptedPid);
			ps.setString(1, product.getProduct_id());
			ps.setString(2, product.getProduct_name());
			ps.setString(3, product.getProduct_category());
			ps.setBigDecimal(4, product.getProduct_unit_price());
			ps.setString(5, product.getProduct_description());
			ps.setString(6, product.getProduct_registered_on());
			ps.setString(7, product.getPin_code());
			ps.setString(8, "0");

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}

	public Boolean isProductIDExisting(Product product) {
		Boolean flag = false;
		String query = "SELECT * FROM product_details WHERE product_id = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			String decryptPID = AESEncryption.encrypt(product.getProduct_id(), AESUtil.key, AESUtil.salt);
			ps.setString(1, product.getProduct_id());
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public List<Product> loadRecentAddedProducts() {
		List<Product> list = new ArrayList<Product>();
		String query = "SELECT SQL_CALC_FOUND_ROWS * FROM product_details ORDER BY product_registered_on DESC LIMIT 10";
		query = "SELECT * FROM product_details WHERE NOT EXISTS (SELECT * FROM order_details WHERE order_details.ordered_product_id = product_details.product_id)  AND is_product_ordered = \"0\" ORDER BY product_registered_on DESC LIMIT 10";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				String PID = rs.getString("product_id").substring(0, 3);
				product.setProduct_id(PID);
				// product.setProduct_id(rs.getString("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_unit_price(rs.getBigDecimal("product_unit_price"));
				product.setProduct_description(rs.getString("product_description"));
				product.setPin_code(rs.getString("pin_code"));
				System.out.println("is_product_ordered: " + rs.getString("is_product_ordered"));
				product.setIs_product_ordered(rs.getString("is_product_ordered"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return list;
	}

	public Product loadProductBYID(String product_id) {
		Product product = new Product();
		String query = "SELECT * FROM product_details WHERE product_id = ? AND is_product_ordered = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, product_id);
			ps.setString(2, "0");
			rs = ps.executeQuery();
			if (rs.next()) {
				product.setProduct_id(rs.getString("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_unit_price(rs.getBigDecimal("product_unit_price"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_description(rs.getString("product_description"));
				product.setPin_code(rs.getString("pin_code"));
				product.setIs_product_ordered(rs.getString("is_product_ordered"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return product;
	}

	public List<Product> loadAllProducts(int offset, int noOfRecords) {
		List<Product> list = new ArrayList<Product>();
		String query = null;// "SELECT SQL_CALC_FOUND_ROWS * FROM product_details ORDER BY
							// product_registered_on DESC LIMIT ?,?";
		query = "SELECT * FROM product_details WHERE NOT EXISTS (SELECT * FROM order_details WHERE order_details.ordered_product_id = product_details.product_id)  AND is_product_ordered = \"0\" ORDER BY product_registered_on DESC LIMIT ?,?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, offset);
			ps.setInt(2, noOfRecords);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getString("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_unit_price(rs.getBigDecimal("product_unit_price"));
				product.setProduct_description(rs.getString("product_description"));
				product.setPin_code(rs.getString("pin_code"));
				product.setIs_product_ordered("0");
				list.add(product);
			}
			System.out.println("Product List Size : " + list.size());
			rs.close();

			rs = ps.executeQuery("SELECT FOUND_ROWS()");
			if (rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return list;
	}

	public Product loadProductId() {
		Product product = new Product();
		String query = "SELECT * FROM product_details ORDER BY PRODUCT_ID DESC";
		int pid = 0;
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				pid = Integer.parseInt(rs.getString("product_id"));
				pid = pid + 1;

			}
			product.setProduct_id("" + pid);
			System.out.println("Next ID: " + product.getProduct_id());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return product;
	}

	public List<Product> loadProductId1() {
		List<Product> list = new ArrayList<Product>();
		String query = null;// "SELECT SQL_CALC_FOUND_ROWS * FROM product_details ORDER BY
							// product_registered_on DESC LIMIT ?,?";
		query = "SELECT * FROM product_details ORDER BY PRODUCT_ID DESC";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				int pid = Integer.parseInt(rs.getString("product_id"));
				pid = pid + 1;
				product.setProduct_id("" + pid);
				list.add(product);
			}
			System.out.println("Product List Size : " + list.size());
			rs.close();

			rs = ps.executeQuery("SELECT FOUND_ROWS()");
			if (rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		System.out.println("Product ID: " + list.get(0).getProduct_id());
		return list;
	}

	public Boolean checkFirstProductView(String username, String product_id) {
		Boolean flag = false;
		String query = "SELECT view_count FROM count_details WHERE (username = ? AND product_id = ?)";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, product_id);
			// ps.setString(3, "is_product_ordered");
			rs = ps.executeQuery();
			if (!rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public void insertFirstProductView(String username, String product_id) {
		String query = "INSERT INTO count_details(product_id, username, view_count, view_date) " + "VALUES(?,?,?,?)";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, product_id);
			ps.setString(2, username);
			ps.setInt(3, 1);
			ps.setString(4, today_string);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	public void updateProductViews(String username, String product_id) {
		String queryFetchViewCount = "SELECT view_count FROM count_details WHERE (product_id=? AND username = ?)";
		String queryUpdateViewCount = "UPDATE count_details SET view_count=?, view_date=? WHERE (product_id=? AND username = ?)";
		Integer view_count = null;
		try {
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			PreparedStatement ps_FetchViewCount = con.prepareStatement(queryFetchViewCount);
			ps_FetchViewCount.setString(1, product_id);
			ps_FetchViewCount.setString(2, username);
			rs = ps_FetchViewCount.executeQuery();
			if (rs.next()) {
				view_count = rs.getInt("view_count");
			}
			view_count += 1;
			rs.close();
			PreparedStatement ps_UpdateViewCount = con.prepareStatement(queryUpdateViewCount);
			ps_UpdateViewCount.setInt(1, view_count);
			ps_UpdateViewCount.setString(2, today_string);
			ps_UpdateViewCount.setString(3, product_id);
			ps_UpdateViewCount.setString(4, username);
			ps_UpdateViewCount.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.close(rs);
			DBUtil.close(con);
		}
	}

	public List<Product> filterProducts(String category, String price, String product_name, String pin_code) {
		String query = null;
		List<Product> list = new ArrayList<Product>();
		System.out.println("filterProducts called! " + pin_code);

		try {
			con = DBConnection.getDBConnection();
			if ((price == "") && (category != "") && (product_name == "")) {
				query = "SELECT * FROM product_details WHERE product_category = ? AND is_product_ordered = ? ORDER BY product_registered_on DESC";
				ps = con.prepareStatement(query);
				ps.setString(1, category);
				ps.setString(2, "0");
			} else if ((category == "") && (price == "") && (product_name == "") && (pin_code == "")) {
				query = "SELECT * FROM product_details WHERE  is_product_ordered = \"0\" ORDER BY product_registered_on DESC";
				query = "SELECT * FROM product_details WHERE NOT EXISTS (SELECT * FROM order_details WHERE order_details.ordered_product_id = product_details.product_id)  AND is_product_ordered = \"0\" ORDER BY product_registered_on DESC LIMIT 100";
				ps = con.prepareStatement(query);
			} else if ((category == "") && (price == "") && (product_name != "")) {
				query = "SELECT * FROM product_details WHERE product_name = ? AND is_product_ordered = ? ORDER BY product_registered_on DESC";
				ps = con.prepareStatement(query);
				ps.setString(1, product_name);
				ps.setString(2, "0");
			} else if ((category != "") && (price == "") && (product_name != "")) {
				query = "SELECT * FROM product_details WHERE (product_name = ? AND product_category = ? AND is_product_ordered = ?) "
						+ "ORDER BY product_registered_on DESC";
				ps = con.prepareStatement(query);
				ps.setString(1, product_name);
				ps.setString(2, category);
				ps.setString(3, "0");
			} else if ((category == "") && (price == "") && (product_name == "") && (pin_code != "")) {
				System.out.println("PIN CODE " + pin_code);
				query = "SELECT * FROM product_details WHERE pin_code = ? AND is_product_ordered = ?"
						+ "ORDER BY product_registered_on DESC";
				ps = con.prepareStatement(query);
				ps.setString(1, pin_code);
				ps.setString(2, "0");
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_id(rs.getString("product_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_category(rs.getString("product_category"));
				product.setProduct_unit_price(rs.getBigDecimal("product_unit_price"));
				product.setProduct_description(rs.getString("product_description"));
				product.setPin_code(rs.getString("pin_code"));
				product.setIs_product_ordered(rs.getString("is_product_ordered"));
				list.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return list;
	}

	public Boolean checkFirstCategorySearch(String username, String category) {
		Boolean flag = false;
		String query = "SELECT * FROM search_count_details WHERE (username = ? AND category = ?)";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, category);
			// ps.setString(3, "0");
			rs = ps.executeQuery();
			if (!rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public Boolean checkFirstProductSearch(String username, String product) {
		Boolean flag = false;
		String query = "SELECT * FROM search_count_details WHERE (username = ? AND product = ?)";
		System.out.println("username " + username + " product " + product);
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, product);
			ps.setString(3, "0");
			// rs = ps.executeQuery();
			if (!rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public Boolean checkFirstPinSearch(String username, String pin_code) {
		Boolean flag = false;
		String query = "SELECT * FROM search_count_details WHERE (username = ? AND pin_code = ?)";
		System.out.println("username " + username + " pin_code " + pin_code);
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, pin_code);
			// ps.setString(3, "0");
			rs = ps.executeQuery();
			if (!rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public Boolean checkFirstCategoryProductSearch(String username, String category, String product) {
		Boolean flag = false;
		String query = "SELECT * FROM search_count_details WHERE (username = ? AND category = ? AND product = ?)";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, category);
			ps.setString(3, product.toLowerCase());
			// ps.setString(4, "0");
			rs = ps.executeQuery();
			if (!rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public void insertFirstSearchCount(String username, String category, String product, String pin_code) {
		String query = null;
		try {
			con = DBConnection.getDBConnection();
			if (category != "" && product == "") {
				query = "INSERT INTO search_count_details(username, category, category_search_count, recent_category_search_date) "
						+ "VALUES(?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, category);
				ps.setInt(3, 1);
				ps.setString(4, today_string);
			} else if (category == "" && product != "") {
				query = "INSERT INTO search_count_details(username, product, product_search_count, recent_product_search_date) "
						+ "VALUES(?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, product.toLowerCase());
				ps.setInt(3, 1);
				ps.setString(4, today_string);
			} else if (category == "" && product == "" && pin_code != "") {
				query = "INSERT INTO search_count_details(username, product, product_search_count, recent_product_search_date, pin_code) "
						+ "VALUES(?,?,?,?,?)";
				System.out.println("query " + query);
				ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, product.toLowerCase());
				ps.setInt(3, 1);
				ps.setString(4, today_string);
				ps.setString(5, pin_code);
			}

			else if (category != "" && product != "") {
				query = "INSERT INTO search_count_details(username, category, category_search_count, recent_category_search_date, "
						+ "product, product_search_count, recent_product_search_date) VALUES(?,?,?,?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, category);
				ps.setInt(3, 1);
				ps.setString(4, today_string);
				ps.setString(5, product.toLowerCase());
				ps.setInt(6, 1);
				ps.setString(7, today_string);
			} else if (category != "" && product != "") {
				query = "INSERT INTO search_count_details(username, category, category_search_count, recent_category_search_date, "
						+ "product, product_search_count, recent_product_search_date) VALUES(?,?,?,?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, username);
				ps.setString(2, category);
				ps.setInt(3, 1);
				ps.setString(4, today_string);
				ps.setString(5, product.toLowerCase());
				ps.setInt(6, 1);
				ps.setString(7, today_string);
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	public void updateSearchCount(String username, String category, String product) {
		String query_FetchCategoryCount = null;
		String query_FetchProductCount = null;
		String query_FetchCategoryProductCount = null;
		String query_UpdateCategoryCount = null;
		String query_UpdateProductCount = null;
		String query_UpdateCategoryProductCount = null;
		Integer category_count = null, product_count = null;
		try {
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			if (category != "" && product == "") {
				query_FetchCategoryCount = "SELECT category_search_count FROM search_count_details WHERE "
						+ "(username = ? AND category = ?)";
				ps = con.prepareStatement(query_FetchCategoryCount);
				ps.setString(1, username);
				ps.setString(2, category);
				rs = ps.executeQuery();
				if (rs.next()) {
					category_count = rs.getInt("category_search_count");
				}
				category_count += 1;
				rs.close();
				ps.close();

				query_UpdateCategoryCount = "UPDATE search_count_details SET category_search_count = ?, "
						+ "recent_category_search_date=? WHERE " + "(username = ? AND category = ?)";
				ps = con.prepareStatement(query_UpdateCategoryCount);
				ps.setInt(1, category_count);
				ps.setString(2, today_string);
				ps.setString(3, username);
				ps.setString(4, category);
				ps.executeUpdate();
			} else if (category == "" && product != "") {
				query_FetchProductCount = "SELECT product_search_count FROM search_count_details "
						+ "WHERE (username = ? AND product = ?)";
				ps = con.prepareStatement(query_FetchProductCount);
				ps.setString(1, username);
				ps.setString(2, product.toLowerCase());
				rs = ps.executeQuery();
				if (rs.next()) {
					product_count = rs.getInt("product_search_count");
				}
				product_count += 1;
				rs.close();
				ps.close();

				query_UpdateProductCount = "UPDATE search_count_details SET product_search_count = ?, "
						+ "recent_product_search_date=? WHERE " + "(username = ? AND product = ?)";
				ps = con.prepareStatement(query_UpdateProductCount);
				ps.setInt(1, product_count);
				ps.setString(2, today_string);
				ps.setString(3, username);
				ps.setString(4, product.toLowerCase());
				ps.executeUpdate();
			} else if (category != "" && product != "") {
				query_FetchCategoryProductCount = "SELECT category_search_count, product_search_count FROM search_count_details "
						+ "WHERE (username = ? AND category = ? AND product = ?)";
				ps = con.prepareStatement(query_FetchCategoryProductCount);
				ps.setString(1, username);
				ps.setString(2, category);
				ps.setString(3, product.toLowerCase());
				rs = ps.executeQuery();
				if (rs.next()) {
					category_count = rs.getInt("category_search_count");
					product_count = rs.getInt("product_search_count");
				}
				category_count += 1;
				product_count += 1;
				rs.close();
				ps.close();

				query_UpdateCategoryProductCount = "UPDATE search_count_details SET category_search_count = ?, "
						+ "recent_category_search_date = ?, " + "product_search_count = ?, "
						+ "recent_product_search_date=? WHERE " + "(username = ? AND category = ? AND product = ?)";
				ps = con.prepareStatement(query_UpdateCategoryProductCount);
				ps.setInt(1, category_count);
				ps.setString(2, today_string);
				ps.setInt(3, product_count);
				ps.setString(4, today_string);
				ps.setString(5, username);
				ps.setString(6, category);
				ps.setString(7, product.toLowerCase());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	public Boolean checkProductExistence(String product) {
		Boolean flag = false;
		String query = "SELECT * FROM product_details WHERE product_name = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, product);
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public Boolean checkProductExistenceWithPin(String pin_code) {
		Boolean flag = false;
		String query = "SELECT * FROM product_details WHERE pin_code = ?";
		System.out.println("query " + query);
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, pin_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}

	public int modifyProductDetails(Product product) {
		int result = 0;
		String query = "UPDATE product_details SET product_name = ?, product_category = ?, product_unit_price = ?, "
				+ "product_description = ?, pin_code = ? WHERE product_id = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, product.getProduct_name());
			ps.setString(2, product.getProduct_category());
			ps.setBigDecimal(3, product.getProduct_unit_price());
			ps.setString(4, product.getProduct_description());
			ps.setString(5, product.getPin_code());
			ps.setString(6, product.getProduct_id());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}

	public int deleteProduct(Product product) {
		int result = 0;
		String query = "DELETE FROM product_details WHERE product_id = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, product.getProduct_id());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
}
