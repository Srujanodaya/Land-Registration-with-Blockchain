package com.megainfo.queries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.megainfo.util.db.DBConnection;


public class SQLCount {
	Connection con;
	Statement smt;
	ResultSet rs;

	public int invokeCount(String query) throws SQLException {
		int count = 0;
		try {
			con = DBConnection.getDBConnection();
			smt = con.createStatement();
//			rs = smt.executeQuery(query);
//			rs.last();
//			count = rs.getRow();
//			System.out.println(count);

			int rowCount = 0;
			rs = smt.executeQuery(query);
			while (rs.next()) {
				rowCount++;
				System.out.println(rs.getString(1));
			}
			System.out.println("rowCount "+rowCount);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			smt.close();
			con.close();
		}
		return count;
	}

	public static void main(String[] args) throws SQLException {
		SQLCount count = new SQLCount();
		count.invokeCount("SELECT * FROM PRODUCT_REVIEWS WHERE REVIEWER = '100'");
	}
}
