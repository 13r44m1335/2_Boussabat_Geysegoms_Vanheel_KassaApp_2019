package database;

import database.ExcelLoadSaveStrategy;
import database.LoadSave;
import java.util.ArrayList;
import model.Artikel;

public class ExcelAdapter
        implements LoadSave {
    private ExcelLoadSaveStrategy excel = new ExcelLoadSaveStrategy();

    public ArrayList<Artikel> load(String filepath) {
        return this.excel.load(filepath);
    }

    public void save(ArrayList<Artikel> artikels, String filepath) {
        this.excel.save(artikels, filepath);
    }
}