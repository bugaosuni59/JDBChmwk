import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Test3 {
	public static void main(String[] args) {
		query2();
	}

	static void query() {
		Connection conn = DBUtil.open();
		String sql = "select id,name,email from tbl1";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString("email");
				System.out.println(id + "," + name + "," + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	static List<Customer> query2() {
		Connection conn = DBUtil.open();
		String sql = "select id,name,email from tbl1";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Customer> list = new ArrayList<Customer>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString("email");
				Customer customer = new Customer(id,name,email);
				list.add(customer);
			}
			System.out.println(list);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
}
