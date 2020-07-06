package util;

import java.sql.Connection;

public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://:3306/takeout";
	private static final String dbUser="root";
	private static final String dbPwd="abc";
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		Connection conn= java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
		System.out.println(conn);
		return conn;
	}
}
