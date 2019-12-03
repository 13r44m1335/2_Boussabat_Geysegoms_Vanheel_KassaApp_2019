package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Artikel;

public class KlantView {
    private Stage stage = new Stage();
    private TableView table = new TableView();
    //private KlantController controller;
    private ObservableList<Artikel> artikels = FXCollections.observableArrayList();

    public KlantView() {
        //setController(controller);
        //controller.setView(this);
        stage.setTitle("KLANT VIEW");
        stage.setResizable(false);
        stage.setX(775);
        stage.setY(20);
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.sizeToScene();
        setTable();
        stage.show();
    }

    /*private void setController(KlantController controller) {
        this.controller = controller;
    }*/

    private void setTable() {
        table = new TableView();
        TableColumn omschrijvingCol = new TableColumn<>("Beschrijving");
        omschrijvingCol.setCellValueFactory(new PropertyValueFactory("omschrijving"));
        table.getColumns().add(omschrijvingCol);

        TableColumn prijsCol = new TableColumn<>("Prijs");
        prijsCol.setCellValueFactory(new PropertyValueFactory("verkoopprijs"));
        table.getColumns().add(prijsCol);

        TableColumn aantal = new TableColumn("Aantal");
        //TODO: fix dit.
        aantal.setCellValueFactory(new PropertyValueFactory("AANTAL"));
        table.getColumns().add(aantal);
        table.setItems(artikels);
    }
}
