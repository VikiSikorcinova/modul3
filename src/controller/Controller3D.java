package controller;

import model3d.Cartesian;
import model3d.Cube;
import model3d.NPyramid;
import model3d.Solid;
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
    private Solid[] solids;
    private Solid cartesian;
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
        renderer.draw(solids[0]);
        renderer.setModel(new Mat4Scale(-5));
        renderer.setModel(new Mat4Transl(0,0,4));
        renderer.draw(solids[1]);
        renderer.setModel(new Mat4Transl(0,0,2));
        renderer.draw(solids[2]);

        //renderer.draw(cartesian);
/*
* for (int i = 0; i < solids[0].getVertexBuffer().size(); i++) {
            Point3D p = solids[0].getVertexBuffer().get(i);
            Point3D nP = p.mul(new Mat4Scale(0.5));
            solids[0].getVertexBuffer().set(i,nP);
        }*/


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
                .withPosition(new Vec3D(1.5, -6, 2))
                .withAzimuth(Math.toRadians(90))
                .withZenith(Math.toRadians(-20));

//        camera = camera.forward(0.1);
//
//        camera = camera.withAzimuth(Math.toRadians(95));
//        camera = camera.addAzimuth(Math.toRadians(5));

        projection = new Mat4PerspRH(
                Math.PI / 3,
                Raster.HEIGHT / (float) Raster.WIDTH,
                0.5,
                50
        );

//        projection = new Mat4OrthoRH();

        solids = new Solid[3];
        solids[0] = new NPyramid(Color.GREEN);
        solids[1] = new NPyramid(Color.GREEN);
        solids[2] = new NPyramid(Color.GREEN);
//        solids[1] = new Cube();

        cartesian = new Cartesian(Color.YELLOW);
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
                switch (e.getKeyCode()){
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
