package edu.ucsb.cs.cs190i.pazspm.touchgestures;

import android.content.SyncStatusObserver;
import android.graphics.Matrix;
import android.widget.ImageView;

/**
 * Created by Samuel on 1/24/2016.
 */
public class Board {
    Point points[] = new Point[4];
    ImageView view;
    public Board(ImageView view){
        this.view = view;
        points[0] = new Point(0,0);
        points[1] = new Point(view.getMaxWidth(),0);
        points[2] = new Point(0,view.getMaxHeight());
        points[3] = new Point(view.getMaxWidth(),view.getMaxHeight());
    }

    public boolean insideView(Matrix m){
        Point mpoints[] = new Point[4];

        for(int x=0;x<4;x++) mpoints[x] = points[x].applyMatrix(m);

//        for(int x=0;x<4;x++){
//            System.out.println(x + " " + mpoints[x].getX() + " " + mpoints[x].getY() + " " + points[x].getX() + " " + points[x].getY());
//            System.out.println("--------------------");
//        }

        if(mpoints[0].getX() > points[0].getX() || mpoints[0].getY() > points[0].getY()) return false;

        if(mpoints[1].getX() < points[1].getX() || mpoints[1].getY() > points[1].getY()) return false;

        if(mpoints[2].getX() > points[2].getX() || mpoints[2].getY() < points[2].getY()) return false;

        if(mpoints[3].getX() < points[3].getX() || mpoints[3].getY() < points[3].getY()) return false;

        return true;
    }

}
