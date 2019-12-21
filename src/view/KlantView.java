package view;

import controller.KlantController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Artikel;
import model.Winkel;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class KlantView extends GridPane {
    private Stage stage = new Stage();

    private TableView table;
    private Label totaal;
    private ObservableList<Pair<Artikel, Integer>> artikelsKlant = observableArrayList();

    /**
     * Deze methode maakt een instantie van de klasse aan ahv een winkel.
     * @param winkel een winkel.
     * @author Andreas Geysegoms
     */
    public KlantView(Winkel winkel) {
        KlantController controller = new KlantController(winkel);
        controller.setView(this);
        stage.setTitle("KLANT VIEW");
        stage.setResizable(false);
        stage.setX(775);
        stage.setY(20);
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add("application/klant.css");
        setTable();
        this.add(table, 0, 0);
        this.add(totaal, 0, 1);
        root.getChildren().add(this);
        setTotaal(0);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }


    /**
     * Deze methode stelt de tabel in.
     * @author Andreas Geysegoms
     */
    public void setTable() {
        this.setPrefHeight(500);
        this.setPrefWidth(500);
        this.setPadding(new Insets(5, 5, 5, 5));

        table = new TableView<Pair<Artikel, Integer>>();
        table.setPrefWidth(REMAINING);
        table.setPrefHeight(REMAINING);

        TableColumn<Pair<Artikel, Integer>, String> omschrijvingCol = new TableColumn<>("Beschrijving");
        omschrijvingCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKey().getOmschrijving()));

        TableColumn<Pair<Artikel, Integer>, Integer> aantal = new TableColumn<>("Aantal");
        aantal.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getValue()).asObject());

        TableColumn<Pair<Artikel, Number>, String> prijsCol = new TableColumn<>("Prijs");
        prijsCol.setCellValueFactory(cell -> new SimpleStringProperty(String.format("€ %.2f",cell.getValue().getKey().getVerkoopprijs())));
        table.setItems(artikelsKlant);
        table.getColumns().add(omschrijvingCol);
        table.getColumns().add(aantal);
        table.getColumns().add(prijsCol);
        omschrijvingCol.setPrefWidth(500/3-5);
        aantal.setPrefWidth(500/3-5);
        prijsCol.setPrefWidth(500/3-5);
        totaal = new Label();
    }


    /**
     * Deze methode stelt de label van het totale bedrag in.
     * @param totaalS het totale bedrag.
     */
    public void setTotaal(double totaalS) {
        totaal.setText(String.format("Totale bedrag: € %.2f",totaalS));
    }

    /**
     * Deze methode haalt de artikelen van de klant op.
     * @return de artikelen.
     * @author Andreas Geysegoms
     */
    public ArrayList<Pair<Artikel, Integer>> getArtikelsKlant() {
        return new ArrayList<>(artikelsKlant);
    }

    /**
     * Deze methode updatet de table.
     * @author Andreas Geysegoms
     */
    public void refresh() {
        table.setItems(artikelsKlant);
    }

    /**
     * Deze methode stelt de artikels van de klant in.
     * @param artikelsKlant de lijst aan pairs van artikelen.
     * @author Andreas Geysegoms
     */
    public void setArtikelsKlant(ArrayList<Pair<Artikel, Integer>> artikelsKlant) {
        this.artikelsKlant = FXCollections.observableArrayList(artikelsKlant);
    }

    /**
     * Deze methode reset de view na een verkoop op hold te zetten.
     * @author Andreas Geysegoms
     */
    public void reset() {
        artikelsKlant = FXCollections.observableArrayList();
        this.table.setItems(artikelsKlant);
        this.setTotaal(0);
    }

    /**
     * Deze methode herstelt de view ahv een lijst van paren van artikels na een verkoop op hold te zetten.
     * @param artikels een lijst van paren van artikels.
     * @author Andreas Geysegoms
     */
    public void resume(ArrayList<Pair<Artikel, Integer>> artikels) {
        artikelsKlant = FXCollections.observableArrayList(artikels);
        this.table.setItems(artikelsKlant);
    }
}
