import java.sql.*;

public class SelectQuery {
    public static ResultSet selectStatement(String sql){
//        ResultSet rs1;
        String url = "jdbc:sqlite:bankAtm.db";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
