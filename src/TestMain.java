
import com.project.api.app.App;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kim Howel delos Reyes
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
        Connection conn = App.getConnection();
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        Statement st = conn.createStatement();
        ResultSet in;
        while (rs.next()) {
            if(rs.getString(3).equalsIgnoreCase("PERSON")) {
                in = st.executeQuery("SELECT * FROM PERSON");
                ResultSetMetaData rsmd = in.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                for(int i = 1; i <= numberOfColumns; i++) {
                    System.out.println(rsmd.getColumnName(i));
                    System.out.println(rsmd.getColumnTypeName(i));
                    System.out.println(rsmd.getColumnDisplaySize(i));
                    System.out.println(rsmd.isNullable(i));
                    System.out.println("==============");
                }
            }
        }
    }
}
