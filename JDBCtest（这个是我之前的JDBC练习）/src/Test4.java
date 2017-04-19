import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test4 {

	public static void main(String[] args) {
//		insert("tom1","tom1@gmail.com");
//		Customer c = new Customer(3,"seruti","shinra@gmail.com");
//		insert(c);
		query(1);
	}

	static void insert(String name,String email){
		String sql = "insert into tbl1(name,email)values(?,?)";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setString(2,email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	static void insert(Customer c){
		String sql = "insert into tbl1(name,email)values(?,?)";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,c.getName());
			pstmt.setString(2,c.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	static void update(Customer c){
		String sql = "update tbl1 set name = ? where id = ?";
		Connection conn = DBUtil.open();
		try {
			// 更新某id 的name
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,c.getName());
			pstmt.setInt(2,c.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	// 删除ID为id的数据
	static void del(int id){
		String sql = "delete from tbl1 where id =?";
		Connection conn = DBUtil.open();
		try {
			// 更新某id 的name
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	// 查询ID为id的第一个人
	static Customer query(int id){
		String sql = "select id,name,email from tbl1 where id =?";
		Connection conn = DBUtil.open();
		try {
			// 更新某id 的name
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			Customer c=null;
			if(rs.next()){
				c = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3));
			}
			System.out.println(c);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
		return null;
	}
}
