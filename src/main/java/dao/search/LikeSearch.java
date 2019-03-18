package dao.search;

import dao.RetailService;
import dao.attributes.Customer;
import dao.attributes.Order;
import dao.attributes.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeSearch extends RetailService {
    final private String[] TABLES = new String[]{"CUSTOMERS", "ORDERS", "PRODUCTS"};

    public List<String> search(String string) {
        List<String> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            for (byte i = 0; i < TABLES.length; i++)
                for (var item : i == 0 ? Customer.values() :
                        i == 1 ? Order.values() : Product.values()) {
                    var name = item.name();
                    if (i == 1 && name.equals("Phone"))
                        continue;
                    var statement = connection.prepareStatement(
                            "SELECT " + name + " FROM " + TABLES[i] +
                                    " WHERE " + name + " LIKE ?"
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