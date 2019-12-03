package database;

import jxl.write.WriteException;
import java.util.Iterator;
import java.io.IOException;
import jxl.read.biff.BiffException;
import java.io.File;
import model.Artikel;
import java.util.ArrayList;
import excel.ExcelPlugin;

public class ExcelLoadSaveStrategy
{
    private ExcelPlugin excelPlugin;

    public ExcelLoadSaveStrategy() {
        this.excelPlugin = new ExcelPlugin();
    }

    public ArrayList<Artikel> load(final String filepath) {
        final File file = new File(filepath);
        final ArrayList<Artikel> res = new ArrayList<Artikel>();
        try {
            final ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>)this.excelPlugin.read(file);
            for (final ArrayList<String> list : data) {
                final String code = list.get(0);
                final String beschrijving = list.get(1);
                final String groep = list.get(2);
                final double prijs = Double.parseDouble(list.get(3));
                final int voorraad = Integer.parseInt(list.get(4));
                final Artikel a = new Artikel(code, beschrijving, groep, prijs, voorraad);
                res.add(a);
            }
        }
        catch (BiffException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        for (Artikel artikel : res) {}
        return res;
    }

    public void save(final ArrayList<Artikel> artikels, final String filepath) {
        final File file = new File(filepath);
        final ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        for (final Artikel artikel : artikels) {
            final ArrayList<String> resInner = new ArrayList<String>();
            final String code = artikel.getCode();
            final String beschrijving = artikel.getOmschrijving();
            final String artikelgroep = artikel.getArtikelGroep();
            final String prijs = String.valueOf(artikel.getVerkoopprijs());
            final String voorraad = String.valueOf(artikel.getActueleVoorraad());
            resInner.add(code);
            resInner.add(beschrijving);
            resInner.add(artikelgroep);
            resInner.add(prijs);
            resInner.add(voorraad);
            res.add(resInner);
        }
        try {
            this.excelPlugin.write(file, (ArrayList)res);
        }
        catch (IOException | WriteException | BiffException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
}