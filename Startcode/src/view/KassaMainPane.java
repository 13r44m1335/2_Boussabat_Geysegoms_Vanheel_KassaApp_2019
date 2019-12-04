package view;


import controller.InstellingenController;
import controller.KassaController;
import controller.KlantController;
import controller.StockController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Winkel;
import view.panels.InstellingenPane;
import view.panels.KassaPane;
import view.panels.ProductOverviewPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(){

	    Winkel winkel = new Winkel();

	    KassaController controller = new KassaController(winkel);
        KlantController controller1 = new KlantController(winkel);

        KassaPane kassaPane = new KassaPane(controller);
	    TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa",kassaPane);

        StockController stockController = new StockController(winkel);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(stockController);

        InstellingenController instellingenController = new InstellingenController(winkel);
        InstellingenPane instellingenPane = new InstellingenPane(instellingenController);

        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen",instellingenPane);
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
