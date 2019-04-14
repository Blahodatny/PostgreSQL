package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

import static java.sql.DriverManager.getConnection;

public class RetailService {
    protected static Connection connection;

    protected RetailService() { }

    public RetailService(String db_name, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = getConnection(
                    "jdbc:postgresql://localhost:5432/" + db_name,
                    user,
                    password
            );
        } catch (Exception e) {
            printError(e);
        }
        System.out.println("Opened database successfully!!!");
    }

    public void operate(Supplier<PreparedStatement> supplier) {
        try {
            connection.setAutoCommit(false);
            var statement = supplier.get();
            statement.executeUpdate();
            statement.close();
            connection.commit();
        } catch (Exception e) {
            printError(e);
        }
    }

    protected void printError(Exception e) {
        e.printStackTrace();
        System.err.println(
                "Error in " + this.getClass().getSimpleName() + ": " +
                        e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }

    protected void close(ResultSet res, PreparedStatement st)
            throws SQLException {
        res.close();
        st.close();
        connection.commit();
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            printError(e);
        }
    }
}