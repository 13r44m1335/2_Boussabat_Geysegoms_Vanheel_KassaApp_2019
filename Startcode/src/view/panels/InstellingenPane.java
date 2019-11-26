package view.panels;

import controller.InstellingenController;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Deze pane wordt gebruikt voor de instellingen.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class InstellingenPane extends GridPane {

    private RadioButton btnExcel, btnTxt;

    /**
     * Deze methode maakt een pane aan en koppelt deze aan een controller.
     * @param instellingenController de controller.
     * @author Andreas Geysegoms
     */
    public InstellingenPane(InstellingenController instellingenController) {
        btnExcel = new RadioButton("Excel");
        btnTxt = new RadioButton("Txt");
        ToggleGroup group = new ToggleGroup();
        btnTxt.setToggleGroup(group);
        btnExcel.setToggleGroup(group);

        btnTxt.setOnAction(event -> {
            instellingenController.setTxt();
        });

        btnExcel.setOnAction(event -> {
            instellingenController.setExcel();
        });

        this.add(btnExcel,0,0);
        this.add(btnTxt,0,1);

    }


}
