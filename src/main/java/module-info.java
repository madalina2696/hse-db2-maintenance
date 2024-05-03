module hse.db2.chocolatery {
    requires javafx.controls;
    requires javafx.fxml;

    opens hse.db2.chocolatery to javafx.fxml;
    exports hse.db2.chocolatery;
}
