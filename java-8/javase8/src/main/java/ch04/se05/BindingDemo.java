package ch04.se05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BindingDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label s = new Label("Shipping");
        TextArea shipping = new TextArea();
        Label b = new Label("Billing");
        TextArea billing = new TextArea();
        billing.textProperty().bindBidirectional(shipping.textProperty());
        VBox root = new VBox();
        root.getChildren().addAll(s, shipping, b, billing);
        Scene scene = new Scene(root);
        stage.setTitle("Binding");
        stage.setScene(scene);
        stage.show();
    }
}
