package model3d;

import transforms.Point3D;

import java.awt.*;

public class Ferguson extends Cubic {
    public Ferguson(Color color) {
        super(color);

        vertexBuffer.add(new Point3D(-1, 0, -1));
        vertexBuffer.add(new Point3D(1, 0, 1));
        vertexBuffer.add(new Point3D(1, 0, 1));
        vertexBuffer.add(new Point3D(2, 0, 1));

        for (int i = 0; i < getVertexBuffer().size() - 3; i += 3) {
            cubicList.add(new transforms.Cubic(transforms.Cubic.FERGUSON,
                    getVertexBuffer().get(i), getVertexBuffer().get(i + 1),
                    getVertexBuffer().get(i + 2), getVertexBuffer().get(i + 3)));
        }
    }
}
