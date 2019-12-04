package view;

import controller.KlantController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Winkel;
import view.panels.KlantPane;

public class KlantMainPane extends BorderPane {

    public KlantMainPane() {
        Winkel winkel = new Winkel();

        KlantController controller = new KlantController(winkel);

        KlantPane klantPane = new KlantPane(controller);
        TabPane tabPane = new TabPane();
        Tab klantenTab = new Tab("Klanten", klantPane);


        tabPane.getTabs().add(klantenTab);
        this.setCenter(tabPane);
    }
}
