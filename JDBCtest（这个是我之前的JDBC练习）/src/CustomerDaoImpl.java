import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao{

	public void add(Customer c) {
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
	public void update(Customer c) {
		String sql = "update tbl1 set name=?,email=? where id = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(3, c.getId());
			pstmt.setString(1,c.getName());
			pstmt.setString(2,c.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	public void delete(int id) {
		String sql = "delete from tbl1 where id = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
	}
	public Customer getCustomerById(int id) {
		String sql = "select id,name,email from tbl1 where id = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				Customer c = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
		return null;
	}
	public List<Customer> query() {
		String sql = "select id,name,email from tbl1";
		Connection conn = DBUtil.open();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Customer> list = new ArrayList<Customer>();
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				Customer c = new Customer(id,name,email);
				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn);
		}
		return null;
	}
}