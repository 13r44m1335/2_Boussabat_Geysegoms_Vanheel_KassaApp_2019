package view.panels;

import com.sun.jdi.Value;
import controller.KassaController;
import controller.StockController;
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
    private Button btnAdd, btnCancel;
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
        table.setPrefWidth(REMAINING);
        this.add(error, 0, 0, 1, 1);
        error.setVisible(false);

        artikelCodeField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                controller.scan(artikelCodeField.getText());
            }
        });
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

        this.add(table, 0, 4, 5, 6);
        table.setItems(artikels);
        totaal = new Label();
        totaal.setText("Totale bedrag: € 0.00");
        this.add(totaal, 0,11);
    }

    /**
     * Deze methode stelt de label van het totale bedrag in.
     * @param totaalS het totale bedrag.
     * @author Andreas Geysegoms
     */
    public void setTotaal(double totaalS) {
        totaal.setText("Totale bedrag: € " + String.valueOf(totaalS));
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
}