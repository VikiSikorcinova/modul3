package model3d;

import transforms.Point3D;

import java.awt.*;

public class Axis extends Solid {
    public Axis() {
        super(Color.black);
        vertexBuffer.add(new Point3D(0, 0, 0));
        vertexBuffer.add(new Point3D(0, 1, 0));
        vertexBuffer.add(new Point3D(1, 0, 0));
        vertexBuffer.add(new Point3D(0, 0, 1));

        addIndices(0, 1, 0, 2, 0, 3);
    }
}
