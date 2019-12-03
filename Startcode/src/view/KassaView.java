package view;

import javafx.scene.layout.BorderPane;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;

public class KassaView
{
    private Stage stage;

    public KassaView() {
        (this.stage = new Stage()).setTitle("KASSA VIEW");
        this.stage.setResizable(false);
        this.stage.setX(20.0);
        this.stage.setY(20.0);
        final Group root = new Group();
        final Scene scene = new Scene((Parent)root, 750.0, 500.0);
        final BorderPane borderPane = new KassaMainPane();
        borderPane.prefHeightProperty().bind((ObservableValue)scene.heightProperty());
        borderPane.prefWidthProperty().bind((ObservableValue)scene.widthProperty());
        root.getChildren().add(borderPane);
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        this.stage.show();
    }
}