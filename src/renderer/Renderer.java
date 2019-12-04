package renderer;

import view.Raster;

public abstract class Renderer {

    final Raster raster;

    Renderer(Raster raster) {
        this.raster = raster;
    }

    public void clear() {
        raster.clear();
    }
}
