package renderer;

import model3d.Solid;
import view.Raster;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;
import transforms.Vec3D;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class Renderer3D extends Renderer implements GPURenderer {

    private Mat4 model, view, projection;

    public Renderer3D(Raster raster) {
        super(raster);
        model = new Mat4Identity();
        view = new Mat4Identity();
        projection = new Mat4Identity();
    }

    @Override
    public void draw(Solid... solids) {
        for (Solid solid : solids) {
            List<Point3D> vb = solid.getVertexBuffer();
            List<Integer> ib = solid.getIndexBuffer();
            for (int i = 0; i < ib.size(); i += 2) {
                Integer indexP1 = ib.get(i);
                Integer indexP2 = ib.get(i + 1);
                Point3D a = vb.get(indexP1);
                Point3D b = vb.get(indexP2);
                transformLine(a, b, solid.getColor());
            }
        }
    }

    private void transformLine(Point3D a, Point3D b, Color color) {
        a = a.mul(model).mul(view).mul(projection);
        b = b.mul(model).mul(view).mul(projection);

        if (clip(a)) return;
        if (clip(b)) return;

        Optional<Vec3D> dehomogA = a.dehomog();
        Optional<Vec3D> dehomogB = b.dehomog();
        // pokud nelze provést dehomogenizaci (w == 0), tak ukončit průchod
        if (!dehomogA.isPresent() || !dehomogB.isPresent()) {
            return;
        }
        // Java >= 11
        // if (dehomogA.isEmpty() || dehomogB.isEmpty()) {

        Vec3D v1 = dehomogA.get();
        Vec3D v2 = dehomogB.get();

        v1 = transformToWindow(v1);
        v2 = transformToWindow(v2);

        raster.drawLine(
                (int) Math.round(v1.getX()),
                (int) Math.round(v1.getY()),
                (int) Math.round(v2.getX()),
                (int) Math.round(v2.getY()),
                color
        );
    }

    private Vec3D transformToWindow(Vec3D v) {
        return v.mul(new Vec3D(1,-1,1))
                .add(new Vec3D(1,1,0))
                //to 2f je akoby ((float) 2)
                .mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));

    }

    private boolean clip(Point3D p) {
        //podmienka ze X je prilis male alebo prilis velke a vtedy sa to zahodi (je prilis vlavo alebo prilis vpravo)
        if (p.getW() < p.getX() || p.getX() < -p.getW()) return true;
        //to iste s Y a Z
        if (p.getW() < p.getY() || p.getY() < -p.getW()) return true;
        if (p.getW() < p.getZ() || p.getZ() < 0) return true;
        //ak sa neda nic orezat, takze ten bod vidime a mozeme ist dalej
        return false;
    }

    @Override
    public void setModel(Mat4 model) {
        this.model = model;
    }

    @Override
    public void setView(Mat4 view) {
        this.view = view;
    }

    @Override
    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }
}
