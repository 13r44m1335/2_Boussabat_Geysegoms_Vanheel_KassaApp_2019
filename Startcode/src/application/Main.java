package application;
	
import controller.StockController;
import database.ArtikelTekstLoadSave;
import database.DbBehaviour;
import database.HashMapDb;
import database.TekstLoadSaveTemplate;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Observer;
import model.StockObserver;
import model.Winkel;
import view.KassaView;
import view.KlantView;

import static model.SoortObserver.STOCK;

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
