package edu.ucsb.cs.cs190i.pazspm.touchgestures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Samuel on 1/20/2016.
 */
public class TouchView extends ImageView {

    private ArrayList<Point> points;

    private float touchX, touchY, scaleFactor = 1.f, scaleFocusX, scaleFocusY, pointsNumber =0;

    private boolean actionDown = false, moved = false;

    private ScaleGestureDetector scaleGestureDetector;

    private boolean scaling = false;

    private Point p1 = null,p2 = null, pointDown = null;

    Board board;

    public TouchView(Context context, AttributeSet attrs){
        super(context, attrs);
        setAdjustViewBounds(true);
        setScaleType(ScaleType.MATRIX);

        board = new Board(this);

        points = new ArrayList<Point>();

        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.SimpleOnScaleGestureListener(){
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scaleFactor *= detector.getScaleFactor();
                // Doesn't let the image be too large or too small
                scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
                // this will force the call for onDraw()
                scaleFocusX = detector.getFocusX();
                scaleFocusY = detector.getFocusY();
                scaling = true;
                invalidate();
                return true;
            }
        });
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(scaling && scaleFactor >= 1.f) {
            Matrix matrix = getImageMatrix();
            matrix.setScale(scaleFactor, scaleFactor, scaleFocusX, scaleFocusY);
            setImageMatrix(matrix);

            scaling = false;
        }

        for(Point p : points){
            canvas.drawCircle(p.getX(),p.getY(),p.getSize(), p.getPaint());
        }
    }

    public void addPoint(Point point){
        points.add(point);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        scaleGestureDetector.onTouchEvent(event);

        int action = MotionEventCompat.getActionMasked(event);

        switch(action){
            case (MotionEvent.ACTION_DOWN):
                getP1P2(event);
                moved = false;



                return true;
            case (MotionEvent.ACTION_UP):
                if(event.getPointerCount() == 1 && p1.getX() == event.getX(0) && p1.getY() == event.getY(0)
                        && !moved){ //TAP
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    addPoint(new Point(event.getX(), event.getY(), 10, paint));
                    invalidate();
                }

                return true;
            case (MotionEvent.ACTION_MOVE): // TRANSLATION OR ROTATION
                moved = true;

                if(event.getPointerCount() == 1){ // TRANSLATION
                    Point p = new Point(event.getX(), event.getY());
                    Matrix m = getImageMatrix();
                    m.postTranslate(p.getX() - p1.getX(), p.getY() - p1.getY());
                    setImageMatrix(m);
                    invalidate();
                    p1 = p;

                }else if(event.getPointerCount() == 2){ // ROTATION
                    Point a1 = new Point(event.getX(0), event.getY(0));
                    Point a2 = new Point(event.getX(1), event.getY(1));

                    Point focal = new Point( (a1.getX()+a2.getX())/2, (a1.getY()+a2.getY())/2 );

                    float angle =  angle(a1,a2, focal);
                    Matrix m = getImageMatrix();
                    m.postRotate(angle/5,focal.getX(),focal.getY());
                    setImageMatrix(m);
                    invalidate();


                }

                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    private void getP1P2(MotionEvent event){
        p1 = new Point(event.getX(0), event.getY(0));
        if(event.getPointerCount() == 2){
            p2 = new Point(event.getX(1),event.getY(1));
        }
    }

    private float angle(Point po1, Point po2, Point po3){
        float a1 = (float) Math.atan2(po1.getY() - po3.getY(), po1.getX() - po3.getX());
        float a2 = (float) Math.atan2(po2.getY() - po3.getY(), po2.getX() - po3.getX());

        return a1-a2;
    }
}
