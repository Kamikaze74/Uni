package fh.pk1.gui;

import javafx.stage.*;
public abstract class RisikoErfassungView extends Stage {


    public RisikoErfassungView(Stage owner) {
        this.initOwner(owner);
    }

    public abstract void showView();
}