package view.panels;

import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import controller.InstellingenController;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

public class InstellingenPane extends GridPane
{
    private RadioButton btnExcel;
    private RadioButton btnTxt;
    private ComboBox comboBox;
    private TextField comboBoxText;
    private TextField additional;
    private Button saveKorting;

    public InstellingenPane(final InstellingenController instellingenController) {
        this.comboBoxText = new TextField("Vul hier het gewenste percentage in.");
        this.additional = new TextField("drempel");
        this.saveKorting = new Button("Save");
        instellingenController.setView(this);
        this.btnExcel = new RadioButton("Excel");
        this.btnTxt = new RadioButton("Txt");
        final ToggleGroup group = new ToggleGroup();
        this.btnTxt.setToggleGroup(group);
        this.btnExcel.setToggleGroup(group);
        final ComboBox comboBox = new ComboBox();
        final Label placeholder = new Label("kies een korting");
        comboBox.setPlaceholder((Node)placeholder);
        comboBox.getItems().add((Object)"Groepkorting");
        comboBox.getItems().add((Object)"Drempelkorting");
        comboBox.getItems().add((Object)"Duurstekorting");
        final HBox hbox = new HBox(new Node[] { (Node)comboBox });
        comboBox.setOnAction(event -> {
            final String newVal = (String)comboBox.getValue();
            this.setCombobox(newVal);
        });
        this.add((Node)hbox, 1, 0);
        instellingenController.setStandard();
        this.btnTxt.setOnAction(event -> instellingenController.setTxt());
        this.btnExcel.setOnAction(event -> instellingenController.setExcel());
        this.add((Node)this.btnExcel, 0, 0);
        this.add((Node)this.btnTxt, 0, 1);
        this.add((Node)this.comboBoxText, 2, 0);
        this.add((Node)this.additional, 3, 0);
        this.comboBoxText.setVisible(false);
        this.additional.setVisible(false);
        this.add((Node)this.saveKorting, 4, 0);
        this.saveKorting.setVisible(false);
        this.saveKorting.setOnAction(event -> instellingenController.createKorting((String)comboBox.getValue(), this.comboBoxText.getText(), this.additional.getText()));
    }

    public void setExcelStandard() {
        this.btnTxt.setSelected(false);
        this.btnExcel.setSelected(true);
    }

    public void setTxtStandard() {
        this.btnExcel.setSelected(false);
        this.btnTxt.setSelected(true);
    }

    public void setCombobox(final String in) {
        if (in.equals("Groepkorting")) {
            this.comboBoxText.setVisible(true);
            this.additional.setText("Groep");
            this.additional.setVisible(true);
        }
        else if (in.equals("Drempelkorting")) {
            this.comboBoxText.setVisible(true);
            this.additional.setText("Drempel");
            this.additional.setVisible(true);
        }
        else if (in.equals("Duurstekorting")) {
            this.comboBoxText.setVisible(true);
            this.additional.setVisible(false);
        }
        this.saveKorting.setVisible(true);
    }
}