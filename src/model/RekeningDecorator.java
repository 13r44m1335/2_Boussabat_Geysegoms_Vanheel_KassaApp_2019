package model;

import controller.KassaController;

/**
 * Deze klasse stelt de decorator voor van de rekening.
 * @author Andreas Geysegoms
 * @version 1.0
 */
public abstract class RekeningDecorator extends RekeningAbstract {

    /**
     * Deze methode haalt de description op.
     * @return de description.
     * @author Andreas Geysegoms
     */
    public abstract String getDescription();
}