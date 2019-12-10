package controller;

import model3d.*;
import renderer.GPURenderer;
import renderer.Renderer3D;
import view.Raster;
import transforms.*;

import java.awt.*;
import java.awt.event.*;

public class Controller3D implements Controller {

    private GPURenderer renderer;
    private Mat4 model, view, projection;
    private Camera camera;
    private Solid[] tree;
    private Solid cartesian;
    private Solid axis;
    private int camStartX, camStartY;


    public Controller3D(Raster raster) {
        initObjects(raster);
        initListeners(raster);
        display();
    }

    private void display() {
        renderer.clear();

//        camera.getViewMatrix()
        renderer.setView(camera.getViewMatrix());
        renderer.setProjection(projection);
        renderer.setModel(new Mat4Identity());
        renderer.draw(axis);
        renderer.draw(tree);
        renderer.draw(cartesian);


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

        tree = new Solid[4];
        tree[0] = new NPyramid(0.5);
        for (int i = 0; i < tree[0].getVertexBuffer().size(); i++) {
            Point3D p = tree[0].getVertexBuffer().get(i);
            Point3D nP = p.mul(new Mat4Transl(0, 0, 3));
            tree[0].getVertexBuffer().set(i, nP);
        }

        tree[1] = new NPyramid(0.8);
        for (int i = 0; i < tree[1].getVertexBuffer().size(); i++) {
            Point3D p = tree[1].getVertexBuffer().get(i);
            Point3D nP = p.mul(new Mat4Transl(0, 0, 2.3));
            tree[1].getVertexBuffer().set(i, nP);
        }
        tree[2] = new NPyramid(1);
        for (int i = 0; i < tree[2].getVertexBuffer().size(); i++) {
            Point3D p = tree[2].getVertexBuffer().get(i);
            Point3D nP = p.mul(new Mat4Transl(0, 0, 1.3));
            tree[2].getVertexBuffer().set(i, nP);
        }
        tree[3] = new Cube(Color.BLACK, 0.2, 0.3);

        cartesian = new Cartesian(Color.YELLOW);

        axis = new Axis();
        for (int i = 0; i < axis.getVertexBuffer().size(); i++) {
            Point3D p = axis.getVertexBuffer().get(i);
            Point3D nP = p.mul(new Mat4Scale(3));
            axis.getVertexBuffer().set(i, nP);
        }
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
                    case KeyEvent.VK_PLUS:
                        break;
                    case KeyEvent.VK_MINUS:
                        break;
                }
                display();
            }
        });
    }

}
