package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PlayRecord;

public class RecordDAO {
	private final String JDBC_URL =
			"jdbc:postgresql://ec2-54-146-4-66.compute-1.amazonaws.com:5432/dk4hvqigdj5jh";
	private final String DB_USER ="fcvjvhrnggfmhc";
	private final String DB_PASS ="b65f8c3a102752e124985da827543198c7266fe75aab67694293039a8cdcf24a";

	public boolean insert(PlayRecord record) {
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql = "INSERT INTO recordData(name, score, date) VALUES(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, record.getPlayerName());
			pStmt.setInt(2, record.getScore());
			pStmt.setString(3, record.getDate());
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

	public List<PlayRecord> getRankingRecord(){
		List<PlayRecord> recordList = new ArrayList<>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql ="select * from recordData ORDER BY score DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				int score = rs.getInt("score");
				String date = rs.getString("date");

				if(score > 0) {
					PlayRecord record = new PlayRecord(name, score, date);
					recordList.add(record);
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

		return recordList;
	}

	public List<PlayRecord> getUserRecord(String name){
		List<PlayRecord> recordList = new ArrayList<>();
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql ="select * from recordData WHERE name = '" + name + "' ORDER BY number DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("name");
				int score = rs.getInt("score");
				String date = rs.getString("date");

				PlayRecord record = new PlayRecord(userName, score, date);
				recordList.add(record);
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

		return recordList;
	}


	public int getPlayCount(String name) {
		int count = 0;
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql ="SELECT COUNT(*) AS count FROM recordData WHERE name = '" + name + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
				while(rs.next()) {
					count = rs.getInt("count");
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

		return count;
	}

	public int getMaxScore(String name) {
		int max = 0;
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
			String sql ="SELECT MAX(score) AS max FROM recordData WHERE name = '" + name + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					max = rs.getInt("max");
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

		return max;
	}

}
