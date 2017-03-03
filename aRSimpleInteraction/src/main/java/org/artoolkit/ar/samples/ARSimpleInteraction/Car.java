package org.artoolkit.ar.samples.ARSimpleInteraction;

import javax.microedition.khronos.opengles.GL10;

public class Car extends Mesh {
    private float speed = 0.0f;

    public void increaseSpeed(){
        if(speed<3.0f) speed = speed + 0.1f;
    }

    public void decreaseSpeed(){
        if(speed>0.0f) speed = speed - 0.1f;
    }

    public void rotateLeft(){
        if(rz<45)rz = rz + 1.0f;
    }

    public void rotateRight(){
        if(rz>-45)rz = rz - 1.0f;
    }

    public float getSpeed(){
        return speed;
    }

    @Override
    public void draw(GL10 gl) {
        super.draw(gl);
        gl.glTranslatef(x+=speed, y, z);//attempting to add acceleration like behaviour
    }

    public Car(float width, float height, float depth) {
        this.z +=15;

        width  /= 2;
        height /= 2;
        depth  /= 2;

        float vertices[] = { -width, -height, -depth, // 0
                            width, -height, -depth, // 1
                            width,  height, -depth, // 2
                            -width,  height, -depth, // 3
                            -width, -height,  depth, // 4
                            width, -height,  depth, // 5
                            width,  height,  depth, // 6
                            -width,  height,  depth, // 7
        };

        short indices[] = { 0, 4, 5,
                0, 5, 1,
                1, 5, 6,
                1, 6, 2,
                2, 6, 7,
                2, 7, 3,
                3, 7, 4,
                3, 4, 0,
                4, 7, 6,
                4, 6, 5,
                3, 0, 1,
                3, 1, 2, };

        float c = 1.0f;
        float colors[] = {
                0, 0, 0, c, // 0 black
                c, 0, 0, c, // 1 red
                c, c, 0, c, // 2 yellow
                0, c, 0, c, // 3 green
                0, 0, c, c, // 4 blue
                c, 0, c, c, // 5 magenta
                c, c, c, c, // 6 white
                0, c, c, c, // 7 cyan
        };

        setIndices(indices);
        setVertices(vertices);
        setColors(colors);
    }
}