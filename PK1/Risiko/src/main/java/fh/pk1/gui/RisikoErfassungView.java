package fh.pk1.gui;

import javafx.stage.*;
public abstract class RisikoErfassungView extends Stage {
    private Risiko risiko;

    public RisikoErfassungView(Risiko risiko, Stage owner) {
        this.risiko = risiko;
    }

    public abstract void showView();
}