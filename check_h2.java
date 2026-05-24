import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class check_h2 {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./data/security_data_db", "sa", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_nota");
        while(rs.next()) {
            System.out.println("ID: " + rs.getLong("id") + " Conteudo: " + rs.getString("conteudo"));
        }
        conn.close();
    }
}
