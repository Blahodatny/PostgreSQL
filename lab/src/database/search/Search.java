package database.search;

import database.RetailService;
import database.operators.enums.ECustomerAttribute;
import database.operators.enums.EOrderAttribute;
import database.operators.enums.EProductAttribute;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search extends RetailService {
    final private String sql = path + "/src/database/sql/";
    final private String like = sql + "search/allTablesSearch-like.sql";
    final private String dir = sql + "search/mandatoryEntry-fts.sql";
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
                                : EProductAttribute.values()) {
                    var name = item.name();
                    if (i == 1 && name.equals("Phone")) continue;
                    var statement = connection.prepareStatement(
                            "SELECT " + name + " FROM " + tables[i] + " WHERE " + name + " % ?"
                    );
                    statement.setString(1, string);
                    var res = statement.executeQuery();
                    if (res.next())
                        list.add(res.getString(item.name()));
                    res.close();
                    statement.close();
                    connection.commit();
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
                    new String(Files.readAllBytes(Paths.get(like)))
            );
            statement.setString(1, '%' + string + '%');
            var res = statement.executeQuery();
            while (res.next()) {
                var item = new String[11];
                for (byte i = 0; i < item.length; i++)
                    item[i] = res.getString(i + 1);
                list.add(item);
            }
            res.close();
            statement.close();
            connection.commit();
        } catch (SQLException | IOException e) {
            printError(e);
        }
        return list;
    }

    public void mandatoryEntry(String str) {
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    new String(Files.readAllBytes(Paths.get(dir)))
            );
            statement.setString(1, str);
            var res = statement.executeQuery();
            System.out.println(res.next());
            System.out.println(res.getString(1));
            res.close();
            statement.close();
            connection.commit();
        } catch (SQLException | IOException e) {
            printError(e);
        }
    }
}