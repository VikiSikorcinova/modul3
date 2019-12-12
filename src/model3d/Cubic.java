package model3d;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Cubic extends Solid {
    protected java.util.List<transforms.Cubic> cubicList;

    public Cubic(Color color) {
        super(color);
        cubicList = new ArrayList<>();
    }

    public List<transforms.Cubic> getCubicList() {
        return cubicList;
    }
}
