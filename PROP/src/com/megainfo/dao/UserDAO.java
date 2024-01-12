package com.megainfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.megainfo.model.User;
import com.megainfo.util.AESDecryption;
import com.megainfo.util.AESEncryption;
import com.megainfo.util.AESPasswordBased;
import com.megainfo.util.AESUtil;
import com.megainfo.util.DBUtil;
import com.megainfo.util.DateFormatter;
import com.megainfo.util.db.DBConnection;

public class UserDAO {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Date today = new Date();

	public int registerUser(User user) {
		int result = 0;
		String query = "INSERT INTO user_details VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, AESPasswordBased.encrypt(user.getPassword(), AESUtil.key));
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getDob());
			ps.setString(6, user.getGender());
			String encryptedPhone = AESEncryption.encrypt(user.getPhone(), AESUtil.key, AESUtil.salt);
			ps.setString(7, encryptedPhone);
			String encryptedEmail = AESEncryption.encrypt(user.getEmail(), AESUtil.key, AESUtil.salt);
			ps.setString(8, encryptedEmail);
			ps.setString(9, user.getAddress());
			ps.setString(10, user.getCity());
			ps.setString(11, user.getState());
			ps.setString(12, user.getCountry());
			ps.setString(13, user.getZipcode());
			ps.setString(14, user.getLast_login());
			ps.setString(15, user.getUser_type());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}

		return result;
	}

	public Boolean isUsernameExisting(User user) {
		Boolean flag = false;
		String query = "SELECT * FROM user_details WHERE username = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
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

	public Boolean isValidUserLogin(User user) {
		Boolean flag = false;
		String query = "SELECT * FROM user_details WHERE username = ? AND password = ? AND user_type = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, AESPasswordBased.encrypt(user.getPassword(), AESUtil.key));
			// ps.setString(2, user.getPassword());
			ps.setString(3, user.getUser_type());
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

	public void updateLastLogin() {
		DateFormatter df = new DateFormatter();
		String today_string = df.todayDateWithTime(today);
		String query = "UPDATE user_details SET last_login = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, today_string);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
	}

	public String loadLastLogin(String username) {
		String last_login = null;
		String query = "SELECT last_login FROM user_details WHERE username = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				last_login = rs.getString("last_login");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return last_login;
	}

	public User loadUserDetails(User user) {
		String query = "SELECT * FROM user_details WHERE username = ?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUsername(rs.getString("username"));
				user.setAddress(rs.getString("address"));
				user.setCity(rs.getString("city"));
				user.setCountry(rs.getString("country"));
				user.setDob(rs.getString("dob"));
				user.setEmail(AESDecryption.decrypt(rs.getString("email"), AESUtil.key, AESUtil.salt));
				user.setFirstname(rs.getString("firstname"));
				user.setGender(rs.getString("gender"));
				user.setLastname(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
				String decryptedPhone = AESDecryption.decrypt(rs.getString("phone"), AESUtil.key, AESUtil.salt);
				user.setPhone(decryptedPhone);
				user.setState(rs.getString("state"));
				user.setZipcode(rs.getString("zipcode"));
				user.setUser_type(rs.getString("user_type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(ps);
			DBUtil.close(con);
		}

		return user;
	}

	public int updateUserProfile(User user) {
		int result = 0;
		String query = "UPDATE user_details SET firstname=?, lastname=?, dob=?, phone=?, email=?, address=?, city = ?, "
				+ "state=?, country=?, zipcode=?, user_type=?";
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getDob());
			ps.setString(4, user.getPhone());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getAddress());
			ps.setString(7, user.getCity());
			ps.setString(8, user.getState());
			ps.setString(9, user.getCountry());
			ps.setString(10, user.getZipcode());
			ps.setString(11, user.getUser_type());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps);
			DBUtil.close(con);
		}
		return result;
	}
}
