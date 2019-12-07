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

public class KlantPane extends GridPane {
    private TableView table;
    private Label error = new Label("Klant error"), totaal;
    private KlantController controller;
    private ObservableList<Artikel> artikels = FXCollections.observableArrayList();


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

        table.setItems(artikels);
        table.getColumns().add(omschrijvingCol);
        table.getColumns().add(aantal);
        totaal = new Label();

    }


    /**
     * Deze methode stelt de label van het totale bedrag in.
     *
     * @param totaalS het totale bedrag.
     * @author Andreas Geysegoms
     */

    public void setTotaal(double totaalS) {
        totaal.setText("Totale bedrag: € " + String.valueOf(totaalS));
    }

    /**
     * Deze methode updatet de view en zet een error indien nodig.
     *
     * @param artikel het artikel dat ingescand is.
     * @author Andreas Geysegoms
     */
    public void addArtikel(Artikel artikel) {
        error.setVisible(false);
        this.artikels.add(artikel);
    }

    /**
     * Deze methode haalt de label error op.
     *
     * @return de error label.
     * @author Andreas Geysegoms
     */
    public Label getError() {
        return error;
    }


    public ObservableList<Artikel> getArtikels() {
        return artikels;
    }
}

