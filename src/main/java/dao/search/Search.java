package dao.search;

import dao.RetailService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Search extends RetailService {
    private final static String SQL = "./src/main/java/dao/sql/search/";

    public List<String[]> search(byte num, List<String> str) {
        List<String[]> list = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            var statement =
                    connection.prepareStatement(new String(readAllBytes(get(
                            SQL + (num == 2 ?
                                    "allTablesSearch-trigram.sql" :
                                    num == 3 ?
                                            "mandatoryEntry-fts.sql" :
                                            "nonEntry-fts.sql")))));
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
        } catch (SQLException | IOException e) {
            printError(e);
        }
        return list;
    }
}