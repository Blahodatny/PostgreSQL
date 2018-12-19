package database.search;

import database.operators.enums.ECustomerAttribute;
import database.operators.enums.EOrderAttribute;
import database.operators.enums.EProductAttribute;

import java.util.List;

public class LikeSearch extends database.RetailService {
    final private String[] TABLES = new String[]{
            "customers", "orders", "products"
    };

    public List<String> search(String string) {
        List<String> list = new java.util.ArrayList<>();
        try {
            connection.setAutoCommit(false);
            for (byte i = 0; i < TABLES.length; i++)
                for (var item :
                        i == 0 ? ECustomerAttribute.values()
                                : i == 1 ? EOrderAttribute.values()
                                : EProductAttribute.values()
                ) {
                    var name = item.name();
                    if (i == 1 && name.equals("Phone")) continue;
                    var statement = connection.prepareStatement(
                            "SELECT " + name + " FROM " + TABLES[i] + " WHERE " + name + " LIKE ?"
                    );
                    statement.setString(1, '%' + string + '%');
                    var res = statement.executeQuery();
                    if (res.next())
                        list.add(res.getString(name));
                    close(res, statement);
                }
        } catch (java.sql.SQLException e) {
            printError(e);
        }
        return list;
    }
}