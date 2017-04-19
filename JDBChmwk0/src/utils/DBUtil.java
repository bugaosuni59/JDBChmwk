package utils;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url;
	private static String username;
	private static String password;
	static{
		Properties prop = new Properties();
		Reader in;
		try {
			in = new FileReader("config.properties");
			prop.load(in);
		} catch (IOException e) {e.printStackTrace();}
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
	}
	// 打开数据库，返回一个数据库连接
	public static Connection open(){
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException|ClassNotFoundException  e) {
			e.printStackTrace();
		}
		return null;
	}
	// 关闭数据库
	public static void close(Connection conn){
		if(conn!=null){
			try {conn.close();} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}
}
