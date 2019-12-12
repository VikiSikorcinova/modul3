package model3d;

import transforms.Point3D;

import java.awt.*;

public class Cube extends Solid {

    public Cube(Color color, double sizeA, double sizeB) {
        super(color);

        vertexBuffer.add(new Point3D(-sizeA, -sizeA, -sizeB));
        vertexBuffer.add(new Point3D(sizeA, -sizeA, -sizeB));
        vertexBuffer.add(new Point3D(sizeA, sizeA, -sizeB));
        vertexBuffer.add(new Point3D(-sizeA, sizeA, -sizeB));

        vertexBuffer.add(new Point3D(-sizeA, -sizeA, sizeB));
        vertexBuffer.add(new Point3D(sizeA, -sizeA, sizeB));
        vertexBuffer.add(new Point3D(sizeA, sizeA, sizeB));
        vertexBuffer.add(new Point3D(-sizeA, sizeA, sizeB));

        // spodna podstava
        addIndices(0, 1, 1, 2, 2, 3, 3, 0);
        // horna podstava
        addIndices(4, 5, 5, 6, 6, 7, 7, 4);
        // "bočne" hrany
        addIndices(0, 4, 1, 5, 2, 6, 3, 7);
    }
}
