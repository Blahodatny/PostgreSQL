package database.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search extends database.RetailService {
    final private String sql = path + "/src/database/sql/search/";

    public List<String[]> search(byte num, String string) {
        List<String[]> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    new String(
                            java.nio.file.Files.readAllBytes(
                                    java.nio.file.Paths.get(
                                            sql + (num == 2 ? "allTablesSearch-like.sql"
                                                    : num == 3 ? "mandatoryEntry-fts.sql"
                                                    : "nonEntry-fts.sql")
                                    )
                            )
                    )
            );
            for (byte i = 1; i < (num == 2 ? 9 : 2); i++)
                statement.setString(i, string);
            var res = statement.executeQuery();
            while (res.next()) {
                var item = new String[num == 2 ? 11 : 3];
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