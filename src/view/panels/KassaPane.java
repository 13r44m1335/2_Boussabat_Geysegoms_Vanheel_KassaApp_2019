package view.panels;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import model.Artikel;
import javafx.collections.ObservableList;
import controller.KassaController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class KassaPane extends GridPane
{
    private TableView table;
    private Button btnAdd;
    private Button btnCancel;
    private TextField artikelCodeField;
    private Label error;
    private Label totaal;
    private KassaController controller;
    private ObservableList<Artikel> artikels;

    public KassaPane(final KassaController controller) {
        this.error = new Label("Niet bestaande code");
        this.artikels = (ObservableList<Artikel>)FXCollections.observableArrayList();
        (this.controller = controller).setView(this);
        this.setTable();
    }

    public void setTable() {
        this.setPrefHeight(150.0);
        this.setPrefWidth(300.0);
        this.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        this.setVgap(5.0);
        this.setHgap(5.0);
        this.add((Node)new Label("Artikel code:"), 0, 1, 1, 1);
        this.add((Node)(this.artikelCodeField = new TextField()), 1, 1, 1, 1);
        (this.table = new TableView()).setPrefWidth(2.147483647E9);
        this.add((Node)this.error, 0, 0, 1, 1);
        this.error.setVisible(false);
        this.artikelCodeField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.controller.scan(this.artikelCodeField.getText());
            }
        });
        final TableColumn omschrijvingCol = new TableColumn("Beschrijving");
        omschrijvingCol.setCellValueFactory((Callback)new PropertyValueFactory("omschrijving"));
        this.table.getColumns().add((Object)omschrijvingCol);
        final TableColumn prijsCol = new TableColumn("Prijs");
        prijsCol.setCellValueFactory((Callback)new PropertyValueFactory("verkoopprijs"));
        this.table.getColumns().add((Object)prijsCol);
        final TableColumn aantal = new TableColumn("Aantal");
        aantal.setCellValueFactory((Callback)new PropertyValueFactory("AANTAL"));
        this.table.getColumns().add((Object)aantal);
        this.add((Node)this.table, 0, 4, 5, 6);
        this.table.setItems((ObservableList)this.artikels);
        (this.totaal = new Label()).setText("Totale bedrag: \u20ac 0.00");
        this.add((Node)this.totaal, 0, 11);
    }

    public void setTotaal(final double totaalS) {
        this.totaal.setText(invokedynamic(makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;, String.valueOf(totaalS)));
    }

    public void addArtikel(final Artikel artikel) {
        this.error.setVisible(false);
        this.artikels.add((Object)artikel);
    }

    public Label getError() {
        return this.error;
    }
}