import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {
	public static void main(String[] args) {
		Connection conn = DBUtil.open();
		String sql = "select id,name,email from Tbl1 ";
		try {
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			// cursor
			while(res.next()){
				int id = res.getInt(1);
				String name = res.getString(2);
				String email = res.getString(3);
				System.out.println(id+","+name+","+email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
	}
}
