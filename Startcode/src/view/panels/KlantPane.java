package view.panels;

import controller.KlantController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;

public class KlantPane extends GridPane {
    private TableView table;
    private Label error = new Label("Klant error"), totaal;
    private KlantController controller;
    private ObservableList<Artikel> artikelsKlant = observableArrayList();


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


        table = new TableView<>();
        table.setPrefWidth(REMAINING);
        table.setPrefHeight(REMAINING);

        TableColumn omschrijvingCol = new TableColumn<>("Beschrijving");
        omschrijvingCol.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn aantal = new TableColumn<>("Aantal");
        aantal.setCellValueFactory(new PropertyValueFactory<>("AANTAL"));

        table.setItems(artikelsKlant);
        table.getColumns().add(omschrijvingCol);
        table.getColumns().add(aantal);
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
    public void addArtikel(Artikel artikel) {
        Artikel res = new Artikel("x","x","x",0.001,1);
        if(artikelsKlant.contains(artikel)){
            pasAantalAan(artikel);
            /*artikelsKlant.add(res);
            artikelsKlant.remove(res);*/
        } else {
            artikelsKlant.add(artikel);
            ArrayList<Artikel> current = new ArrayList<>(artikelsKlant);
            artikelsKlant = FXCollections.observableArrayList(current);
        }
    }

    /**
     * Deze methode haalt de label error op.
     *
     * @return de error label.
     */
    public Label getError() {
        return error;
    }


    //-----------------------//
    public Artikel getArtikel(Artikel artikel){
        Artikel res = null;
        for (Artikel a : artikelsKlant){
            if(a == artikel) res = a;
        }
        return res;
    }

    public void pasAantalAan(Artikel artikel){
        Artikel res = getArtikel(artikel);
        res.setAANTAL(res.getAANTAL() + 1);
        artikelsKlant.remove(artikel);
        artikelsKlant.add(res);

        ArrayList<Artikel> current = new ArrayList<>(artikelsKlant);
        artikelsKlant = FXCollections.observableArrayList(current);
    }
}

