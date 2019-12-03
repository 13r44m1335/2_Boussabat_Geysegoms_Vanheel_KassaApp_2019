package view;

import view.panels.InstellingenPane;
import controller.InstellingenController;
import view.panels.ProductOverviewPane;
import controller.StockController;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import view.panels.KassaPane;
import controller.KassaController;
import model.Winkel;
import javafx.scene.layout.BorderPane;

public class KassaMainPane extends BorderPane
{
    public KassaMainPane() {
        final Winkel winkel = new Winkel();
        final KassaController controller = new KassaController(winkel);
        final KassaPane kassaPane = new KassaPane(controller);
        final TabPane tabPane = new TabPane();
        final Tab kassaTab = new Tab("Kassa", (Node)kassaPane);
        final StockController stockController = new StockController(winkel);
        final ProductOverviewPane productOverviewPane = new ProductOverviewPane(stockController);
        final InstellingenController instellingenController = new InstellingenController(winkel);
        final InstellingenPane instellingenPane = new InstellingenPane(instellingenController);
        instellingenController.setKassaController(controller);
        final Tab artikelTab = new Tab("Artikelen", (Node)productOverviewPane);
        final Tab instellingTab = new Tab("Instellingen", (Node)instellingenPane);
        final Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
        this.setCenter((Node)tabPane);
    }
}