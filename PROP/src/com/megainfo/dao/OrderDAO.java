package com.megainfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.megainfo.model.Order;
import com.megainfo.util.DBUtil;
import com.megainfo.util.DateFormatter;
import com.megainfo.util.db.DBConnection;

public class OrderDAO {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	int result = 0;
	Date today = new Date();
	DateFormatter df = new DateFormatter();
	String today_string = df.todayDateWithTime(today);
	public int orderProduct(Order order){
		String queryUpdateCart = "UPDATE cart_details SET is_product_ordered = ? WHERE (username = ? AND product_id = ?)";
		String query = "INSERT INTO order_details(ordered_product_id, ordered_product_units, "
				+ "total_order_amount, ordered_on, ordered_by, order_status, "
				+ "order_status_updated_on, is_order_delivered, is_order_cancelled) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		try{
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setString(1, order.getOrdered_product_id());
			ps.setInt(2, order.getOrdered_product_units());
			ps.setBigDecimal(3, order.getTotal_order_amount());
			ps.setString(4, order.getOrdered_on());
			ps.setString(5, order.getOrdered_by());
			ps.setString(6, order.getOrder_status());
			ps.setString(7, order.getOrdered_on());
			ps.setBoolean(8, order.getIs_order_delivered());
			ps.setBoolean(9, order.getIs_order_cancelled());
			
			result = ps.executeUpdate();
			
			PreparedStatement ps_update = con.prepareStatement(queryUpdateCart);
			ps_update.setBoolean(1, true);
			ps_update.setString(2, order.getOrdered_by());
			ps_update.setString(3, order.getOrdered_product_id());
			ps_update.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}
	public List<Order> loadAllOrders(Order order){
		List<Order> list = new ArrayList<Order>();
		String query = "SELECT * FROM order_details WHERE (ordered_by = ? AND is_order_delivered = ? AND is_order_cancelled = ?) "
				+ "ORDER BY ordered_on DESC";
		try{
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, order.getOrdered_by());
			ps.setBoolean(2, order.getIs_order_delivered());
			ps.setBoolean(3, order.getIs_order_cancelled());
			rs = ps.executeQuery();
			while(rs.next()){
				order = new Order();
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrdered_product_id(rs.getString("ordered_product_id"));
				order.setOrdered_product_units(rs.getInt("ordered_product_units"));
				order.setTotal_order_amount(rs.getBigDecimal("total_order_amount"));
				order.setOrdered_on(rs.getString("ordered_on"));
				order.setOrder_status(rs.getString("order_status"));
				
				
				list.add(order);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		System.out.println("List Size : " +list.size());
		return list;
	}
	public void updateOrderCount(String username, String product_id){
		String queryFetchOrderCount = "SELECT order_count FROM count_details WHERE (product_id=? AND username = ?)";
		String queryUpdateOrderCount = "UPDATE count_details SET order_count=?, order_date=? WHERE (product_id=? AND username = ?)";
		Integer order_count = null;
		try{
			con = DBConnection.getDBConnection();
			con.setAutoCommit(false);
			PreparedStatement ps_FetchOrderCount = con.prepareStatement(queryFetchOrderCount);
			ps_FetchOrderCount.setString(1, product_id);
			ps_FetchOrderCount.setString(2, username);
			rs = ps_FetchOrderCount.executeQuery();
			if(rs.next()){
				order_count = rs.getInt("order_count");
			}
			if(order_count == 0){
				order_count = 1;
			}else
				order_count += 1;
			rs.close();
			PreparedStatement ps_UpdateOrderCount = con.prepareStatement(queryUpdateOrderCount);
			ps_UpdateOrderCount.setInt(1, order_count);
			ps_UpdateOrderCount.setString(2, today_string);
			ps_UpdateOrderCount.setString(3, product_id);
			ps_UpdateOrderCount.setString(4, username);
			ps_UpdateOrderCount.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBUtil.close(rs);
			DBUtil.close(con);
		}
	}
}
