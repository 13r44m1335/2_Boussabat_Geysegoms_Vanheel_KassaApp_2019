package controller;


import model.Artikel;
import model.Observer;
import model.SoortObserver;
import model.Winkel;
import view.panels.LogPane;

import java.util.ArrayList;
import java.util.Collections;

import static model.SoortObserver.LOG;

/**
 * Deze klasse fungeert als controller tussen de Log tab en de winkel
 *
 * @author Reda Boussabat
 */
public class LogController extends Observer {

    private Winkel winkel;
    private LogPane view;
    private ArrayList<Artikel> artikels;

    /**
     * Deze methode maakt een instantie aan van een Logcontroller adhv de winkel
     * @param winkel de winkel.
     * @author Reda Boussabat
     */

    public LogController(Winkel winkel) {
        super(winkel);
        this.winkel = winkel;
        winkel.registerObserver(this, LOG);
    }


    /**
     * Deze methode update de log tab.
     * @param logs de lijst van artikels die verkocht werden.
     * @param soort het soort update.
     * @author Andreas Geysegoms
     */
    @Override
    public void update(ArrayList<Artikel> logs, Enum soort) {
        Double[] res = new Double[3];
        res[0] = berekenTotaal(logs);
        try {
            res[1] = winkel.getKorting().berekenKorting(logs);
        } catch (NullPointerException e) {
            res[1] = 0.0;
        }
        res[2] = res[0] - res[1];
        view.updateLog(res);
    }

    /**
     * Deze methode berekent het totaal van de meegegeven artikels.
     * @param logs de meegegeven artikels.
     * @return de totale prijs.
     * @author Andreas Geysegoms
     */
    private Double berekenTotaal(ArrayList<Artikel> logs) {
        Double res = 0.0;
        for (Artikel a : logs) {
            res += a.getVerkoopprijs();
        }
        return res;
    }

    public void setView(LogPane logPane) {
        this.view = logPane;
    }

    public void toonLogs(){
        winkel.toonLogs();
    }

}
