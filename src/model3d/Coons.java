package model3d;

import transforms.Point3D;

import java.awt.*;

public class Coons extends Cubic {
    public Coons(Color color) {
        super(color);
        vertexBuffer.add(new Point3D(1, 1, -1));
        vertexBuffer.add(new Point3D(-1, 1, -1));
        vertexBuffer.add(new Point3D(1, 1, 1));
        vertexBuffer.add(new Point3D(-1, 1, 1));

        for (int i = 0; i < getVertexBuffer().size() - 3; i += 3) {
            cubicList.add(new transforms.Cubic(transforms.Cubic.COONS,
                    getVertexBuffer().get(i), getVertexBuffer().get(i + 1),
                    getVertexBuffer().get(i + 2), getVertexBuffer().get(i + 3)));
        }
    }
}
