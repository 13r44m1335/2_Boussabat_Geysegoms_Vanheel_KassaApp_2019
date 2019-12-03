package view.panels;

import controller.StockController;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Artikel;

public class ProductOverviewPane
        extends GridPane {
    private TableView<Artikel> table = new TableView();
    private ObservableList<Artikel> artikels = FXCollections.observableArrayList();

    public ProductOverviewPane(StockController controller) {
        this.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
        this.setVgap(5.0);
        this.setHgap(5.0);
        TableColumn code = new TableColumn("Artikel code");
        TableColumn beschrijving = new TableColumn("Artikel beschrijving");
        TableColumn groep = new TableColumn("Artikel groep");
        TableColumn prijs = new TableColumn("Eenheidsprijs");
        TableColumn voorraad = new TableColumn("Actuele voorraad");
        code.setMinWidth(100.0);
        beschrijving.setMinWidth(100.0);
        groep.setMinWidth(100.0);
        prijs.setMinWidth(50.0);
        voorraad.setMinWidth(50.0);
        code.setCellValueFactory((Callback)new PropertyValueFactory("code"));
        beschrijving.setCellValueFactory((Callback)new PropertyValueFactory("omschrijving"));
        groep.setCellValueFactory((Callback)new PropertyValueFactory("artikelGroep"));
        prijs.setCellValueFactory((Callback)new PropertyValueFactory("verkoopprijs"));
        voorraad.setCellValueFactory((Callback)new PropertyValueFactory("actueleVoorraad"));
        controller.setStock(this);
        this.add((Node)new Label("Products:"), 0, 0, 1, 1);
        controller.toonArtikelen();
        this.table.setItems(this.artikels);
        this.table.getColumns().addAll(new TableColumn[]{code, beschrijving, groep, prijs, voorraad});
        this.add(this.table, 0, 1);
    }

    public void updateStockView(ArrayList<Artikel> artikels) {
        this.artikels.clear();
        this.artikels.addAll(artikels);
    }
}