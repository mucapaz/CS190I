package edu.ucsb.cs.cs190i.pazspm.touchgestures;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Samuel on 1/21/2016.
 */
class Point{

    private float pos[] = new float[2], size;

    private Paint paint;

    public Point(float x, float y, float size, Paint paint){
        pos[0] = x;
        pos[1] = y;
        this.size = size;
        this.paint = paint;
        System.out.println(paint.getColor() == Color.RED);
    }

    public Point(float x, float y){
        pos[0] = x;
        pos[1] = y;
    }

    public float getSize() {
        return size;
    }

    public float getX() {
        return pos[0];
    }

    public float getY() {
        return pos[1];
    }

    public void setX(float x){
        pos[0] = x;
    }

    public void setY(float y){
        pos[1] = y;
    }

    public Paint getPaint() {
        return paint;
    }

    public float[] getPos(){
        return pos;
    }

    public Point applyMatrix(Matrix m){
        float ar[] = new float[2];
        ar[0] = pos[0];
        ar[1] = pos[1];
        m.mapPoints(ar);
        return new Point(ar[0],ar[1]);
    }
}