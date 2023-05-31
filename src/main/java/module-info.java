module com.example.cybersecurity {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.cybersecurity to javafx.fxml;
    exports com.example.cybersecurity;
}