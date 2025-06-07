package fh.pk1.gui;

import javafx.stage.*;
public abstract class RisikoErfassungView extends Stage {
    private RisikoView risiko;

    public RisikoErfassungView(RisikoView risiko, Stage owner) {
        this.risiko = risiko;
    }

    public abstract void showView();
}