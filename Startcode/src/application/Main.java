package application;

import controller.KlantController;
import database.ExcelLoadSaveStrategy;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Artikel;
import view.KassaView;
import view.KlantView;

import java.util.ArrayList;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
