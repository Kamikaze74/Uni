package fh.pk1.gui;

import fh.pk1.gui.beans.RisikoBean;
import javafx.stage.*;
public abstract class RisikoErfassungView extends Stage {

    RisikoBean bean;
    protected boolean wurdeGespeichert = false;


    public RisikoErfassungView(Stage owner, RisikoBean bean) {
        this.initOwner(owner);
        this.bean = bean;
    }

    public abstract void showView();
    public boolean isGespeichert() {
        return wurdeGespeichert;
    }
    public RisikoBean getBean() {
        return bean;
    }
}