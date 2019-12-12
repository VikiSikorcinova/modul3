package model3d;

import transforms.Cubic;
import transforms.Point3D;

import java.awt.*;

public class Curve extends model3d.Cubic {
    public Curve(Color color) {
        super(color);
        for (double a = 2; a <= Math.PI * 12; a += 0.1) {
            double x = Math.exp(-0.1*a)*Math.cos(a)*2.5;
            double y = Math.exp(-0.1*a)*Math.sin(a)*2.5;
            double z = a/10;
            vertexBuffer.add(new Point3D(x, y, z));
        }

        for (int j = 0; j < getVertexBuffer().size()-3; j+=3) {
            cubicList.add(new Cubic(Cubic.BEZIER,
                    getVertexBuffer().get(j),getVertexBuffer().get(j+1),
                    getVertexBuffer().get(j+2),getVertexBuffer().get(j+3)));
        }
    }



}
