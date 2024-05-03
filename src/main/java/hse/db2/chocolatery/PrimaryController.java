package hse.db2.chocolatery;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import main.java.hse.db2.chocolatery.SQLDatabaseConnection;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        try {
            SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection();
            List<Map<String, Object>> results = sqlDatabaseConnection
                    .executeQuery(
                            "SELECT TOP (1000) [ArtikelID],[Preis pro Kg],[Name] FROM [WKB4_DB2_Projekt].[dbo].[alkait02_Artikel]");
            for (Map<String, Object> row : results) {
                System.out.println(row.get("ArtikelID")
                        + " " + row.get("Preis pro Kg")
                        + " " + row.get("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        App.setRoot("secondary");
    }
}