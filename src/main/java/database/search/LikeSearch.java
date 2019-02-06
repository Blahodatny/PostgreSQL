package database.search;

import database.RetailService;
import database.operators.enums.CustomerAttribute;
import database.operators.enums.OrderAttribute;
import database.operators.enums.ProductAttribute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeSearch extends RetailService {
    final private String[] TABLES = new String[]{
            "customers", "orders", "products"
    };

    public List<String> search(String string) {
        List<String> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            for (byte i = 0; i < TABLES.length; i++)
                for (var item :
                        i == 0 ? CustomerAttribute.values() :
                                i == 1 ? OrderAttribute.values() :
                                        ProductAttribute.values()
                ) {
                    var name = item.name();
                    if (i == 1 && name.equals("Phone"))
                        continue;
                    var statement = connection.prepareStatement(
                            "SELECT " + name + " FROM " + TABLES[i] + " WHERE " + name + " LIKE ?"
                    );
                    statement.setString(1, '%' + string + '%');
                    var res = statement.executeQuery();
                    if (res.next())
                        list.add(res.getString(name));
                    close(res, statement);
                }
        } catch (SQLException e) {
            printError(e);
        }
        return list;
    }
}