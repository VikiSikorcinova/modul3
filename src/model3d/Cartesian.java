package model3d;

import transforms.Point3D;

import java.awt.*;

public class Cartesian extends Solid {

    public Cartesian(Color color) {
        super(color);

        final int y = 0;
        int i = 0;
        for (double x = -5; x <= 5; x += 0.1) {
            double z = Math.pow(x, 2);
            vertexBuffer.add(new Point3D(x, y, z));
            if (x > -5) {
                addIndices(i);
                addIndices(++i);
            }
        }
    }
}
