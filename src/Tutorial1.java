import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tutorial1 {

	public static void main(String[] args) {
		Connection conn = DBUtil.open();
		String sql = "select candidate_id,skill_id from candidate_skills; ";
		try {
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			// cursor
			while(res.next()){
				int candidate_id = res.getInt(1);
				int skill_id = res.getInt(2);
				System.out.println(candidate_id+","+skill_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
	}

}
