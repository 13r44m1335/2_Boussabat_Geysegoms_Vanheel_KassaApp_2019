package view;


import controller.StockController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.KassaPane;
import view.panels.ProductOverviewPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(){

        KassaPane kassaPane = new KassaPane();
	    TabPane tabPane = new TabPane(); 	    
        Tab kassaTab = new Tab("Kassa",kassaPane);

        StockController stockController = new StockController();
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(stockController);


        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
