package view.panels;

import controller.InstellingenController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Deze pane wordt gebruikt voor de instellingen.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public class InstellingenPane extends GridPane {

    private RadioButton btnExcel, btnTxt;
    private ComboBox comboBox;
    private TextField comboBoxText;
    private TextField additional;
    private Button saveKorting;

    /**
     * Deze methode maakt een pane aan en koppelt deze aan een controller.
     * @param instellingenController de controller.
     * @author Andreas Geysegoms
     */
    public InstellingenPane(InstellingenController instellingenController) {
        this.comboBoxText = new TextField("Vul hier het gewenste percentage in.");
        this.additional = new TextField("drempel");
        this.saveKorting = new Button("Save");
        instellingenController.setView(this);
        btnExcel = new RadioButton("Excel");
        btnTxt = new RadioButton("Txt");
        ToggleGroup group = new ToggleGroup();
        btnTxt.setToggleGroup(group);
        btnExcel.setToggleGroup(group);
        ComboBox comboBox = new ComboBox();
        Label placeholder = new Label("kies een korting");
        comboBox.setPlaceholder((Node) placeholder);
        comboBox.getItems().add((Object) "Groepkorting");
        comboBox.getItems().add((Object) "Drempelkorting");
        comboBox.getItems().add((Object) "Duurstekorting");
        HBox hbox = new HBox(new Node[]{(Node) comboBox});
        comboBox.setOnAction(event -> {
            String newVal = (String) comboBox.getValue();
            this.setCombobox(newVal);
        });


        instellingenController.setStandard();

        btnTxt.setOnAction(event -> {
            instellingenController.setTxt();
        });

        btnExcel.setOnAction(event -> {
            instellingenController.setExcel();
        });
        this.add(comboBox, 1, 0);
        this.add(btnExcel, 0, 0);
        this.add(btnTxt, 0, 1);
        this.add(this.comboBoxText, 2, 0);
        this.add(this.additional, 3, 0);
        this.comboBoxText.setVisible(false);
        this.additional.setVisible(false);
        this.add(this.saveKorting, 4, 0);
        this.saveKorting.setVisible(false);
        this.saveKorting.setOnAction(event -> {
            instellingenController.createKorting((String) comboBox.getValue(), this.comboBoxText.getText(), this.additional.getText());
        });
    }

    /**
     * Deze methode stelt de combobox van de kortingen in.
     * @param in de input voor de korting.
     * @author Andreas Geysegoms
     */
    public void setCombobox(String in) {
        if (in.equals("Groepkorting")) {
            this.comboBoxText.setVisible(true);
            this.additional.setText("Groep");
            this.additional.setVisible(true);
        } else if (in.equals("Drempelkorting")) {
            this.comboBoxText.setVisible(true);
            this.additional.setText("Drempel");
            this.additional.setVisible(true);
        } else if (in.equals("Duurstekorting")) {
            this.comboBoxText.setVisible(true);
            this.additional.setVisible(false);
        }
        this.saveKorting.setVisible(true);
    }

    /**
     * Deze methode stelt de radiobutton in op excel bij inladen.
     * @author Andreas Geysegoms
     */
    public void setExcelStandard() {
        btnTxt.setSelected(false);
        btnExcel.setSelected(true);
    }

    /**
     * Deze methode stelt de radiobutton in op txt bij inladen.
     * @author Andreas Geysegoms
     */
    public void setTxtStandard() {
        btnExcel.setSelected(false);
        btnTxt.setSelected(true);
    }
}
