package com.megainfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megainfo.model.Cart;
import com.megainfo.util.DBUtil;
import com.megainfo.util.DateFormatter;
import com.megainfo.util.db.DBConnection;

public class CartDAO {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Date today = new Date();
	DateFormatter df = new DateFormatter();
	String today_string = df.todayDateWithTime(today);
	public int addToCart(Cart cart){
		int result = 0;
		String query = "INSERT INTO cart_details(username, product_id, added_on, is_product_ordered) "
				+ "VALUES(?,?,?,?)";
		try{
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, cart.getUsername());
			ps.setString(2, cart.getProduct_id());
			DateFormatter df = new DateFormatter();
			String today_string = df.todayDateWithTime(today);
			ps.setString(3, today_string);
			ps.setString(4, "0");
			result = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}
	public List<String> loadProductID(String username){
		List<String> list = new ArrayList<String>();
		String query = "SELECT product_id FROM cart_details WHERE (username = ? AND is_product_ordered = ?) ORDER BY added_on DESC";
		try{
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, "0");
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("product_id"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return list;
	}
	public Boolean checkExistingProductInCart(Cart cart){
		Boolean flag = false;
		String query = "SELECT * FROM cart_details WHERE (product_id = ? AND is_product_ordered = ? AND username = ?)";
		try{
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, cart.getProduct_id());
			ps.setString(2, "0");//cart.getIs_product_ordered());
			ps.setString(3, cart.getUsername());
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return flag;
	}
	
	public int removeProductFromCart(Cart cart){
		int result = 0;
		String query = "DELETE FROM cart_details WHERE (product_id = ? AND username = ?)";
		try{
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, cart.getProduct_id());
			ps.setString(2, cart.getUsername());
			result = ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}
	
	public void updateAddToCartViews(String username, String product_id){
		String queryFetchViewCount = "SELECT add_to_cart_count FROM count_details WHERE (product_id=? AND username = ?)";
		String queryUpdateViewCount = "UPDATE count_details SET add_to_cart_count=?, add_to_cart_date=? WHERE (product_id=? AND username = ?)";
		Integer add_to_cart_count = null;
		try{
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			PreparedStatement ps_FetchViewCount = con.prepareStatement(queryFetchViewCount);
			ps_FetchViewCount.setString(1, product_id);
			ps_FetchViewCount.setString(2, username);
			rs = ps_FetchViewCount.executeQuery();
			if(rs.next()){
				add_to_cart_count = rs.getInt("add_to_cart_count");
			}
			if(add_to_cart_count == 0){
				add_to_cart_count = 1;
			}else
				add_to_cart_count += 1;
			rs.close();
			PreparedStatement ps_UpdateViewCount = con.prepareStatement(queryUpdateViewCount);
			ps_UpdateViewCount.setInt(1, add_to_cart_count);
			ps_UpdateViewCount.setString(2, today_string);
			ps_UpdateViewCount.setString(3, product_id);
			ps_UpdateViewCount.setString(4, username);
			ps_UpdateViewCount.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBUtil.close(rs);
			DBUtil.close(con);
		}
	}
}
