import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Test5 {

	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
	}
	
	static void test1(){
		Connection conn = DBUtil.open();
		try {
			CallableStatement cstmt = conn.prepareCall("{call all_customers()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				Customer cus = new Customer(id,name,email);
				System.out.println(cus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	static void test2(){
		Connection conn = DBUtil.open();
		try {
			CallableStatement cstmt = conn.prepareCall("{call insert_customer(?,?)}");
			cstmt.setString(1,"tom123");
			cstmt.setString(2,"tom123@gmail.com");
			int r = cstmt.executeUpdate();
			System.out.println(r);// 更新结果行数
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	static void test3(){
		Connection conn = DBUtil.open();
		try {
			CallableStatement cstmt = conn.prepareCall("{call getnamebyid(?,?)}");
			cstmt.setInt(1,5);
			cstmt.registerOutParameter(2,Types.CHAR);
			cstmt.execute();
			String name = cstmt.getString(2);
			cstmt.executeQuery();
			System.out.println(name);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
}
