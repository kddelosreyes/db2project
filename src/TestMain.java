
import com.project.api.app.App;
import com.project.api.oraclesql.Table;
import com.project.api.oraclesql.TableColumn;
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
        List<Table> tables = App.getTables();
        for(Table table : tables) {
            System.out.println("TABLE NAME: " + table.getTableName());
            for(TableColumn tableColumn : table.getTableColumns()) {
                System.out.println(tableColumn.getColumnName());
                System.out.println(tableColumn.getDataType());
                System.out.println(tableColumn.getLength());
                System.out.println(tableColumn.getPrecision());
                System.out.println(tableColumn.getScale());
                System.out.println(tableColumn.getIsNull());
            }
        }
    }
}
