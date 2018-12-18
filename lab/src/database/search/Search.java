package database.search;

import java.util.List;

public class Search extends database.RetailService {
    final private String SQL = PATH + "/src/database/sql/search/";

    public List<String[]> search(byte num, List<String> str) {
        List<String[]> list = new java.util.ArrayList<>();
        try {
            connection.setAutoCommit(false);
            var statement = connection.prepareStatement(
                    new String(
                            java.nio.file.Files.readAllBytes(
                                    java.nio.file.Paths.get(
                                            SQL + (num == 2 ? "allTablesSearch-trigram.sql"
                                                    : num == 3 ? "mandatoryEntry-fts.sql"
                                                    : "nonEntry-fts.sql")
                                    )
                            )
                    )
            );
            for (byte i = 1; i < (num == 2 ? 9 : 2); i++)
                statement.setString(i, String.join(" | ", str));
            var res = statement.executeQuery();
            while (res.next()) {
                var item = new String[num == 2 ? 11 : 3];
                for (byte i = 0; i < item.length; i++)
                    item[i] = res.getString(i + 1);
                list.add(item);
            }
            close(res, statement);
        } catch (java.sql.SQLException | java.io.IOException e) {
            printError(e);
        }
        return list;
    }
}