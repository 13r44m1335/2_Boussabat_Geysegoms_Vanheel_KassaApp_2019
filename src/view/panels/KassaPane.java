package view.panels;

import com.sun.jdi.Value;
import controller.KassaController;
import controller.StockController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Artikel;

import java.util.ArrayList;

import java.util.ArrayList;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class KassaPane extends GridPane {
    private TableView table;
    private Button btnHold, btnResume, btnCancel, btnSell;
    private TextField artikelCodeField;
    private Label error = new Label("Niet bestaande code"), totaal;
    private KassaController controller;
    private ObservableList<Artikel> artikels = FXCollections.observableArrayList();

    public KassaPane(KassaController controller) {
        this.controller = controller;
        controller.setView(this);
        setTable();
    }

    private void setTable() {
        this.setPrefHeight(150);
        this.setPrefWidth(300);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Artikel code:"), 0, 1, 1, 1);
        artikelCodeField = new TextField();
        this.add(artikelCodeField, 1, 1, 1, 1);
        table = new TableView<>();
        table.setId("my-table");
        //table.setPrefWidth(REMAINING);
        this.add(error, 0, 0, 1, 1);
        error.setVisible(false);
        btnHold = new Button("Zet een verkoop op hold");
        btnResume = new Button("Resume hold");
        btnSell = new Button("BETAALD");
        btnCancel = new Button("Verkoop annuleren");
        btnResume.setDisable(true);

        artikelCodeField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                error.setVisible(false);
                controller.scan(artikelCodeField.getText());
            }
        });

        Button scanButton = new Button("Scan");
        scanButton.setOnAction(event -> {
            error.setVisible(false);
            controller.scan(artikelCodeField.getText());
        });
        this.add(scanButton, 2, 1, 1, 1);

        Button deleteButton = new Button("Verwijder");
        deleteButton.setOnAction(event -> {
            error.setVisible(false);
            if (table.getSelectionModel().getSelectedCells().isEmpty()) {
                controller.verwijderArtikelByInput(artikelCodeField.getText());
            }
            else {
                Artikel artikel = (Artikel) table.getSelectionModel().getSelectedItem();
                controller.verwijderArtikel(artikel);
            }
        });
        this.add(deleteButton, 3,1,1,1);


        TableColumn omschrijvingCol = new TableColumn<>("Beschrijving");
        omschrijvingCol.setCellValueFactory(new PropertyValueFactory("omschrijving"));
        table.getColumns().add(omschrijvingCol);
        omschrijvingCol.setPrefWidth(245);
        TableColumn<Artikel, String> prijsCol = new TableColumn<>("Prijs");
        prijsCol.setCellValueFactory(cell -> new SimpleStringProperty(String.format("€ %.2f",cell.getValue().getVerkoopprijs())));
        table.getColumns().add(prijsCol);
        prijsCol.setPrefWidth(245);
        TableColumn<Artikel, String> aantal = new TableColumn("Aantal");
        aantal.setCellValueFactory(cell -> new SimpleStringProperty("1"));
        table.getColumns().add(aantal);
        aantal.setPrefWidth(245);
        this.add(table, 0, 4, 5, 6);
        this.add(btnHold,1,11);
        this.add(btnResume,2,11);
        this.add(btnSell,1,12);
        this.add(btnCancel,3,12);

        btnResume.setOnAction(event ->  {
            controller.resume();
        });

        btnHold.setOnAction(event -> {
            controller.putOnHold();
        });

        btnSell.setOnAction(event -> {
            controller.sell();
        });

        btnCancel.setOnAction(event -> {
            controller.cancel();
        });

        table.setItems(artikels);
        totaal = new Label();
        totaal.setText("Totale bedrag: € 0,00");
        this.add(totaal, 0,11);
        btnSell.setOnAction(event -> {
            controller.sell();
        });

    }

    /**
     * Deze methode stelt de label van het totale bedrag in.
     * @param totaalS het totale bedrag.
     * @author Andreas Geysegoms
     */
    public void setTotaal(double totaalS) {
        totaal.setText(String.format("Totale bedrag: € %.2f",totaalS));
    }

    /**
     * Deze methode updatet de view en zet een error indien nodig.
     * @param artikel het artikel dat ingescand is.
     * @author Andreas Geysegoms
     */
    public void addArtikel(Artikel artikel) {
        error.setVisible(false);
        this.artikels.add(artikel);
    }

    /**
     * Deze methode haalt de label error op.
     * @return de error label.
     * @author Andreas Geysegoms
     */
    public Label getError() {
        return error;
    }

    /**
     * Deze methode haalt de textfield waarmee we artikels inscannen op.
     * @return het scan veld.
     * @author Andreas Geysegoms
     */
    public TextField getArtikelCodeField() {
        return artikelCodeField;
    }

    /**
     * Deze methode haalt alle artikels uit de winkelmand op.
     * @return de artikels van de winkelmand.
     * @author Andreas Geysegoms
     */
    public ArrayList<Artikel> getAll() {
        return new ArrayList<>(artikels);
    }

    /**
     * Deze methode handelt het visuele van het op hold zetten van een aankoop.
     * Deze methode reset de view.
     * @author Andreas Geysegoms
     */
    public void reset() {
        artikels = FXCollections.observableArrayList();
        btnHold.setDisable(true);
        btnResume.setDisable(false);
        this.table.setItems(artikels);
        this.setTotaal(0);
    }

    /**
     * Deze methode reset de view na betaald te zijn.
     * @author Andreas Geysegoms
     */
    public void resetVerkoop() {
        artikels = FXCollections.observableArrayList();
        this.table.setItems(artikels);
        this.setTotaal(0);
    }

    /**
     * Deze methode handelt het visuele van herstellen van de on hold aankoop.
     * @param artikelArrayList de artikels uit de winkelwagen die op hold staat.
     * @author Andreas Geysegoms
     */
    public void resume(ArrayList<Artikel> artikelArrayList) {
        artikels = FXCollections.observableArrayList(artikelArrayList);
        this.table.setItems(artikels);
        btnHold.setDisable(false);
        btnResume.setDisable(true);
    }

    /**
     * Deze methode stelt de artikels in.
     * @param artikels de artikels.
     * @author Andreas Geysegoms
     */
    public void setArtikels(ArrayList<Artikel> artikels) {
        this.artikels = FXCollections.observableArrayList(artikels);
        table.setItems(this.artikels);
    }

}