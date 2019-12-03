package view.panels;

import controller.InstellingenController;
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
    private TextField comboBoxText = new TextField("Vul hier het gewenste percentage in.");
    private TextField additional = new TextField("drempel");
    private Button saveKorting = new Button("Save");

    /**
     * Deze methode maakt een pane aan en koppelt deze aan een controller.
     * @param instellingenController de controller.
     * @author Andreas Geysegoms
     */
    public InstellingenPane(InstellingenController instellingenController) {
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

        //if (comboBox.getValue().equals("Groepkorting")) System.out.println("Eureka!");

        HBox hbox = new HBox(comboBox);

        comboBox.setOnAction(event -> {
            String newVal = (String) comboBox.getValue();
            setCombobox(newVal);
        });

        this.add(hbox, 1,0);

        instellingenController.setStandard();

        btnTxt.setOnAction(event -> {
            instellingenController.setTxt();
        });

        btnExcel.setOnAction(event -> {
            instellingenController.setExcel();
        });

        this.add(btnExcel,0,0);
        this.add(btnTxt,0,1);
        this.add(comboBoxText,2,0);
        this.add(additional,3,0);
        comboBoxText.setVisible(false);
        additional.setVisible(false);
        this.add(saveKorting,4,0);
        saveKorting.setVisible(false);
        saveKorting.setOnAction(event -> {
            instellingenController.createKorting((String) comboBox.getValue(), comboBoxText.getText(), additional.getText());
        });
    }

    /**
     * Deze methode stelt de radiobutton in op excel bij inladen.
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

    public void setCombobox(String in) {
        if (in.equals("Groepkorting")) {
            comboBoxText.setVisible(true);
            additional.setText("Groep");
            additional.setVisible(true);
        } else if (in.equals("Drempelkorting")) {
            comboBoxText.setVisible(true);
            additional.setText("Drempel");
            additional.setVisible(true);
        }
        else if (in.equals("Duurstekorting")) {
            comboBoxText.setVisible(true);
            additional.setVisible(false);
        }
        saveKorting.setVisible(true);
    }
}
