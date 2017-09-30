
import com.project.api.app.App;
import com.project.api.oraclesql.Table;
import com.project.api.oraclesql.TableColumn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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
        /*Connection conn = App.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from userstable");
        System.out.println(rs.next());
        while(rs.next()) {
            System.out.println(rs.getString("username"));
        }*/
        
        List<Table> tables = App.getTables();
        for(Table table : tables) {
            for(TableColumn col : table.getTableColumns()) {
                System.out.println(col.getColumnName());
                System.out.println(col.getDataType());
                System.out.println(col.getLength());
                System.out.println("=========================");
            }
        }
    }
}
