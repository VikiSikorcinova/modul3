package model3d;


import transforms.Point3D;

import java.awt.*;

public class NPyramid extends Solid {

    public NPyramid(Color color) {
        super(color);

        vertexBuffer.add(new Point3D(-1, -1, -1));
        vertexBuffer.add(new Point3D(1, -1, -1));
        vertexBuffer.add(new Point3D(1, 1, -1));
        vertexBuffer.add(new Point3D(-1, 1, -1));

        vertexBuffer.add(new Point3D(0, 0, 1));       //4
        addIndices(0, 1, 1, 2, 2, 3, 3, 0);
        addIndices(0, 4, 1, 4, 2, 4, 3, 4);

    }

}
