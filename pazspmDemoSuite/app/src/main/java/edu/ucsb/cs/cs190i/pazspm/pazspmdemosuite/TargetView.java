package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Samuel on 2/11/2016.
 */
public class TargetView extends View{

    int px = -1,py = -1,vx,vy, bsize = 100;
    Paint red;

    public TargetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void set(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
        red = new Paint();
        red.setARGB(255,255,0,0);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if(px == -1 && py == -1){
            px = canvas.getWidth()/2;
            py = canvas.getHeight()/2;
        }

        px+=vx;
        py+=vy;

        if((px < bsize) || (px + bsize> canvas.getWidth())){
            px-=vx;
            vx*=-1;
            px+=vx;
        }

        if((py < bsize) || (py + bsize> canvas.getHeight())){
            py-=vy;
            vy*=-1;
            py+=vy;
        }

        canvas.drawCircle(px,py,bsize,red);
    }
}
