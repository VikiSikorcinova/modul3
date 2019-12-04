package model3d;

import transforms.Point3D;

import java.awt.*;

public class Cube extends Solid {

    public Cube() {
        this(Color.RED);
    }

    public Cube(Color color) {
        super(color);

        vertexBuffer.add(new Point3D(-1, -1, -1));
        vertexBuffer.add(new Point3D(1, -1, -1));
        vertexBuffer.add(new Point3D(1, 1, -1));
        vertexBuffer.add(new Point3D(-1, 1, -1));

        vertexBuffer.add(new Point3D(-1, -1, 1));
        vertexBuffer.add(new Point3D(1, -1, 1));
        vertexBuffer.add(new Point3D(1, 1, 1));
        vertexBuffer.add(new Point3D(-1, 1, 1));

        // spodní podstava
        addIndices(0, 1, 1, 2, 2, 3, 3, 0);
        // horní podstava
        addIndices(4, 5, 5, 6, 6, 7, 7, 4);
        // "boční" hrany
        addIndices(0, 4, 1, 5, 2, 6, 3, 7);
    }
}
