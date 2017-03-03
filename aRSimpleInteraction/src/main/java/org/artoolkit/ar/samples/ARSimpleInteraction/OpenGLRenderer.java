package org.artoolkit.ar.samples.ARSimpleInteraction;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer extends ARRenderer {

    private int markerID = -1;
    Group root = new Group();
    Car car;
    private TextView speedometer;

    public OpenGLRenderer(Context context) {
        Group group = new Group();
        car = new Car(30, 30, 30);
        group.add(car);
        speedometer = (TextView) ((Activity)context).findViewById(R.id.speedometer);
        speedometer.setText(Float.toString(car.getSpeed()));
        root = group;
    }

    @Override
    public boolean configureARScene() {

        markerID = ARToolKit.getInstance().addMarker("single;Data/hiro.patt;80");
        if (markerID < 0) return false;

        return true;

    }

    public void turnCarRight() {
        car.rotateRight();
    }

    public void turnCarLeft() {
        car.rotateLeft();
    }

    public void aButtonPressed() {
        car.increaseSpeed();
        speedometer.setText(Float.toString(car.getSpeed()));
    }

    public void aButtonReleased() {
        car.decreaseSpeed();
        speedometer.setText(Float.toString(car.getSpeed()));
    }

    public void bButtonPressed() {
        car.decreaseSpeed();
        speedometer.setText(Float.toString(car.getSpeed()));
    }

    public void draw(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadMatrixf(ARToolKit.getInstance().getProjectionMatrix(), 0);

        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glFrontFace(GL10.GL_CW);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        if (ARToolKit.getInstance().queryMarkerVisible(markerID)) {
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerID), 0);
            root.draw(gl);
        }


    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background color to black ( rgba ).
        //gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // Enable Smooth Shading, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);
    }

}