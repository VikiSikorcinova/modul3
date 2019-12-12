package model3d;

import transforms.Point3D;

import java.awt.*;

public class Octahedron extends Solid {
    public Octahedron(double size, Color color) {
        super(color);
        init(size);
    }

    public Octahedron(Color color) {
        super(color);
        init(1);
    }

    void init(double size) {

        vertexBuffer.add(new Point3D(-size, -size, -size));
        vertexBuffer.add(new Point3D(size, -size, -size));
        vertexBuffer.add(new Point3D(size, size, -size));
        vertexBuffer.add(new Point3D(-size, size, -size));

        vertexBuffer.add(new Point3D(0, 0, size));
        vertexBuffer.add(new Point3D(0, 0, -size * 3));
        addIndices(0, 1, 1, 2, 2, 3, 3, 0);
        addIndices(0, 4, 1, 4, 2, 4, 3, 4);
        addIndices(0, 5, 1, 5, 2, 5, 3, 5);
    }

}


