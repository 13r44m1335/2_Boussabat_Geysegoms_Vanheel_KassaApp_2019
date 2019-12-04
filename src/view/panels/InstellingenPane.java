package view.panels;

import controller.InstellingenController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    private Label input = new Label("Inlezen uit:");
    private Label korting = new Label("Korting:");
    private Label percent = new Label("%  ");
    private Label euro = new Label("€  ");

    /**
     * Deze methode maakt een pane aan en koppelt deze aan een controller.
     * @param instellingenController de controller.
     * @author Andreas Geysegoms
     */
    public InstellingenPane(InstellingenController instellingenController) {
        percent.setVisible(false);
        euro.setVisible(false);
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
        comboBox.setPlaceholder(placeholder);
        comboBox.getItems().add("Groepkorting");
        comboBox.getItems().add("Drempelkorting");
        comboBox.getItems().add("Duurstekorting");
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
        this.add(input,0,0);
        this.add(korting, 1,0);
        this.add(comboBox, 1, 1);
        this.add(btnExcel, 0, 1);
        this.add(btnTxt, 0, 2);
        this.add(this.comboBoxText, 2, 1);
        this.add(percent,3,1);
        this.add(this.additional, 4, 1);
        this.comboBoxText.setVisible(false);
        this.additional.setVisible(false);
        this.add(euro,5,1);
        this.add(this.saveKorting, 6, 1);
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
    private void setCombobox(String in) {
        percent.setVisible(true);
        euro.setVisible(false);
        switch (in) {
            case "Groepkorting":
                this.comboBoxText.setVisible(true);
                this.additional.setText("Groep");
                this.additional.setVisible(true);
                break;
            case "Drempelkorting":
                this.comboBoxText.setVisible(true);
                this.additional.setText("Drempel");
                this.additional.setVisible(true);
                this.euro.setVisible(true);
                break;
            case "Duurstekorting":
                this.comboBoxText.setVisible(true);
                this.additional.setVisible(false);
                break;
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
