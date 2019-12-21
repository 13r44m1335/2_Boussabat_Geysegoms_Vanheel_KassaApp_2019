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



    @Override
    public void update(ArrayList<Artikel> logs, Enum soort) {
        artikels = winkel.getCurrent().getAll();
        view.updateLog(artikels);
    }

    public void setView(LogPane logPane) {
        this.view = logPane;
    }

    public void toonLogs(){
        winkel.toonLogs();
    }


}
