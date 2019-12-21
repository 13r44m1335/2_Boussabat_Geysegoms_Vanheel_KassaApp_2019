package view.panels;

import controller.LogController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Artikel;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;

/**
 * Deze pane zal de in de log tab aantonen wat er verkocht is geweest
 *
 * @author Reda Boussabat
 */

public class LogPane extends GridPane {

    private TableView<Artikel> tableView = new TableView();
    private ObservableList<Artikel> logs = FXCollections.observableArrayList();


    /**
     * Deze methode maakt een overview pane aan gekoppelde aan een controller met een gelijkaardige naam
     *
     * @param controller
     * @author Reda Boussabat
     */
    public LogPane(LogController controller) {

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        TableColumn datum = new TableColumn("Datum verkoop");
        TableColumn tijdstip = new TableColumn("Tijdstip verkoop");
        TableColumn totaalbedrag = new TableColumn("Totaal Bedrag");
        TableColumn korting = new TableColumn("Korting");
        TableColumn teBetalen = new TableColumn("Te betalen");

        datum.setMinWidth(50);
        tijdstip.setMinWidth(50);
        totaalbedrag.setMinWidth(100);
        korting.setMinWidth(100);
        teBetalen.setMinWidth(100);


        datum.setCellValueFactory(new PropertyValueFactory<>("datum"));
        tijdstip.setCellValueFactory(new PropertyValueFactory<>("tijd"));
        totaalbedrag.setCellValueFactory(new PropertyValueFactory<>("totaalbedrag"));
        korting.setCellValueFactory(new PropertyValueFactory<>("koring"));
        teBetalen.setCellValueFactory(new PropertyValueFactory<>("teBetalen"));

        controller.setView(this);
        this.add(new Label("Logs"),0,0,1,1);
        controller.toonLogs();

        tableView.setItems(logs);
        tableView.getColumns().addAll(datum, tijdstip, totaalbedrag, korting, teBetalen);
        this.add(tableView, 0,1);
    }

    /**
     * Deze methode updatet de logs
     * @param artikels zijn de informatie over voor de verkopen
     * @author Boussabat Reda
     */
    public void updateLog(ArrayList<Artikel> artikels) {
        this.logs.clear();
        this.logs.addAll(artikels);
    }
}
