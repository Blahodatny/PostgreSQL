package database.search;

import database.operators.enums.ECustomerAttribute;
import database.operators.enums.EOrderAttribute;
import database.operators.enums.EProductAttribute;

import java.util.List;

public class Trigram extends database.RetailService {
    final private String[] tables = new String[]{
            "CUSTOMERS", "ORDERS", "PRODUCTS"
    };

    public List<String> trigramSearch(String string) {
        List<String> list = new java.util.ArrayList<>();
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
                    System.out.println(statement.toString());
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