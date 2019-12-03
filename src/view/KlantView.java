package view;

import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.collections.FXCollections;
import model.Artikel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class KlantView
{
    private Stage stage;
    private TableView table;
    private ObservableList<Artikel> artikels;

    public KlantView() {
        this.stage = new Stage();
        this.table = new TableView();
        this.artikels = (ObservableList<Artikel>)FXCollections.observableArrayList();
        this.stage.setTitle("KLANT VIEW");
        this.stage.setResizable(false);
        this.stage.setX(775.0);
        this.stage.setY(20.0);
        final Group root = new Group();
        final Scene scene = new Scene((Parent)root, 500.0, 500.0);
        this.stage.setScene(scene);
        this.stage.sizeToScene();
        this.setTable();
        this.stage.show();
    }

    private void setTable() {
        this.table = new TableView();
        final TableColumn omschrijvingCol = new TableColumn("Beschrijving");
        omschrijvingCol.setCellValueFactory((Callback)new PropertyValueFactory("omschrijving"));
        this.table.getColumns().add((Object)omschrijvingCol);
        final TableColumn prijsCol = new TableColumn("Prijs");
        prijsCol.setCellValueFactory((Callback)new PropertyValueFactory("verkoopprijs"));
        this.table.getColumns().add((Object)prijsCol);
        final TableColumn aantal = new TableColumn("Aantal");
        aantal.setCellValueFactory((Callback)new PropertyValueFactory("AANTAL"));
        this.table.getColumns().add((Object)aantal);
        this.table.setItems((ObservableList)this.artikels);
    }
}