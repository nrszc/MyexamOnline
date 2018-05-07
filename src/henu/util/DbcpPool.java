package henu.util;
import java.sql.*;

public class DbcpPool {
	
	private static final String URL = "jdbc:mysql://localhost:3306/myexamonline";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	protected static Statement s=null;
	protected static ResultSet rs = null;
	protected static Connection conn = null;
	
	public static synchronized Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static int executeUpdate(String sql)
	{
		int result = 0;
		try {
			s = getConnection().createStatement();
			result = s.executeUpdate(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	public static ResultSet executeQuery(String sql)
	{
		
		try {
			s = getConnection().createStatement();
			rs = s.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}
	
	public static PreparedStatement executePreparedStatement(String sql)
	{
		PreparedStatement ps = null;
		try
		{
			ps = getConnection().prepareStatement(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ps;
	}
	
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void close()
	{
		try
		{
			if(rs!=null)
				rs.close();
			if(s!= null)
				s.close();
			if(conn!=null)
				conn.close();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		/*String username = "";
		try {
		String sqlSearch = "SELECT * FROM tb_users";
		ResultSet rs = null;
		DbUtil a = new DbUtil();
		rs = a.executeQuery(sqlSearch);
		
		
			username = rs.getString("fd_username");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(username);
		
		/*String sql = "INSERT INTO tb_users(fd_username,fd_password,fd_usertype,fd_gender,fd_email," +
				"fd_birthdate, fd_introduction,fd_hobby) VALUES (?,?,?,?,?,?,?,?)";
		
		
		PreparedStatement ps = DbUtil.executePreparedStatement(sql);
		try {
			ps.setString(1, "username");
			ps.setString(2, "password");
			ps.setString(3, "1");
			ps.setString(4, "gender");
			ps.setString(5, "email");
			ps.setString(6, "birthdate");
			ps.setString(7, "introduction");
			ps.setString(8, "hobby");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		int result = 0;
		try {
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
