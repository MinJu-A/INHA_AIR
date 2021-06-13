package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//
// MySQL 연결 코드
//
public class databaseClass {
	private static Connection conn;
	private static Statement stmt;
	
	public static void connect(String dbURL, String dbID, String dbPassword) {
		try {
			// Database 연결
			Class.forName("com.mysql.cj.jdbc.Driver");	// 드라이버 로드
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);	// 연결
			System.out.println("데이터베이스 연결 성공!");
			
			// 쿼리 수행을 위한 Statement 객체 생성
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("에러");
			e.printStackTrace();
		}
	}
	
	public static void closeDB() {
		try {
			conn.close();
			System.out.println("데이터베이스 연결 해제");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	// SELECT
	//
	public static ResultSet select(String sql) {
		// 쿼리 수행
		ResultSet rs = null;
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//
	// INSERT
	//
	public static int insert(String sql) {
		// 쿼리 수행
		int rs = 0;
				
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//
	// UPDATE
	//
	public static int update(String sql) {
		// 쿼리 수행
		int rs = 0;
				
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//
	// DELETE
	//
	public static int delete(String sql) {
		// 쿼리 수행
		int rs = 0;
				
		try {
			rs = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	
	public static void main(String[] args) {
		String dbURL="jdbc:mysql://114.71.137.174:61083/inhaair?serverTimezone=UTC&useSSL=false";
		String dbID="inhaair";
		String dbPassword="1234";
		
		connect(dbURL, dbID, dbPassword);
		
	}
}
