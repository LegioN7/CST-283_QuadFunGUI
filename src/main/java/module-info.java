module org.example.quadfungui {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.quadfungui to javafx.fxml;
    exports org.example.quadfungui;
}