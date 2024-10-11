package org.example.IceCreamShop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * An Ice Cream Shop Application that simulates add ice cream to an order and then
 * checking out, which calculates the total price with sales tax.
 * Author: Mackenzie Whitney, Sept. 2024
 */
public class IceCreamShopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IceCreamShopApplication.class.getResource("shop-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ice Cream Shop");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}