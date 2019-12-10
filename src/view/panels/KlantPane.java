package view.panels;

import controller.KlantController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.Artikel;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class KlantPane extends GridPane {
    private TableView table;
    private Label error = new Label("Klant error"), totaal;
    private KlantController controller;
    private ObservableList<Pair<Artikel, Integer>> artikelsKlant = observableArrayList();


    public KlantPane(KlantController controller) {
        this.controller = controller;
        controller.setView(this);
        setTable();
        this.add(table, 0, 0);
        this.add(totaal, 0, 1);
    }


    public void setTable() {
        this.setPrefHeight(150);
        this.setPrefWidth(300);
        this.setPadding(new Insets(5, 5, 5, 5));

        table = new TableView<Pair<Artikel, Integer>>();
        table.setPrefWidth(REMAINING);
        table.setPrefHeight(REMAINING);

        TableColumn<Pair<Artikel, Integer>, String> omschrijvingCol = new TableColumn<>("Beschrijving");
        omschrijvingCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKey().getOmschrijving()));

        TableColumn<Pair<Artikel, Integer>, Integer> aantal = new TableColumn<>("Aantal");
        aantal.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getValue()).asObject());

        TableColumn<Pair<Artikel, Number>, Double> prijsCol = new TableColumn<>("Prijs");
        prijsCol.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getKey().getVerkoopprijs()).asObject());

        table.setItems(artikelsKlant);
        table.getColumns().add(omschrijvingCol);
        table.getColumns().add(aantal);
        table.getColumns().add(prijsCol);
        totaal = new Label();

    }


    /**
     * Deze methode stelt de label van het totale bedrag in.
     *
     * @param totaalS het totale bedrag.

     */

    public void setTotaal(double totaalS) {
        totaal.setText("Totale bedrag: € " + String.valueOf(totaalS));
    }

    /**
     * Deze methode updatet de view en zet een error indien nodig.
     *
     * @param artikel het artikel dat ingescand is.
     */

    /**
     * Deze methode haalt de label error op.
     *
     * @return de error label.
     */
    public Label getError() {
        return error;
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
}

