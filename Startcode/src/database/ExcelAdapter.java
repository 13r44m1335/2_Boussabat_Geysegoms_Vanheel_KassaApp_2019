package database;

import model.Artikel;

import java.util.ArrayList;

public class ExcelAdapter implements LoadSave {

    private ExcelLoadSaveStrategy excel = new ExcelLoadSaveStrategy();

    @Override
    public ArrayList<Artikel> load(String filepath) {
        return excel.load(filepath);
    }

    @Override
    public void save(ArrayList<Artikel> artikels, String filepath) {
        excel.save(artikels, filepath);
    }
}
