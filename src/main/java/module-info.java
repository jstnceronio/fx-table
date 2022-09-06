module com.table.table {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.table.table to javafx.fxml;
    exports com.table.table;
}