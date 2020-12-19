package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {

	private final String JDBC_URL =
			"jdbc:postgresql://ec2-54-146-4-66.compute-1.amazonaws.com:5432/dk4hvqigdj5jh";
	private final String DB_USER ="fcvjvhrnggfmhc";
	private final String DB_PASS ="b65f8c3a102752e124985da827543198c7266fe75aab67694293039a8cdcf24a";


	public User select(String name) {
		User user = null;
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql = "SELECT * FROM userData WHERE name='" + name + "';";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String Username = rs.getString("name");
				String Userpass = rs.getString("pass");
				user = new User(name, Userpass);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			  try{
				    if (conn != null){
				      conn.close();
				    }
				  }catch (SQLException e){
					  e.printStackTrace();
				  }
		}
		return user;

	}

	public boolean userAdd(User user) {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql = "INSERT INTO userdata(name, pass) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());
			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			  try{
				    if (conn != null){
				      conn.close();
				    }
				  }catch (SQLException e){
					  e.printStackTrace();
				  }
		}
		return true;
	}

	public boolean setMaxScore(String name) {
		Connection conn = null;
		int max = 0;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql ="SELECT MAX(score) AS max FROM recordData WHERE name = '" + name + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					max = rs.getInt("max");
				}
				if(max != 0) {
					String sql2 = "UPDATE userData SET maxscore=" + max + " WHERE name='" + name + "';";
					PreparedStatement pStmt2 = conn.prepareStatement(sql2);
					int result = pStmt2.executeUpdate();

					if(result != 1) {
						return false;
					}
				}

			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			  try{
				    if (conn != null){
				      conn.close();
				    }
				  }catch (SQLException e){
					  e.printStackTrace();
				  }
		}
		return true;
	}

	public List<User> getUserRanking(){
		List<User> userList = new ArrayList<>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql ="select * from userData WHERE maxscore IS NOT NULL ORDER BY maxscore DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				int maxScore = rs.getInt("maxscore");

				if(maxScore > 0) {
					User user = new User(name, maxScore);
					userList.add(user);
				}else {
					break;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			  try{
				    if (conn != null){
				      conn.close();
				    }
				  }catch (SQLException e){
					  e.printStackTrace();
				  }
		}

		return userList;
	}



}
