package model3d;

import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Solid {

    final List<Point3D> vertexBuffer = new ArrayList<>();
    final List<Integer> indexBuffer = new ArrayList<>();
    Color color;

    public Solid(Color color) {
        this.color = color;
    }

    public void addIndices(Integer... indices) {
        indexBuffer.addAll(Arrays.asList(indices));
    }

    public List<Point3D> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
