import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author mysqltutorial.org
 */
public class Tutorial4 {
 
    /**
     * Insert a new candidate
     * @param firstName
     * @param lastName
     * @param dob
     * @param email
     * @param phone
     * @return 
     */
    public static int insertCandidate(String firstName,String lastName,Date dob, 
                                       String email, String phone) {
        // for insert a new candidate
        ResultSet rs = null;
        int candidateId = 0;
        
        String sql = "INSERT INTO candidates(first_name,last_name,dob,phone,email) "
                   + "VALUES(?,?,?,?,?)";
        
        try (Connection conn = MySQLJDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
            
            // set parameters for statement
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, dob);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
 
            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);
 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return candidateId;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // insert a new candidate
         int id = insertCandidate("Bush", "Lily", Date.valueOf("1980-01-04"), 
                        "bush.l@yahoo.com", "(408) 898-6666");
         
         System.out.println(String.format("A new candidate with id %d has been inserted.",id));
    }
}