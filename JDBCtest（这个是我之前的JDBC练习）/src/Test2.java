import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
	public static void main(String[] args) {
//		createTable();
//		insert();
//		update();
//		delete();
		query();
		query2();
	}
	static void createTable() {
		Connection conn = DBUtil.open();
		String sql = "create table UserTbl(id int primary key auto_increment,name varchar(20))";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	static void insert() {
		Connection conn = DBUtil.open();
		String sql = "insert into UserTbl(name)values('izaya')";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	static void update() {
		Connection conn = DBUtil.open();
		String sql = "update UserTbl set name='shizuo' where id<3";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	static void delete() {
		Connection conn = DBUtil.open();
		String sql = "delete from UserTbl where id > 3";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	static void query() {
		Connection conn = DBUtil.open();
		String sql = "select id,name from UserTbl";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println(id+","+name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	static List<User> query2() {
		Connection conn = DBUtil.open();
		String sql = "select id,name from UserTbl";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<User> list = new ArrayList<User>();
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
//				System.out.println(id+","+name);
				User u = new User(id,name);
				list.add(u);
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
