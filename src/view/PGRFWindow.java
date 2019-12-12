package view;

import javax.swing.*;
import java.awt.*;

public class PGRFWindow extends JFrame {

    private final Raster raster;

    public PGRFWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PGRF1 cvičení");

        raster = new Raster();
        raster.setFocusable(true);
        raster.grabFocus();

        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap(0);

        setLayout(layout);
        add(raster);
        pack();

        setLocationRelativeTo(null);
    }

    public Raster getRaster() {
        return raster;
    }

}
