package org.example.IceCreamShop;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import models.*;

import java.util.ArrayList;

/**
 * The controller class of the application whose responsibility is the display layer of the application
 */
public class IceCreamShopController {
    @FXML
    private Button btnAddToOrder;
    @FXML
    private Button btnResetSelection;
    @FXML
    private Button btnCheckout;
    @FXML
    private Button btnNewOrder;
    @FXML
    private Button btnExit;
    @FXML
    private VBox fxSizePane;
    @FXML
    private VBox fxFlavorPane;
    @FXML
    private VBox fxExtraPane;
    @FXML
    private ListView<String> listViewOrder;
    @FXML
    private TextArea taCheckoutInfo;

    private ToggleGroup sizeToggleGroup;  // Group for radio buttons
    private Order order;

    @FXML
    public void initialize() {
        order = new Order();
        addSizeRadioButtons();
        resetSizeSelection();
        addExtraCheckBoxes();
        listViewOrder.getStyleClass().add("custom-list-view"); // add style class to be picked up by styles.css

        // set on click events for each of the buttons in the application
        btnAddToOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Size selectedSize = (Size) sizeToggleGroup.getSelectedToggle().getUserData();
                ArrayList<Flavor> flavors = new ArrayList<>();
                ArrayList<Extra> extras = new ArrayList<>();

                for(Node node : fxFlavorPane.getChildren()){
                    if(node instanceof ChoiceBox<?> choiceBox){
                        Flavor flavor = (Flavor) choiceBox.getSelectionModel().getSelectedItem();
                        if(flavor != null) {
                            flavors.add(flavor);
                        }
                    }

                }
                for(Node node : fxExtraPane.getChildren()){
                    if(node instanceof CheckBox){
                        if(((CheckBox) node).isSelected()) {
                            Extra extra = (Extra) node.getUserData();
                            if (extra != null) {
                                extras.add(extra);
                            }
                        }
                    }
                }
                IceCream addedIceCream = new IceCream(selectedSize, flavors, extras);
                order.addIceCreamToOrder(addedIceCream);

                listViewOrder.getItems().clear();
                Order.populateListViewWithIceCreamCountMap(listViewOrder);
            }
        });
        btnResetSelection.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetSizeSelection();
                resetExtraSelections();
            }
        });
        btnCheckout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                taCheckoutInfo.setText(order.toString());
            }
        });
        btnNewOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                order = new Order();
                resetSizeSelection();
                resetExtraSelections();
                listViewOrder.getItems().clear();
                taCheckoutInfo.clear();
            }
        });
        btnExit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
    } // end initialize

    // method to reset Size selection and includes resetting the flavor selection ChoiceBoxes
    private void resetSizeSelection() {
        // set first Radio Button in Sizes toggle group
        Toggle firstSizeToggle = sizeToggleGroup.getToggles().getFirst();
        firstSizeToggle.setSelected(true);
        showFlavorSelections(firstSizeToggle);
    }

    // method to populate checkboxes for Extras in the application
    private void addExtraCheckBoxes() {
        for (Extra extra : Extra.values()){
            CheckBox extraCheckBox = new CheckBox(extra.toString());
            extraCheckBox.setUserData(extra);  // Store the extra in userData for easy retrieval
            // Add CheckBox to the Pane
            fxExtraPane.getChildren().add(extraCheckBox);
        }
    }

    // method for showing Flavor ChoiceBoxes for a given toggle, which determines Size.
    private void showFlavorSelections(Toggle toggle) {
        fxFlavorPane.getChildren().clear();
        Label labelFlavorTitle = new Label("Flavors");
        labelFlavorTitle.getStyleClass().add("header"); // add class to be picked up by style.css
        fxFlavorPane.getChildren().add(labelFlavorTitle);
        Size size = (Size) toggle.getUserData();
        for(int i=1; i<=size.getNumScoops(); i++){
            Label lbl = new Label("Flavor "+i);
            ChoiceBox<Flavor> cbFlavor = new ChoiceBox<>();
            cbFlavor.getStyleClass().add("custom-choicebox"); // used css class to set font
            for(Flavor flavor : Flavor.values()){
                cbFlavor.getItems().add(flavor);
            }
            if(i<=3) {
                cbFlavor.getSelectionModel().select(i-1); // since index starts at 0.
            } else { // in case number of scoops exceeds 3.
                cbFlavor.getSelectionModel().selectFirst();
            }
            fxFlavorPane.getChildren().add(lbl);
            fxFlavorPane.getChildren().add(cbFlavor);
        }
    }

    // method to populate Size options in the application.
    private void addSizeRadioButtons() {
        // Initialize the ToggleGroup
        sizeToggleGroup = new ToggleGroup();
        // Create RadioButtons for each Size enum
        for (Size size : Size.values()) {
            RadioButton sizeRadioButton = new RadioButton(size.toString());
            sizeRadioButton.setToggleGroup(sizeToggleGroup);  // Add to ToggleGroup so that multiple selections can't be made at the same time.
            sizeRadioButton.setUserData(size);  // Store the size in userData for easy retrieval

            /* add listener that shows new ChoiceBoxes for flavors and resets extra selections
            ** when changing size
             */
            sizeRadioButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    showFlavorSelections(sizeRadioButton);
                    resetExtraSelections();
                }
            });
            fxSizePane.getChildren().add(sizeRadioButton);
        }
    }

    private void resetExtraSelections() {
        for(Node node : fxExtraPane.getChildren()){
            if(node instanceof CheckBox){
                ((CheckBox) node).setSelected(false);
            }
        }
    }
} // end controller