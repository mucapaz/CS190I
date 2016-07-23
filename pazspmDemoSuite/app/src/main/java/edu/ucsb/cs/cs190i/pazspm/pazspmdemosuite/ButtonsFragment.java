package edu.ucsb.cs.cs190i.pazspm.pazspmdemosuite;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Samuel on 2/9/2016.
 */
public class ButtonsFragment extends Fragment {

    AppCompatActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.content_main);
            ll.setGravity(0);
        }
        //if()

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
//
//        SpeechFragment frag = new SpeechFragment();
//        frag.setArguments(bundle);
//
//        FragmentTransaction fm = activity.getSupportFragmentManager().beginTransaction();
//        fm.replace(R.id.fragment_container, frag);
//        fm.addToBackStack(null);
//        fm.commit();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            activity = (AppCompatActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

}
