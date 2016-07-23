package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * Created by Samuel on 2/11/2016.
 */
public class AnimationFragment extends Fragment{

    View rootView;
    TargetView animationView;
    DrawingThread dt;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.animation_layout, container, false);
        Random rand = new Random();
        int x = rand.nextInt(80);
        int y = rand.nextInt(80);
        if(x < 0 ) x=-x;
        if(y < 0 ) y=-y;

        animationView = (TargetView) rootView.findViewById(R.id.animation_view);
        animationView.set(x,y);
        DrawingThread dt = new DrawingThread(animationView, 50);
        dt.start();
        return rootView;
    }


}
