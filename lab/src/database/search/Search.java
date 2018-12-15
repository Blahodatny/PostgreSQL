package database.search;

import database.operators.enums.ECustomerAttribute;
import database.operators.enums.EOrderAttribute;
import database.operators.enums.EProductAttribute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search extends database.RetailService {
    final private String sql = path + "/src/database/sql/search/allTablesSearch-like.sql";
    final private String[] tables = new String[]{
            "CUSTOMERS", "ORDERS", "PRODUCTS"
    };

    public List<String> trigramSearch(String string) {
        List<String> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            for (byte i = 0; i < tables.length; i++)
                for (var item :
                        i == 0 ? ECustomerAttribute.values()
                                : i == 1 ? EOrderAttribute.values()
                                : EProductAttribute.values()
                ) {
                    var name = item.name();
                    if (i == 1 && name.equals("Phone")) continue;
                    var statement = connection.prepareStatement(
                            "SELECT " + name + " FROM " + tables[i] + " WHERE " + name + " % ?"
                    );
                    statement.setString(1, string);
                    var res = statement.executeQuery();
                    if (res.next())
                        list.add(res.getString(item.name()));
                    close(res, statement);
                }
        } catch (SQLException e) {
            printError(e);
        }
        return list;
    }

    public List<String[]> likeSearch(String string) {
        List<String[]> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    new String(
                            java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(sql))
                    )
            );
            statement.setString(1, '%' + string + '%');
            var res = statement.executeQuery();
            while (res.next()) {
                var item = new String[11];
                for (byte i = 0; i < item.length; i++)
                    item[i] = res.getString(i + 1);
                list.add(item);
            }
            close(res, statement);
        } catch (SQLException | java.io.IOException e) {
            printError(e);
        }
        return list;
    }
}