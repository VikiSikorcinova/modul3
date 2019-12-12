package controller;

import model3d.*;
import model3d.Cubic;
import renderer.GPURenderer;
import renderer.Renderer3D;
import view.Raster;
import transforms.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controller3D implements Controller {

    private GPURenderer renderer;
    private Mat4 model, view, projection;
    private Camera camera;
    private Solid[] tree;
    private Solid cartesian;
    private Solid axis;
    private Solid curve;
    private int camStartX, camStartY;


    public Controller3D(Raster raster) {
        initObjects(raster);
        initListeners(raster);

    }

    public void display() {
        renderer.clear();

        renderer.setView(camera.getViewMatrix());
        renderer.setProjection(projection);
        renderer.setModel(model);
        renderer.draw(axis);
        renderer.draw(tree);
        renderer.draw(cartesian);
        renderer.draw(curve);
animation();
    }

    @Override
    public void initObjects(Raster raster) {
        renderer = new Renderer3D(raster);

        model = new Mat4Identity();

        view = new Mat4ViewRH(
                new Vec3D(0.5, -6, 2),
                new Vec3D(-0.5, 6, -2),
                new Vec3D(0, 0, 1)
        );

        camera = new Camera()
                .withPosition(new Vec3D(7, 3, 2))
                .withAzimuth(Math.toRadians(200))
                .withZenith(Math.toRadians(-10));

        projection = new Mat4PerspRH(
                Math.PI / 3,
                Raster.HEIGHT / (float) Raster.WIDTH,
                0.5,
                50
        );

        //projection = new Mat4OrthoRH(Raster.WIDTH / 180, Raster.HEIGHT / 180, 0.5, 50);

        tree = new Solid[5];
        tree[0] = new NPyramid(0.5);
        tree[0] = translateMethod(tree[0],0,0,3);

        tree[1] = new NPyramid(0.8);
        tree[1] = translateMethod(tree[1],0,0,2.3);
        tree[2] = new NPyramid(1);
        tree[2] = translateMethod(tree[2],0,0,1.3);

        tree[3] = new Cube(Color.BLACK, 0.2, 0.3);

        tree[4] = new Octahedron(0.2);
        tree[4] = translateMethod(tree[4],0,0,4.1);



        cartesian = new Cartesian(Color.YELLOW);

        curve = new Curve(Color.MAGENTA);

        axis = new Axis();
        axis = scaleMethod(axis, 3);

        Timer refresher = new Timer();
        refresher.schedule(new TimerTask() {
            @Override
            public void run() {
                display();
            }
        }, 1000/60);

    }

    private Solid scaleMethod(Solid s,double scale) {
        for (int i = 0; i < s.getVertexBuffer().size(); i++) {
            Point3D p = s.getVertexBuffer().get(i);
            Point3D nP = p.mul(new Mat4Scale(scale));
            s.getVertexBuffer().set(i, nP);
        }
        return s;
    }
    private Solid translateMethod(Solid s,double x,double y, double z) {
        for (int i = 0; i < s.getVertexBuffer().size(); i++) {
            Point3D p = s.getVertexBuffer().get(i);
            Point3D nP = p.mul(new Mat4Transl(x, y, z));
            s.getVertexBuffer().set(i, nP);
        }
        return s;
    }


    @Override
    public void initListeners(Raster raster) {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                camStartX = e.getX();
                camStartY = e.getY();
            }
        });
        int sensitivity = 2500;
        raster.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                camera = camera.addAzimuth((Math.PI / sensitivity) * (camStartX - e.getX()));
                camera = camera.addZenith((Math.PI / sensitivity) * (camStartY - e.getY()));
                camStartX = e.getX();
                camStartY = e.getY();
                display();
            }
        });


        double step = 0.08;
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        camera = camera.forward(step);
                        break;
                    case KeyEvent.VK_S:
                        camera = camera.backward(step);
                        break;
                    case KeyEvent.VK_A:
                        camera = camera.left(step);
                        break;
                    case KeyEvent.VK_D:
                        camera = camera.right(step);
                        break;
                    case KeyEvent.VK_O:
                        projection = new Mat4OrthoRH(Raster.WIDTH/100, Raster.HEIGHT/100, 0.5, 50);
                        break;
                    case KeyEvent.VK_P:
                        projection = new Mat4PerspRH(
                                Math.PI / 3,
                                Raster.HEIGHT / (float) Raster.WIDTH,
                                0.5,
                                50);

                        break;
                    case KeyEvent.VK_M:
                        model = model.mul(new Mat4Scale(0.5));
                        break;
                    case KeyEvent.VK_N:
                        model = model.mul(new Mat4Scale(1.5));
                        break;
                    case KeyEvent.VK_UP:
                        model = model.mul(new Mat4RotY(0.5));
                        break;
                    case KeyEvent.VK_DOWN:
                        model = model.mul(new Mat4RotY(-0.5));
                        break;
                    case KeyEvent.VK_LEFT:
                        model = model.mul(new Mat4RotZ(0.5));
                        break;
                    case KeyEvent.VK_RIGHT:
                        model = model.mul(new Mat4RotZ(-0.5));
                        break;
                    case KeyEvent.VK_I:
                        model = model.mul(new Mat4Transl(0,1,0));
                        break;
                    case KeyEvent.VK_K:
                        model = model.mul(new Mat4Transl(0,-1,0));
                        break;
                    case KeyEvent.VK_L:
                        model = model.mul(new Mat4Transl(1,0,0));
                        break;
                    case KeyEvent.VK_J:
                        model = model.mul(new Mat4Transl(-1,0,0));
                        break;
                    case KeyEvent.VK_R:
                        animation();
                        break;
                }

                display();
            }
        });

    }
    private void animation() {
        Timer animation = new Timer();

        animation.schedule(new TimerTask() {
            @Override
            public void run() {

                //Pruchod polem animací
                    Solid solid = tree[4];
                    //solid = rotation(solid, "Z", Math.PI / 100);
                    for (int v = 0; v < solid.getVertexBuffer().size(); v++) {
                        Point3D point3D = solid.getVertexBuffer().get(v);
                        Point3D newPoint = point3D.mul(new Mat4RotZ(Math.PI / 100));
                        solid.getVertexBuffer().set(v, newPoint);
                    }
                    tree[4]=solid;
                }
        }, 1000/60);
    }

}
