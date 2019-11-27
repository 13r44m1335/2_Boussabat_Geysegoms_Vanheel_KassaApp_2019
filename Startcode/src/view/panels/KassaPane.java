package view.panels;

import controller.KassaController;
import controller.StockController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Label error = new Label("");

    public KassaPane(KassaController controller) {
        controller.setView(this);
        setTable();
    }

    public void setTable() {
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
        error = new Label();
        error.setVisible(false);


        TableColumn omschrijvingCol = new TableColumn<>("Omschrijving");
        omschrijvingCol.setCellValueFactory(new PropertyValueFactory("omschrijving"));
        table.getColumns().add(omschrijvingCol);

        TableColumn prijsCol = new TableColumn<>("Prijs");
        prijsCol.setCellValueFactory(new PropertyValueFactory("prijs"));
        table.getColumns().add(prijsCol);

        this.add(table, 0, 4, 5, 6);


    }







}