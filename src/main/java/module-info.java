module org.example.IceCreamShop {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.IceCreamShop to javafx.fxml;
    exports org.example.IceCreamShop;
}