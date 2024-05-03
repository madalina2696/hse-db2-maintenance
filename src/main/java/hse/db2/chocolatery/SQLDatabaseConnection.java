package main.java.hse.db2.chocolatery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLDatabaseConnection {

    private static final String serverName = "itnt0005";
    private static final String port = "1433";
    private static final String databaseName = "WKB4_DB2_Projekt";
    private static final String username = "wkb4";
    private static final String password = "wkb4";
    private static String connectionUrl;

    public SQLDatabaseConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connectionUrl = "jdbc:sqlserver://"
                + serverName + ":"
                + port + ";"
                + "database=" + databaseName + ";"
                + "user=" + username + ";"
                + "password=" + password + ";"
                /* + "encrypt=true;" */
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";
    }

    public List<Map<String, Object>> executeQuery(String query) {
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}