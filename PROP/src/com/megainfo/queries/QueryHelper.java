package com.megainfo.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.megainfo.util.db.DBConnection;


/**
 * @author rkaruti
 * 
 */
public class QueryHelper {

	/**
	 * @param args
	 */
	ResultSet rs;
	Connection con;
	PreparedStatement ps;
	Statement smt;

	public ArrayList<String> invokeRecords(String query) {
		ArrayList<String> al = new ArrayList<String>();
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			System.out.println("Query executed " + query);
			rs = ps.executeQuery();
			System.out.println("Result Set : " + rs);
			int numcols = rs.getMetaData().getColumnCount();
			List<List<String>> result = new ArrayList<>();
			while (rs.next()) {
				for (int i = 1; i <= numcols; i++) { // don't skip the last
					al.add(rs.getString(i));
				}
				result.add(al); // add it to the result
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}

	public int invokeCount(String query) throws SQLException {
		int count = 0;
		try {
			con = DBConnection.getDBConnection();
			smt = con.createStatement();
			int rowCount = 0;
			rs = smt.executeQuery(query);
			while (rs.next()) {
				rowCount++;
			}
			System.out.println("rowCount " + rowCount);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			smt.close();
			con.close();
		}
		return count;
	}

	public boolean findDuplicate(String query) throws SQLException {
		boolean duplicate = false;
		try {
			con = DBConnection.getDBConnection();
			smt = con.createStatement();
			int rowCount = 0;
			rs = smt.executeQuery(query);
			while (rs.next()) {
				rowCount++;
			}
			System.out.println("rowCount " + rowCount);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			smt.close();
			con.close();
		}
		return duplicate;
	}

	public int insertRecords(String query, ArrayList<String> list) {
		System.out.println("Query " + query);
		int x = 0;
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			for (int i = 0; i < list.size(); i++) {
				ps.setString(i + 1, list.get(i));
			}
			x = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public ArrayList invokeEditRecords(String query) {
		ArrayList al = new ArrayList();
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			System.out.println("Query executed ");
			rs = ps.executeQuery();
			System.out.println("Result Set " + rs);

			int numcols = rs.getMetaData().getColumnCount();
			List<List<String>> result = new ArrayList<>();
			while (rs.next()) {
				for (int i = 1; i <= numcols; i++) { // don't skip the last
					al.add(rs.getString(i));
				}
				result.add(al); // add it to the result
			}

			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}

	public int updateRecords(String query) {
		int i = 0;
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			i = ps.executeUpdate(query);
			System.out.println("Values updated! " + i);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public void deleteInformation(String query) {
		try {
			con = DBConnection.getDBConnection();
			int status = 0;
			ps = con.prepareStatement(query);
			status = ps.executeUpdate();
			System.out.println("Value Deleted Successfully! " + status);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//public static void main(String ar[]) throws SQLException {
	//	QueryHelper rf = new QueryHelper();
		// rf.invokeRecords();
		// rf.insertRecords();
		// rf.updateRecords();
		// rf.deleteInformation();
		//rf.invokeCount("SELECT COUNT(REVIEWER) FROM PRODUCT_REVIEWS");
	//}
}
